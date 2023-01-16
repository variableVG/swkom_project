package at.fhtw.swen3.persistence;

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