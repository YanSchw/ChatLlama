package chatllama.api;

import chatllama.data.entity.SessionToken;
import chatllama.data.service.SessionTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Authorization {

    @GetMapping(value = "/auth/session/isvalid", produces = "application/json")
    public String isvalid(@RequestHeader("Session-Token") String token) {
        SessionToken sessionToken = SessionTokenService.getInstance().getByToken(token);
        return sessionToken != null ? "true" : "false";
    }

}
