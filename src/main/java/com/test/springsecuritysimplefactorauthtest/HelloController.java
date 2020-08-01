package com.test.springsecuritysimplefactorauthtest;

import com.test.springsecuritysimplefactorauthtest.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/index2")
    public String sayHelloToUser(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        return "hello";
    }

    @GetMapping("/for-user")
    public String forUser() {
        return "for-user";
    }

    @GetMapping("/for-admin")
    public String forAdmin() {
        return "for-admin";
    }

    @PostMapping("/register")
    public String register(User user) {
        helloService.saveUserWithEncodedPassword(user);
        return "successfully-registered";
    }

    @GetMapping("/sign-up")
    public String siqnup(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }
}
