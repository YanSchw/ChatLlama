package chatllama.api;

import chatllama.data.entity.SessionToken;
import chatllama.data.entity.User;
import chatllama.data.service.SessionTokenService;
import chatllama.data.service.UserService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Authorization {

    @GetMapping(value = "/auth/session/isvalid", produces = "application/json")
    public String isvalid(@RequestHeader("Session-Token") String token) {
        SessionToken sessionToken = SessionTokenService.getInstance().getByToken(token);

        JSONObject json = new JSONObject();
        json.put("isvalid", sessionToken != null);

        return json.toString();
    }

    @GetMapping(value = "/auth/login", produces = "application/json")
    public String login(@RequestHeader("Username") String username, @RequestHeader("Password") String password) {
        User user = UserService.getInstance().getUserByNameAndPassword(username, password);
        JSONObject json = new JSONObject();

        if (user != null) {
            SessionToken sessionToken = SessionToken.createTokenForUser(user);
            json.put("sessionToken", sessionToken);
        } else {
            json.put("sessionToken", "undefined");
        }

        return json.toString();
    }

}
