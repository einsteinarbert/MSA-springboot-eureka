package io.github.eureka.api.config;

import io.github.eureka.api.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static io.github.eureka.api.common.MsgUtil.SPLIT_CHAR;
import static io.github.eureka.api.common.ResponseCode.FAILURE;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 14:10<br/>
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "trace";

    @NotNull
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException iex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        //Body omitted as it's similar to the method of same name
        // in ProductController example...
        //.....
        log.error(TRACE, iex);
        return ResponseEntity.ok().body(new ResponseDTO<>(FAILURE,
                ResponseDTO.NG, iex.getMessage(), ""));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleItemNotFoundException(
            IllegalArgumentException iex,
            WebRequest _request
    ){
        //Body omitted as it's similar to the method of same name
        // in ProductController example...
        //.....
        log.error("ExceptionHandler", iex);
        String msg = iex.getMessage();
        if (StringUtils.hasLength(msg) && msg.contains(SPLIT_CHAR)) {
            String[] msgLst = msg.split(SPLIT_CHAR);
            try {
                return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                        msgLst[0], msgLst[1], ""));
            } catch (Exception _e) {
                return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                        msgLst[0], msg, ""));
            }
        }
        return ResponseEntity.ok().body(new ResponseDTO<>(FAILURE,
                ResponseDTO.NG, iex.getMessage(), ""));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            RuntimeException ex,
            WebRequest _request
    ){
        //Body omitted as it's similar to the method of same name
        // in ProductController example...
        //.....
        log.error(TRACE, ex);
        return ResponseEntity.ok(ResponseDTO.builder().code(FAILURE).message(ex.getMessage()).build());
    }

    @NotNull
    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            @NotNull Exception ex,
            Object body,
            @NotNull HttpHeaders _headers,
            @NotNull HttpStatus _status,
            @NotNull WebRequest _request) {
        log.error(TRACE, ex);
        return ResponseEntity.ok(ResponseDTO.builder().code(FAILURE).message(ex.getMessage()).build());
    }

}
