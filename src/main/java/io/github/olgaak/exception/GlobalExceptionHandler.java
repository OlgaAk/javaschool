package io.github.olgaak.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PassengerAlreadyExistsOnTrainException.class})
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof PassengerAlreadyExistsOnTrainException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            PassengerAlreadyExistsOnTrainException paeot = (PassengerAlreadyExistsOnTrainException) ex;

            return handlePassengerAlreadyExistsOnTrainException(paeot, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    protected ResponseEntity<Object> handlePassengerAlreadyExistsOnTrainException(PassengerAlreadyExistsOnTrainException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<Object>(body, headers, status);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e) {
        return "404";
    }
}

