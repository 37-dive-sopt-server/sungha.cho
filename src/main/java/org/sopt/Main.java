package org.sopt; // 이 파일이 포함된 패키지 경로(폴더 논리 이름). import에서 같은 패키지면 경로 생략 가능.

import org.sopt.controller.MemberController; // 컨트롤러(요청을 서비스에 전달하는 역할) 사용을 위해 불러온다.
import org.sopt.domain.Member;               // Member 타입(도메인 객체)을 사용하기 위해 불러온다.
import org.sopt.repository.MemoryMemberRepository; // 메모리 저장소(Repository) 구현체를 사용하기 위해 불러온다.
import org.sopt.service.MemberServiceImpl;   // 서비스 구현체를 사용하기 위해 불러온다.

import java.util.List;       // 전체 회원 조회 결과(List<Member>)를 출력할 때 필요
import java.util.Optional;   // null 대신 안전하게 값을 담을 수 있는 컨테이너
import java.util.Scanner;    // 콘솔에서 사용자 입력을 읽기 위한 도구

public class Main {          // 자바 애플리케이션의 시작 클래스 정의
    public static void main(String[] args) { // 자바 프로그램의 진입점(시작되는 메서드)

        // 아래 3개는 객체를 미리 만들어 둔 것.
        // 하지만 실제로는 MemberController 내부에서 다시 new를 하기 때문에,
        // 여기서 만든 두 개(memberRepository, memberService)는 사용되지 않음(Dead code).
        MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 메모리에 회원을 저장/조회할 저장소 인스턴스 생성
        MemberServiceImpl memberService = new MemberServiceImpl();             // 비즈니스 로직을 담당할 서비스 인스턴스 생성
        MemberController memberController = new MemberController();            // 요청을 받아 서비스로 전달할 컨트롤러 인스턴스 생성

        Scanner scanner = new Scanner(System.in); // 콘솔 입력(키보드)을 읽기 위한 스캐너 생성

        while (true) { // 사용자가 종료를 선택할 때까지 무한 반복으로 메뉴를 보여준다.
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨"); // 메뉴 헤더 출력
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");      // 1번 메뉴: 이름 입력 받아 회원 등록
            System.out.println("2️⃣. ID로 회원 조회 🔍"); // 2번 메뉴: 숫자 ID로 회원 한 명 조회
            System.out.println("3️⃣. 전체 회원 조회 📋"); // 3번 메뉴: 저장된 모든 회원 목록 출력
            System.out.println("4️⃣. 종료 🚪");          // 4번 메뉴: 프로그램 종료
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");       // 사용자에게 메뉴 번호 입력 안내

            String choice = scanner.nextLine(); // 사용자가 입력한 한 줄을 문자열로 읽는다(엔터 전까지)

            switch (choice) { // 입력한 문자열 값에 따라 분기 처리
                case "1": // "1"이면 회원 등록 로직 수행
                    System.out.print("등록할 회원 이름을 입력하세요: "); // 이름 입력 안내
                    String name = scanner.nextLine(); // 사용자로부터 이름 문자열 입력 받음
                    if (name.trim().isEmpty()) {      // 공백만 입력하거나 빈 문자열이면
                        System.out.println("⚠️ 이름을 입력해주세요."); // 경고 메시지 출력
                        continue; // while의 다음 반복으로 넘어감(메뉴로 복귀)
                    }
                    Long createdId = memberController.createMember(name); // 컨트롤러에 등록 요청을 보냄 → 내부적으로 서비스가 저장
                    if (createdId != null) { // 등록이 성공하면 ID가 반환됨
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")"); // 성공 메시지
                    } else {
                        System.out.println("❌ 회원 등록 실패"); // 예외적 상황(현재 코드에선 거의 발생 X)
                    }
                    break; // switch 종료

                case "2": // "2"이면 ID로 단일 회원 조회
                    System.out.print("조회할 회원 ID를 입력하세요: "); // ID 입력 안내
                    try {
                        Long id = Long.parseLong(scanner.nextLine()); // 문자열을 숫자(Long)로 변환 시도
                        Optional<Member> foundMember = memberController.findMemberById(id); // 컨트롤러로 조회 요청
                        if (foundMember.isPresent()) { // Optional 안에 실제 Member 객체가 있으면
                            System.out.println(
                                    "✅ 조회된 회원: ID=" + foundMember.get().getId() // get()으로 Optional에서 값 꺼냄
                                            + ", 이름=" + foundMember.get().getName()
                            );
                        } else { // Optional이 비어 있다면(해당 ID 없음)
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) { // 숫자로 변환 실패하면(문자 입력 등)
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;

                case "3": // "3"이면 전체 회원 목록 조회
                    List<Member> allMembers = memberController.getAllMembers(); // 컨트롤러로 전체 조회 요청
                    if (allMembers.isEmpty()) { // 결과가 비어 있으면(등록된 회원 없음)
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else { // 회원이 하나 이상 있으면 목록 출력
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) { // 리스트를 순회하며 한 줄씩 출력
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;

                case "4": // "4"이면 종료
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!"); // 종료 인사
                    scanner.close(); // 스캐너 리소스 반납(입력 스트림 닫기)
                    return;          // main 메서드를 끝내며 프로그램 종료

                default: // 1~4 이외의 값을 입력한 경우
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요."); // 안내 메시지
            }
        }
    }
}
