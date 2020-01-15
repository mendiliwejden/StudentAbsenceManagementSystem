package com.glsi.student.controllers;

import com.glsi.student.entities.Absence;
import com.glsi.student.entities.Etudiant;
import com.glsi.student.entities.Matiere;
import com.glsi.student.repositories.EtudiantRepository;
import com.glsi.student.repositories.MatiereRepository;
import com.glsi.student.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/admin/statistique")
@Controller
public class StatAbsenceController {
	
	@Autowired
	private EtudiantRepository etudRepo;
	@Autowired
	private MatiereRepository matRepo;
	@Autowired
	private AbsenceService service;
	
	List<Etudiant> etudiants;
	List<Matiere> matieres;
	List<Absence> absences;

	@GetMapping({"", "/"})
	public String statAbsence(Model model) {
		absences=service.findAllAbsences();
		Stream<Absence> sp = absences.stream();
		double sum=sp.map(x->x.getNbrAbs()).reduce(0.0d, (x,y) -> x+y);
		
		etudiants=(List<Etudiant>) etudRepo.findAll();
		matieres=(List<Matiere>) matRepo.findAll();
		
		Map<String, Double> studentAbsence = new HashMap<>();
		
		for (Etudiant etudiant:etudiants) {
			studentAbsence.put(etudiant.getPrenom(), service.findAbsenceByStudent(etudiant));
		}
		
		Map<String, Double> MatiereAbsence = new HashMap<>();
		
		for (Matiere matiere:matieres) {
			MatiereAbsence.put(matiere.getLabel(), service.findAbsenceBySubject(matiere));
		}
		model.addAttribute("sum", sum);
		model.addAttribute("studentAbsence", studentAbsence);
		model.addAttribute("MatiereAbsence", MatiereAbsence);
		return "admin/statistique/Absence";
	}
	
}




