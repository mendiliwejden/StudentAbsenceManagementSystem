package com.glsi.student.repositories;

import com.glsi.student.entities.Absence;
import org.springframework.data.repository.CrudRepository;

public interface AbsenceRepository extends CrudRepository<Absence, String> {

}
