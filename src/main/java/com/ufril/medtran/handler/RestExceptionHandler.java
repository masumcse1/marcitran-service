package com.ufril.medtran.handler;

import com.ufril.medtran.dto.common.Error;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.common.ValidationError;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.exception.BadRequestException;
import com.ufril.medtran.exception.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author moin
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = Logger.getLogger(RestExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setDetail(rnfe.getMessage());
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage(rnfe.getClass().getName());
        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequestException(BadRequestException bre, HttpServletRequest request) {
        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setDetail(bre.getMessage());
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage(bre.getClass().getName());

        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public ResponseEntity<?> handleException(MultipartException mpe, HttpServletRequest request) {
        logger.debug(request);
        Error error = new Error();
        error.setStatus(HttpStatus.PAYLOAD_TOO_LARGE.value());
        error.setTitle(HttpStatus.PAYLOAD_TOO_LARGE.getReasonPhrase());
        error.setDetail(mpe.getMessage());
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage(mpe.getClass().getName());
        logger.debug("FileSizeLimitExceededException");
        logger.error(error);

        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), null, HttpStatus.PAYLOAD_TOO_LARGE);
    }

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
//    @ResponseBody
//    protected ResponseEntity<?> handleMaxUploadSizeExceededException(
//            final HttpServletRequest request, final HttpServletResponse response, final Throwable e) throws IOException {
//        HttpStatus status = getStatus(request);
//        logger.warn(e.getMessage());
//        Error error = new Error();
//        error.setStatus(status.value());
//        error.setTitle(status.getReasonPhrase());
//        error.setDetail("Max file size exceeded");
//        error.setTimeStamp(new Date().getTime());
//        error.setDeveloperMessage("Max file size exceeded");
//        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), null, status);
//    }

//    @ExceptionHandler(MultipartException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    protected ResponseEntity<?> handleGenericMultipartException(
//            final HttpServletRequest request, final HttpServletResponse response, final Throwable e) throws IOException {
//        HttpStatus status = getStatus(request);
//        logger.warn(e.getMessage());
//        Throwable rootCause = e;
//        Throwable cause = e.getCause();
//        while (cause != null && !cause.equals(rootCause)) {
//            rootCause = cause;
//            cause = cause.getCause();
//        }
//
//        Error error = new Error();
//        error.setStatus(status.value());
//        error.setTitle(status.getReasonPhrase());
//        error.setDetail(rootCause.getMessage());
//        error.setTimeStamp(new Date().getTime());
//        error.setDeveloperMessage(rootCause.getMessage());
//        logger.debug("Multipart Exception Handler");
//        logger.error(rootCause.getMessage());
//        logger.error(error);
//        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), null, status);
//    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error();
        error.setStatus(status.value());
        error.setTitle(status.getReasonPhrase());
        error.setDetail(ex.getMessage());
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage(ex.getClass().getName());

        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error();
        error.setTimeStamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle("Validation Failed");
        error.setDetail("Input validation failed");
        error.setDeveloperMessage(ex.getClass().getName());

        List<FieldError> fieldErrors =  ex.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors) {
            logger.debug("FieldError : " + fe.toString());
            logger.debug("Errors : " + error.getErrors().toString());
            List<ValidationError> validationErrorList = error.getErrors();
            if(validationErrorList == null) {
                validationErrorList = new ArrayList<>();
            }
            ValidationError validationError = new ValidationError(fe.getField(), fe.getCode(), messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }

        return new ResponseEntity<>(new Response(StatusType.ERROR, null, error), headers, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
