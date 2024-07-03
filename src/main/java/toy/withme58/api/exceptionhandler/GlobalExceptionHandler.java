package toy.withme58.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import toy.withMe58.api.common.api.Api;
import toy.withMe58.api.common.error.ErrorCode;
import toy.withMe58.api.common.exception.ApiException;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) //예외들 중 처리하지 않은 마지막 예외들을 이곳에 처리
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>>exception(
            Exception exception
    ){
        log.error("",exception);

        return ResponseEntity.status(500)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR,exception.getMessage())

                );
    }
}
