package chatllama;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public Resource index() {
        return new ClassPathResource("html/index.html");
    }

    @GetMapping("/css/{style}")
    public Resource css(@PathVariable String style) {
        return new ClassPathResource("css/" + style);
    }

    @GetMapping(value = "/js/{script}", produces = "application/javascript")
    public Resource js(@PathVariable String script) {
        return new ClassPathResource("js/" + script);
    }

}
