package com.glsi.student.controllers;

import com.glsi.student.entities.*;
import com.glsi.student.repositories.*;
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
@RequestMapping("/admin/etudiant")
public class EtudiantController {
    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;

    @GetMapping({"","/"})
    public String get(Model model) {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiantRepository.findAll().forEach(etudiants::add);

        List<Classe> classes = new ArrayList<>();
        classeRepository.findAll().forEach(classes::add);

        model.addAttribute("classes", classes);
        model.addAttribute("etudiants", etudiants);
        return "admin/etudiant/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        List<Classe> classes = new ArrayList<>();
        classeRepository.findAll().forEach(classes::add);
        model.addAttribute("classes", classes);
        model.addAttribute("etudiant", new Etudiant());
        return "admin/etudiant/add";
    }

    
    @PostMapping("/add")
    public String add(@Valid Etudiant etudiant, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Classe> classes = new ArrayList<>();
            classeRepository.findAll().forEach(classes::add);
            model.addAttribute("classes", classes);
            return "admin/etudiant/add";
        }
        etudiantRepository.save(etudiant);
        return "redirect:/admin/etudiant";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable long id, Model model) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid etudiant ID: " + id));

        List<Classe> classes = new ArrayList<>();
        classeRepository.findAll().forEach(classes::add);

        model.addAttribute("classes", classes);
        model.addAttribute("etudiant", etudiant);
        return "admin/etudiant/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, @Valid Etudiant etudiant, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Classe> classes = new ArrayList<>();
            classeRepository.findAll().forEach(classes::add);

            model.addAttribute("classes", classes);
            return "/admin/etudiant/update";
        }
        etudiantRepository.save(etudiant);
        return "redirect:/admin/etudiant";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid etudiant ID: "+ id));
        etudiantRepository.delete(etudiant);
        return "redirect:/admin/etudiant";
    }

}
