package com.test.springsecuritysimplefactorauthtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/for-user")
    public String forUser() {
        return "for-user";
    }

    @GetMapping("/for-admin")
    public String forAdmin() {
        return "for-admin";
    }
}
