package com.test.springsecuritysimplefactorauthtest;

import com.test.springsecuritysimplefactorauthtest.security.User;
import com.test.springsecuritysimplefactorauthtest.security.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User admin = new User();
        admin.setUsername("Marian");
        admin.setPassword(passwordEncoder.encode("Broda"));
        admin.setRole("ROLE_ADMIN");
        userRepository.save(admin);

        User user = new User();
        user.setUsername("Dariusz");
        user.setPassword(passwordEncoder.encode("Popa"));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }


}
