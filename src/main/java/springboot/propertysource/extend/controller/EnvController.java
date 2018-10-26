package springboot.propertysource.extend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link Environment} {@link Controller}
 *
 * @author shijw823
 * @since 2018/10/26
 */
@RestController
public class EnvController {
    @Autowired
    private Environment env;

    @GetMapping("/getProperty")
    public String getProperty(String name) {
        return env.getProperty(name, "not found");
    }
}
