package sopt.org.FourthSeminar.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    GET_POST_LIST_SUCCESS(HttpStatus.OK, "게시물 리스트 조회에 성공했습니다."),
    GET_POST_SUCCESS(HttpStatus.OK, "게시물 조회에 성공했습니다."),
    GET_EMOTION_CALENDAR_SUCCESS(HttpStatus.OK, "감정 캘린더 조회에 성공했습니다."),
    GET_EMOTION_SUCCESS(HttpStatus.OK, "감정 조회에 성공했습니다."),

    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_BOARD_SUCCESS(HttpStatus.CREATED, "게시물 생성이 완료됐습니다."),
    CREATE_EMOTION_SUCCESS(HttpStatus.CREATED, "감정 기록에 성공했습니다."),
    CREATE_VOCIE_TO_TEXT_SUCCESS(HttpStatus.CREATED,"네이버 클로바를 이용한 stt와 감성분석 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}