package jp.co.mindshift.ayakashi.api.config;

import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        var fieldErr = iex.getBindingResult();
        List<FieldError> fieldErrors = fieldErr.getFieldErrors();
        if (fieldErrors.size() > 0) {
            Map<String, String> data = new HashMap<>();
            ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
            response.setStatusCode(ResponseCode.FAILURE);
            response.setMessage(MsgUtil.getMessageContent("validate.fields.error"));
            for (var f: fieldErrors) {
                String msgContent = MsgUtil.getMessageContent(f.getDefaultMessage());
                data.put(f.getField(), "-1".equals(msgContent) ? f.getDefaultMessage() : msgContent);
            }
            response.setData(data);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok().body(new ResponseDTO<>(ResponseCode.FAILURE, iex.getMessage(), ""));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleItemNotFoundException(
            IllegalArgumentException iex,
            WebRequest ignored
    ){
        //Body omitted as it's similar to the method of same name
        // in ProductController example...
        //.....
        log.error("ExceptionHandler", iex);
        String msg = iex.getMessage();
        if (StringUtils.hasLength(msg) && msg.contains(MsgUtil.SPLIT_CHAR)) {
            String[] msgLst = msg.split(MsgUtil.SPLIT_CHAR);
            try {
                return ResponseEntity.ok().body(new ResponseDTO<>(ResponseCode.FAILURE, msgLst[1], msgLst[0]));
            } catch (Exception _e) {
                return ResponseEntity.ok().body(new ResponseDTO<>(ResponseCode.FAILURE, msg, msgLst[0]));
            }
        }
        return ResponseEntity.ok().body(new ResponseDTO<>(ResponseCode.FAILURE, iex.getMessage(), ResponseDTO.NG));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            RuntimeException ex,
            WebRequest ignored
    ){
        //Body omitted as it's similar to the method of same name
        // in ProductController example...
        //.....
        log.error(TRACE, ex);
        if (ex instanceof ConstraintViolationException) {
            var fieldErr = ((ConstraintViolationException) ex).getConstraintViolations();
            ResponseDTO<Map<String, String>> res = new ResponseDTO<>();
            res.setStatusCode(ResponseCode.FAILURE);
            Map<String, String> data = new HashMap<>();
            for (var err: fieldErr) {
                var paths = err.getPropertyPath().toString().split("\\.");
                String key = err.getMessageTemplate();
                String msgErr = MsgUtil.getMessageContent(key);
                data.put(paths.length == 2 ? paths[1]: key, "-1".equals(msgErr) ? key : msgErr);
                res.setData(data);
            }
            res.setMessage(MsgUtil.getMessageContent("validate.fields.error"));
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.ok(ResponseDTO.builder().statusCode(ResponseCode.FAILURE).message(ex.getMessage()).build());
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
        return ResponseEntity.ok(ResponseDTO.builder().statusCode(ResponseCode.FAILURE).message(ex.getMessage()).build());
    }

}
