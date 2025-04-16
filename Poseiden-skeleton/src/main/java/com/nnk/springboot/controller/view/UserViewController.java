package com.nnk.springboot.controller.view;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Afficher la liste des utilisateurs
    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    // Afficher le formulaire d’ajout
    @GetMapping("/add")
    public String showAddForm(User user) {
        return "user/add";
    }

    // Enregistrer un nouvel utilisateur
    @PostMapping("/validate")
    public String validateUser(@Valid User user, BindingResult result, Model model) {
        // ✅ On vérifie les erreurs AVANT d’encoder le mot de passe
        if (result.hasErrors()) {
            return "user/add";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/user/list";
    }

    // Afficher le formulaire de mise à jour
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword(""); // vider le champ mot de passe pour éviter d'afficher le hash
        model.addAttribute("user", user);
        return "user/update";
    }

    // Enregistrer la mise à jour
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result) {
        // ✅ Vérifier les erreurs avant d’encoder
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/user/list";
    }

    // Supprimer un utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}

