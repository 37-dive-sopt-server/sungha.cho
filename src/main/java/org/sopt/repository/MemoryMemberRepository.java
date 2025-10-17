package org.sopt.repository;

import org.sopt.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
