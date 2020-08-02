package com.test.springsecuritysimplefactorauthtest.service;

import com.test.springsecuritysimplefactorauthtest.model.Token;
import com.test.springsecuritysimplefactorauthtest.model.User;
import com.test.springsecuritysimplefactorauthtest.repository.TokenRepository;
import com.test.springsecuritysimplefactorauthtest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private MailService mailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void saveUserWithEncodedPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        sendToken(user);
    }

    private void sendToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);
        tokenRepository.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(user.getUsername(), "QuizApp mail confirmation.", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
