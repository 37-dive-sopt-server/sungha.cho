package org.sopt.global.exception.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_NAME(HttpStatus.BAD_REQUEST, "INVALID_NAME", "⚠️ 이름을 입력해주세요."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "DUPLICATE_EMAIL", "⚠️ 이미 등록된 이메일입니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "INVALID_GENDER", "⚠️ 유효하지 않은 성별 입력입니다 (MALE 또는 FEMALE만 허용)"),
    UNDER_AGE(HttpStatus.BAD_REQUEST, "UNDER_AGE", "⚠️ 가입 불가: 만 20세 미만입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "⚠️ 해당 ID의 회원을 찾을 수 없습니다."),
    EMPTY_MEMBER_LIST(HttpStatus.NOT_FOUND, "EMPTY_MEMBER_LIST", "ℹ️ 등록된 회원이 없습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "⚠️ 내부 오류가 발생했습니다."),
    FILE_INIT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_INIT_FAILED", "⚠️ 파일 초기화 중 오류가 발생했습니다."),
    MEMBER_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER_SAVE_FAILED", "⚠️ 파일에서 회원 정보를 불러오던 중 오류가 발생했습니다."),
    FILE_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_UPDATE_FAILED", "⚠️ 파일 저장 중 오류가 발생했습니다."),

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_NOT_FOUND", "⚠️ 해당 ID의 아티클을 찾을 수 없습니다."),
    INVALID_TAG(HttpStatus.BAD_REQUEST, "INVALID_TAG", "⚠️ 태그는 CS/DB/SPRING/ETC 중 하나여야 합니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
