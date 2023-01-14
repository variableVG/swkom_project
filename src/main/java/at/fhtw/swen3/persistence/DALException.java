package at.fhtw.swen3.persistence;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import lombok.Getter;

@Getter
public class DALException extends Exception {

    private Exception innerException;
    private ErrorEntity errorEntity;

    public DALException(long errorId, String errorMsg, Exception e) {
        this.innerException = e;
        this.errorEntity = ErrorEntity.builder().errorMessage(errorMsg).id(errorId).build();

    }

    @Override
    public String getMessage() {
        return errorEntity.getErrorMessage();
    }
}