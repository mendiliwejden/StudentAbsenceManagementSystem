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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/admin/absence")
@Controller
public class AbsenceController {
	
	@Autowired
	private EtudiantRepository etudRepo;
	@Autowired
	private MatiereRepository matRepo;
	@Autowired
	private AbsenceService absenceService;
	
	List<Etudiant> etudiants;
	List<Matiere> matieres;
	List<Absence> absences;

	@GetMapping({"", "/"})
	public String gererAbsence(Model model) {
		etudiants=(List<Etudiant>) etudRepo.findAll();
		matieres=(List<Matiere>) matRepo.findAll();
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("matieres", matieres);
		//model.addAttribute("absences", absences);
		return "admin/absence/GererAbsence";
	}
	
	@GetMapping("/{matricule}/{Id}")
	public String marquerLAbsence(Model model, @PathVariable(name="matricule")Long matricule
			, @PathVariable(name="Id")Long Id) {
		Matiere x = matRepo.findById(Id).get();
		Etudiant k = etudRepo.findById(matricule).get();
		Absence ss = absenceService.findAbsenceByIdMat(Id, matricule);
		
		if (ss.getId()!=null)
			
		{   ss.setNbrAbs(ss.getNbrAbs()+1.5);
			absenceService.save(ss);
		}
		else

		{	Absence absence=new Absence();
			absence.setEtudiant(k);
			absence.setMatiere(x);
			absence.setNbrAbs(1.5);
			absenceService.save(absence);
		}
		
		etudiants= (List<Etudiant>) etudRepo.findAll();
		matieres= (List<Matiere>) matRepo.findAll();
		
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("matieres", matieres);
		
		return "redirect:/admin/absence";
	}

}




