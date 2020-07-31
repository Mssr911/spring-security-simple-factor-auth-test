package com.test.springsecuritysimplefactorauthtest;

import com.test.springsecuritysimplefactorauthtest.security.User;
import com.test.springsecuritysimplefactorauthtest.security.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan
public class Start {

    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        User admin = new User();
        admin.setLogin("Marian");
        admin.setPassword(passwordEncoder.encode("Broda"));
        admin.setRole("ADMIN");
        userRepository.save(admin);

        User user = new User();
        admin.setLogin("Dariusz");
        admin.setPassword(passwordEncoder.encode("Popa"));
        admin.setRole("USER");
        userRepository.save(user);
    }


}
