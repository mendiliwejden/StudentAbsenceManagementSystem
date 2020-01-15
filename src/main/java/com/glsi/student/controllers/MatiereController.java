package com.glsi.student.controllers;

import com.glsi.student.entities.Matiere;
import com.glsi.student.repositories.MatiereRepository;
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
@RequestMapping("/admin/matiere")
public class MatiereController {
    private final MatiereRepository matiereRepository;

    @GetMapping({"", "/"})
    public String get(Model model) {
        List<Matiere> matieres = new ArrayList<>();
        matiereRepository.findAll().forEach(matieres::add);
        model.addAttribute("matieres", matieres);
        return "admin/matiere/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "admin/matiere/add";
    }

    @PostMapping("/add")
    public String add(@Valid Matiere matiere, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/matiere/add";
        }
        matiereRepository.save(matiere);
        return "redirect:/admin/matiere";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable long id, Model model) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Matiere ID: " + id));

        model.addAttribute("matiere", matiere);
        return "admin/matiere/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, @Valid Matiere matiere, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/admin/matiere/update";
        }
        matiereRepository.save(matiere);
        return "redirect:/admin/matiere";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid matiere ID: "+ id));
        matiereRepository.delete(matiere);
        return "redirect:/admin/matiere";
    }
}
