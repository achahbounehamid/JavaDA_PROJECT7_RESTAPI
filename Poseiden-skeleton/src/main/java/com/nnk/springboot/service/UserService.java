package com.nnk.springboot.service;


import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Ajouter un utilisateur
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Lister tous les utilisateurs
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Trouver un utilisateur par son ID
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    // Supprimer un utilisateur
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}

