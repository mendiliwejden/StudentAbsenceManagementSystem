package com.glsi.student.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Column(name="Label", length = 30, unique = true)
	private String Label;
	
	@Column
	@Size(min=2,max=30)
	private String nomComplet;

	@OneToMany(mappedBy = "classes")
	@JsonIgnore
	List<Etudiant> etudiants;

	@ManyToMany(mappedBy = "classes")
	@JsonIgnore
	private List<Matiere> matieres;
	
}
