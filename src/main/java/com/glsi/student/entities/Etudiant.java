package com.glsi.student.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Etudiant {

	@Id
	@GeneratedValue
	@NonNull
	@Column(length = 5)
	private Long matricule;

	@Column
	@NotEmpty
	@Size(min=2,max=30)
	private String nom ;

	@Column
	@NotEmpty
    @Size(min=2,max=30)
	private String prenom;
	
	@Email
	@NotEmpty
	@Column(name = "Email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Classe classes;


}
