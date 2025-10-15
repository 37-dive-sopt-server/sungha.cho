package org.sopt.domain; // 도메인(비즈니스 데이터 모델) 패키지

import java.time.LocalDate;

public class Member { // Member는 '회원' 한 명을 표현하는 데이터 클래스

    private Long id;   // 회원을 구별하는 고유 번호(자동 증가 등으로 발급)
    private String name; // 회원 이름
    private String email;   // 이메일
    private LocalDate birth;   // 생년월일
    private Gender gender;  // 성별 (ENUM)

    // 생성자: Member 객체를 만들 때 id와 name을 반드시 받도록 강제
    public Member(Long id, String name, String email, LocalDate birth, Gender gender) {
        this.id = id;       // 전달받은 id를 필드에 저장
        this.name = name;   // 전달받은 name을 필드에 저장
        this.email = email;
        this.birth = birth;
        this.gender = gender;
    }

    // Getter: 외부에서 값을 읽을 수 있도록 제공(불변성 유지 차원에서 setter는 없음)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getBirth() { return birth; }
    public Gender getGender() { return gender; }
}
