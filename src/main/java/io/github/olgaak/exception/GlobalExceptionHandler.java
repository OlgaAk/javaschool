package io.github.olgaak.exception;

import io.github.olgaak.aspect.LogAspect;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger
            = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({PassengerAlreadyExistsOnTrainException.class})
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        logger.warn(ex.getMessage());
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
        logger.warn(ex.getMessage());
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
       logger.warn(ex.getMessage());
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<Object>(body, headers, status);
    }

    @ExceptionHandler(NestedServletException.class)
    public String handleServerSideError(HttpServletRequest request, Exception e) {
        logger.warn(e.getMessage());
        logger.error(e.getStackTrace()[0].getFileName() + " " + e.getStackTrace()[0].getMethodName() + " " +  e.getStackTrace()[0].getLineNumber());
        return "500";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(HttpServletRequest request, Exception e) {
        logger.error(e.getLocalizedMessage());
        logger.error(e.getCause().getLocalizedMessage());
        logger.error(e.getClass().getName());
        logger.error(e.getStackTrace()[0].getFileName() + " " + e.getStackTrace()[0].getMethodName() + " " +  e.getStackTrace()[0].getLineNumber());
        return "500";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointer(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e.getCause());
        logger.error(e.getStackTrace()[0].getFileName() + " " + e.getStackTrace()[0].getMethodName() + " " +  e.getStackTrace()[0].getLineNumber());
        return "500";
    }

    @ExceptionHandler(LazyInitializationException.class)
    public String handleLazyInitializationException(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e.getCause());
        logger.error(e.getStackTrace()[0].getFileName() + " " + e.getStackTrace()[0].getMethodName() + " " +  e.getStackTrace()[0].getLineNumber());
        return "500";
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    public ModelAndView handleActionNotAllowedException(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e.getCause());
        logger.error(e.getStackTrace()[0].getFileName() + " " + e.getStackTrace()[0].getMethodName() + " " +  e.getStackTrace()[0].getLineNumber());
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", e.getMessage());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("403");
        return mav;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e) {
        logger.warn(e.getMessage());
        return "404";
    }

    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    public String handleMethodNotAllowed(HttpServletRequest request, Exception e) {
        logger.warn(e.getMessage());
        return "405";
    }
}

