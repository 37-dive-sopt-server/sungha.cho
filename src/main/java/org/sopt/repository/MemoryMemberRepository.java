package org.sopt.repository; // 저장소(Repository) 관련 클래스가 모인 패키지

import org.sopt.domain.Member; // 저장/반환할 데이터 타입이 Member이므로 import

import java.util.*; // Map, HashMap, List, ArrayList, Optional 등을 사용하기 위해 한 번에 import

public class MemoryMemberRepository { // 메모리 기반 저장소 구현체(간단한 학습/테스트 용도)

    // static final: 클래스 수준(모든 인스턴스가 공유) + 변경 불가 참조(다른 Map으로 교체 불가)
    // 실제 데이터는 이 Map에 "ID → Member" 형태로 저장된다.
    private static final Map<Long, Member> store = new HashMap<>();

    // 회원 저장 메서드: 키(id)로 맵에 저장하고, 저장된 Member를 그대로 반환
    public Member save(Member member) {
        store.put(member.getId(), member); // member의 id를 키로, member 객체를 값으로 저장
        return member;                      // 저장된 객체를 그대로 돌려준다(체이닝/확인용)
    }

    // ID로 회원 조회: 없을 수도 있으므로 Optional로 감싸서 반환
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Map에서 id 키로 꺼내고, null이면 빈 Optional
    }

    // 전체 회원 조회: Map의 value들만 모아서 새로운 ArrayList로 만들어 반환(원본 보호)
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 컬렉션 복사본을 만들어 돌려주므로 외부에서 수정해도 내부 안전
    }
}
