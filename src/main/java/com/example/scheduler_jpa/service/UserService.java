package com.example.scheduler_jpa.service;

import com.example.scheduler_jpa.entity.User;
import com.example.scheduler_jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

