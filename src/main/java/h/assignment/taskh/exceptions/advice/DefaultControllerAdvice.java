package h.assignment.taskh.exceptions.advice;

import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.exceptions.dto.ResponseDto;
import h.assignment.taskh.exceptions.dto.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler({
            ConstraintViolationException.class,
            WrongInputDataException.class
    })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseDto defaultExceptionHandler(ConstraintViolationException exception) {
        ResponseDto error = new ResponseDto();
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));

        }
        log.error(exception.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseDto error = new ResponseDto();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
            error.setFieldName(fieldError.getField());
            error.setMessage(Objects.requireNonNull(fieldError.getDefaultMessage()));
        }
        log.error(e.getMessage());
        return error;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseDto defaultExceptionHandler(ResourceNotFoundException exception) {
        return getResponse(exception);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto defaultExceptionHandler(RuntimeException exception) {
        return getResponse(exception);
    }

    private ResponseDto getResponse(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseDto(exception.getMessage());
    }
}
