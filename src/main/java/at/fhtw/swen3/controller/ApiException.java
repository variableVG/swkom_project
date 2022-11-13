package at.fhtw.swen3.controller;

import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-27T18:07:47.576Z[GMT]")
@Slf4j
public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        log.info("This message is from class ApiException {}", msg);
        this.code = code;
    }
}
