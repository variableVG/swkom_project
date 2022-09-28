package at.fhtw.swen3.services;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-27T18:07:47.576Z[GMT]")
public class NotFoundException extends at.fhtw.swen3.services.ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
