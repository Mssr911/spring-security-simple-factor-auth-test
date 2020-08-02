package com.test.springsecuritysimplefactorauthtest.controller;

import com.test.springsecuritysimplefactorauthtest.model.Token;
import com.test.springsecuritysimplefactorauthtest.repository.TokenRepository;
import com.test.springsecuritysimplefactorauthtest.repository.UserRepository;
import com.test.springsecuritysimplefactorauthtest.service.UserService;
import com.test.springsecuritysimplefactorauthtest.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    private UserService userService;
    private TokenRepository tokenRepository;
    private UserRepository userRepository;

    public UserController(UserService userService, TokenRepository tokenRepository, UserRepository userRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
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
        userService.saveUserWithEncodedPassword(user);
        return "successfully-registered";
    }

    @GetMapping("/sign-up")
    public String siqnup(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/token")
    public String authorize(@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value);
        User user = byValue.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "account-confirmed";
    }
}
