package com.glsi.student.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matiere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;

	@Column(name="Label")
	@NotEmpty
	@Size(min=2,max=30, message = "Le champs label doit étre >= 2")
	private String label ;

	@Column
	private int heure;

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "Matiere_Classe",
			joinColumns = @JoinColumn(name = "Matiere_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "classe_id", referencedColumnName = "id"))
	List<Classe> classes;


}
