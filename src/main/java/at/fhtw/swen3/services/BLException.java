package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import lombok.Getter;

@Getter
public class BLException extends Exception {

    private Exception innerException;
    private ErrorEntity errorEntity;

    public BLException(long errorId, String errorMsg, Exception e) {
        this.innerException = e;
        this.errorEntity = ErrorEntity.builder().errorMessage(errorMsg).id(errorId).build();

    }

    @Override
    public String getMessage() {
        return errorEntity.getErrorMessage();
    }
}
