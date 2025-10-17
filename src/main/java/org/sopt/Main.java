package org.sopt;

import org.sopt.config.AppConfig;
import org.sopt.controller.MemberController;
import org.sopt.domain.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AppConfig config = new AppConfig();
        MemberController memberController = config.memberController();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 회원 삭제 🗑️");
            System.out.println("5️⃣. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("등록할 회원 이름을 입력하세요: ");
                    String name = scanner.nextLine();

                    System.out.print("이메일을 입력하세요: ");
                    String email = scanner.nextLine();

                    System.out.print("생년월일을 입력하세요: ");
                    LocalDate birth = LocalDate.parse(scanner.nextLine());

                    System.out.print("성별을 입력하세요 (MALE/FEMALE): ");
                    String genderInput = scanner.nextLine().toUpperCase();

                    try {
                        Long createdId = memberController.createMember(name, email, birth, genderInput);
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Member m = memberController.findMemberById(id); // Optional 제거
                        System.out.println("✅ 조회된 회원:");
                        System.out.println("   ID: " + m.getId());
                        System.out.println("   이름: " + m.getName());
                        System.out.println("   이메일: " + m.getEmail());
                        System.out.println("   생년월일: " + m.getBirth());
                        System.out.println("   성별: " + m.getGender());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        List<Member> allMembers = memberController.getAllMembers();
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId()
                                    + ", 이름=" + member.getName()
                                    + ", 이메일=" + member.getEmail()
                                    + ", 생년월일=" + member.getBirth()
                                    + ", 성별=" + member.getGender());
                        }
                        System.out.println("--------------------------");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.print("삭제할 회원의 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        memberController.deleteMember(id); // boolean 반환 → 예외 방식으로 변경
                        System.out.println("✅ 회원(ID: " + id + ")이 성공적으로 삭제되었습니다.");
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "5":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;

                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
