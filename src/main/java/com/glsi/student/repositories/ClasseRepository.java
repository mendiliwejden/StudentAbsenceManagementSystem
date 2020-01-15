package com.glsi.student.repositories;

import com.glsi.student.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe,Long> {

}
