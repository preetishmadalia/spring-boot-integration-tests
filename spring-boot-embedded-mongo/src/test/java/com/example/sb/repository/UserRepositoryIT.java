package com.example.sb.repository;

import com.example.sb.SpringBootEmbeddedMongoApplication;
import com.example.sb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringBootEmbeddedMongoApplication.class)
public class UserRepositoryIT {

    @Autowired private UserRepository userRepository;

    @Test
    public void listAllUsers() {
        Collection<User> users = this.userRepository.findAll();
        log.info("Users, {}", users);
        assertEquals(users.size(), 2);
        assertThat(users.stream().map(u -> u.getFirstName()).collect(Collectors.toList())).isEqualTo(Arrays.asList("John", "James"));
    }

    @Test
    public void addNewUser() {
        User user = User.builder().userId(3).firstName("Jim").lastName("White").emailId("jim.white@example.com").build();
        this.userRepository.save(user);
        assertThat(this.userRepository.findById(3).get().getEmailId()).isEqualTo("jim.white@example.com");
    }

    @Test
    public void updateUser() {
        User user = User.builder().userId(1).firstName("Shaun").lastName("Turner").emailId("shaun.turner@example.com").build();
        this.userRepository.save(user);
        assertThat(this.userRepository.findById(1).get().getFirstName()).isEqualTo("Shaun");
    }

    @Test
    public void deleteUser() {
        this.userRepository.deleteById(1);
        assertThat(this.userRepository.findById(1).isPresent()).isEqualTo(false);
    }
}
