package com.example.sb;

import com.example.sb.entity.User;
import com.example.sb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories
public class SpringBootSampleMongoApplication implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleMongoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = User.builder().userId(1).firstName("John").lastName("Doe").emailId("john.doe@example.com").build();
        User user2 = User.builder().userId(2).firstName("James").lastName("Smith").emailId("james.smith@example.com").build();
        this.userRepository.saveAll(Arrays.asList(user1, user2));
    }
}
