package toy.withme58.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs{

    OK(200,200,"성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),400,"잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),500,"서버 에러"),

    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(),512,"Null Pointer"),
    MULTI_REQUEST(405,405,"하루 한번만 요청 가능합니다"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
