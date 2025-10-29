package org.sopt.repository;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import org.sopt.global.exception.BusinessException;
import static org.sopt.global.exception.constant.ErrorCode.*;

@Repository
public class FileMemberRepository implements MemberRepository {
    private static final String FILE_PATH = "members.txt";
    private static final int FIELD_SIZE = 5;
    private static final String HEADER = "id,name,email,birthDate,gender";

    private final Map<Long, Member> idMap = new HashMap<>();
    private final Map<String, Long> emailMap = new HashMap<>();
    private Long sequence = 1L;

    public FileMemberRepository() {
        initFile();
        loadFromFile();
    }

    /* ---------- 파일 초기화 ---------- */
    private void initFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(HEADER);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new BusinessException(FILE_INIT_FAILED);
        }
    }

    /* ---------- CRUD ---------- */

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId(sequence++);
        }
        idMap.put(member.getId(), member);
        emailMap.put(member.getEmail().toLowerCase(), member.getId());
        saveToFile();
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(idMap.get(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(emailMap.get(email.toLowerCase()))
                .map(idMap::get);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(idMap.values());
    }

    @Override
    public void deleteById(Long id) {
        Member removed = idMap.remove(id);
        if (removed != null) {
            emailMap.remove(removed.getEmail().toLowerCase());
            saveToFile();
        }
    }

    /* ---------- 파일 동기화 ---------- */

    private void loadFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            skipHeader(reader);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                Member member = parseLine(line);
                idMap.put(member.getId(), member);
                emailMap.put(member.getEmail().toLowerCase(), member.getId());
                updateSequence(member.getId());
            }
        } catch (IOException e) {
            throw new BusinessException(MEMBER_SAVE_FAILED);
        }
    }

    private void skipHeader(BufferedReader reader) throws IOException {
        reader.readLine();
    }

    private void updateSequence(Long id) {
        if (id >= sequence) {
            sequence = id + 1;
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            writer.write(HEADER);
            writer.newLine();
            for (Member member : idMap.values()) {
                writer.write(formatMember(member));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new BusinessException(FILE_UPDATE_FAILED);
        }
    }

    /* ---------- 파싱/포맷 ---------- */

    private Member parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != FIELD_SIZE) {
            throw new BusinessException(MEMBER_SAVE_FAILED);
        }

        try {
            Long id = Long.parseLong(parts[0]);
            String name = parts[1];
            String email = parts[2];
            LocalDate birthDate = LocalDate.parse(parts[3]);
            Gender gender = Gender.valueOf(parts[4].toUpperCase());
            return new Member(id, name, email, birthDate, gender);
        } catch (RuntimeException e) {
            throw new BusinessException(MEMBER_SAVE_FAILED);
        }
    }

    private String formatMember(Member member) {
        return String.format("%d,%s,%s,%s,%s",
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getBirth(),
                member.getGender());
    }
}
