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
@RequestMapping("/admin/classe")
public class ClasseController {
    private final ClasseRepository classeRepository;
    private final MatiereRepository matiereRepository;
    private final EtudiantRepository etudiantRepository;

    @GetMapping({"", "/"})
    public String get(Model model) {
        List<Classe> classes = new ArrayList<>();
        classeRepository.findAll().forEach(classes::add);
        model.addAttribute("classes", classes);
        return "admin/classe/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        List<Matiere> matieres = new ArrayList<>();
        matiereRepository.findAll().forEach(matieres::add);
        model.addAttribute("matieres", matieres);
        model.addAttribute("classe", new Classe());
        return "admin/classe/add";
    }

    @PostMapping("/add")
    public String addRoom(@Valid Classe classe, BindingResult result,Model model) {
        if(result.hasErrors()) {
            List<Matiere> matieres = new ArrayList<>();
            matiereRepository.findAll().forEach(matieres::add);
            model.addAttribute("matieres", matieres);
            return "admin/classe/add";
        }
        classeRepository.save(classe);
        return "redirect:/admin/classe";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable long id, Model model) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classe ID: " + id));
        List<Matiere> matieres = new ArrayList<>();
        matiereRepository.findAll().forEach(matieres::add);

        model.addAttribute("matieres", matieres);

        model.addAttribute("classe", classe);
        return "admin/classe/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, @Valid Classe classe, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Matiere> matieres = new ArrayList<>();
            matiereRepository.findAll().forEach(matieres::add);
            return "/admin/classe/update";
        }
        classeRepository.save(classe);
        return "redirect:/admin/classe";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Room ID: "+ id));
        classeRepository.delete(classe);
        return "redirect:/admin/classe";
    }


}
