package org.sopt.domain; // 도메인(비즈니스 데이터 모델) 패키지

public class Member { // Member는 '회원' 한 명을 표현하는 데이터 클래스

    private Long id;   // 회원을 구별하는 고유 번호(자동 증가 등으로 발급)
    private String name; // 회원 이름

    // 생성자: Member 객체를 만들 때 id와 name을 반드시 받도록 강제
    public Member(Long id, String name) {
        this.id = id;       // 전달받은 id를 필드에 저장
        this.name = name;   // 전달받은 name을 필드에 저장
    }

    // Getter: 외부에서 id를 읽을 수 있도록 제공(불변성 유지 차원에서 setter는 없음)
    public Long getId() {
        return id;
    }

    // Getter: 외부에서 name을 읽을 수 있도록 제공
    public String getName() {
        return name;
    }
}
