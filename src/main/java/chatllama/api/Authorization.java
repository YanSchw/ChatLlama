package chatllama.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Authorization {

    @GetMapping(value = "/auth/session/isvalid", produces = "application/json")
    public String isvalid(@RequestHeader("Session-Token") String sessionToken) {
        return "{}";
    }

}
