package org.sopt.global.exception.constant;

public class ErrorMessage {

    private ErrorMessage() {}

    public static final String INVALID_NAME = "⚠️ 이름을 입력해주세요.";
    public static final String DUPLICATE_EMAIL = "⚠️ 이미 등록된 이메일입니다: ";
    public static final String INVALID_GENDER = "⚠️ 유효하지 않은 성별 입력입니다 (MALE 또는 FEMALE만 허용)";
    public static final String UNDER_AGE = "⚠️ 가입 불가: 만 20세 미만입니다.";
    public static final String MEMBER_NOT_FOUND = "⚠️ 해당 ID의 회원을 찾을 수 없습니다.";
    public static final String EMPTY_MEMBER_LIST = "ℹ️ 등록된 회원이 없습니다.";

    public static final String FILE_INIT_FAILED = "⚠️ 파일 초기화 중 오류가 발생했습니다.";
    public static final String MEMBER_SAVE_FAILED = "⚠️ 파일에서 회원 정보를 불러오던 중 오류가 발생했습니다.";
    public static final String FILE_UPDATE_FAILED = "⚠️ 파일 저장 중 오류가 발생했습니다.";
}
