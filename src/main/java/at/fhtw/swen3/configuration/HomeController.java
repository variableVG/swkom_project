package at.fhtw.swen3.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home redirection to OpenAPI api documentation
 */
@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        log.info("From HomeController");
        return "redirect:swagger-ui.html";
    }

}