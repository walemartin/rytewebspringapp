package com.rytesoft.rytewebspringapp.service;

import com.rytesoft.rytewebspringapp.model.User;
import com.rytesoft.rytewebspringapp.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(int id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsersOrderedByUsername() {
        return userRepository.findAllByOrderByUsernameAsc();
    }

    public List<User> searchUsersByUsername(String searchTerm) {
        return userRepository.findAllByUsernameContainingIgnoreCase(searchTerm);
    }
}

