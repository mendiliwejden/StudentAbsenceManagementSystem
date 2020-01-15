package com.glsi.student.repositories;

import com.glsi.student.entities.Etudiant;
import com.glsi.student.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

public interface MatiereRepository extends JpaRepository<Matiere,Long> {
    public Optional<Matiere> findById(long id);

    @Override
    List<Matiere> findAll();

    List<Matiere> findMatiereByClasses(String classe);


}
