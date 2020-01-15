package com.glsi.student.repositories;

import com.glsi.student.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

}

