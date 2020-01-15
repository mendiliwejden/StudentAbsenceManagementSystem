package com.glsi.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Absence_Etudiant_Matiere", //
       uniqueConstraints = { //
      @UniqueConstraint(name = "EtudMat", columnNames = { "Id_Student", "Id_Matiere" }) })
public class Absence {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Student", nullable = false)
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Matiere", nullable = false)
    private Matiere matiere;

    private double nbrAbs=0;

}
