package h.assignment.taskh.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WrongInputDataException extends RuntimeException {
    public WrongInputDataException(String message) {
        super(message);
        log.error(message);
    }
}
