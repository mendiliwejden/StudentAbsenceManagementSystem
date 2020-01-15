package com.glsi.student.service;

import com.glsi.student.entities.Absence;
import com.glsi.student.entities.Etudiant;
import com.glsi.student.entities.Matiere;
import com.glsi.student.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceService {

	@Autowired
	private AbsenceRepository abRepo;
	List<Absence> absences;

	//find absence by student and subject
	public Absence findAbsenceByIdMat(Long id,Long Matricule) {
		absences=(List<Absence>) abRepo.findAll();
		List<Absence> r = absences.stream().filter(x->x.getEtudiant().getMatricule().equals(Matricule))
				.collect(Collectors.toList());
		 Absence k = r.stream().filter(x->x.getMatiere().getId().equals(id)).findFirst().get();
		
		return k;
		}


	//Existing of absence
	public boolean existAbsence(Long id,Long Matricule){
		absences=(List<Absence>) abRepo.findAll();
		List<Absence> r = absences.stream().filter(x->x.getEtudiant().getMatricule().equals(Matricule)).collect(Collectors.toList());
		 Absence k = r.stream().filter(x->x.getMatiere().getId().equals(id)).findFirst().get();
		if (k.getEtudiant().getMatricule().equals(Matricule))	
		return true;
		else 
		return false;
		}
	
	//find all absences 
	public List<Absence> findAllAbsences(){
		List<Absence> r =(List<Absence>) abRepo.findAll();
		return r;
	}

	//enregistrer absence
	public void save(Absence absence){
		abRepo.save(absence);
	}
	
	//find absence for one student 
	public double findAbsenceByStudent(Etudiant etudiant) {
		
		List<Absence> r =(List<Absence>) abRepo.findAll();
		return r.stream().filter(x->x.getEtudiant().equals(etudiant)).map(x->x.getNbrAbs()).reduce(0.0d, (x,y) -> x+y);
		
	}
	
	//find absence for one Subject
		public double findAbsenceBySubject(Matiere matiere) {
			List<Absence> r =(List<Absence>) abRepo.findAll();
			return r.stream().filter(x->x.getMatiere().equals(matiere)).map(x->x.getNbrAbs()).reduce(0.0d, (x,y) -> x+y);
		}
	
	
		
}
