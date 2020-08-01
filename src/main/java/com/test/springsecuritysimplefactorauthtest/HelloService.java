package com.test.springsecuritysimplefactorauthtest;

import com.test.springsecuritysimplefactorauthtest.security.User;
import com.test.springsecuritysimplefactorauthtest.security.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public HelloService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUserWithEncodedPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
