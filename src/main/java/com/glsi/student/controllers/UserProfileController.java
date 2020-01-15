package com.glsi.student.controllers;

import com.glsi.student.entities.Role;
import com.glsi.student.entities.User;
import com.glsi.student.repositories.RoleRepository;
import com.glsi.student.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")

public class UserProfileController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/profil")
   public String getUserProfile( Model model, Principal principal)
    {
        User user = userRepository.findUserByEmail(principal.getName()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        System.out.println(user);
        return "users/profil/index";
    }

    @PostMapping("/profil/update")
    public String UpdateUser(@Valid User user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                List<Role> roles = new ArrayList<>();
                roleRepository.findAll().forEach(roles::add);

                model.addAttribute("roles", roles);
                return "users/profil/index";
            }
            userRepository.save(user);
            return "redirect:/users/profil";
        }

    }




