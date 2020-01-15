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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/users")
public class ManageUsersController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping({"","/"})
    public String getUsers(Model model) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        model.addAttribute("users", users);
        return "admin/users/index";
    }

    @GetMapping("/add")
    public String addUserView(Model model) {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "admin/users/add";
    }


    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Role> roles = new ArrayList<>();
            roleRepository.findAll().forEach(roles::add);
            model.addAttribute("roles", roles);
            return "admin/users/add";
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/update")
    public String updateUserView(@PathVariable long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + id));

        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);

        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "admin/users/update";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable long id, @Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Role> roles = new ArrayList<>();
            roleRepository.findAll().forEach(roles::add);

            model.addAttribute("roles", roles);
            return "/admin/users/update";
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: "+ id));
        userRepository.delete(user);
        return "redirect:/admin/users";
    }
}
