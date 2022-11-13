package at.fhtw.swen3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            log.error("class ApiUtil, setExampleResponse {}" ,e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
