package com.glsi.student.entities;

import com.glsi.student.validators.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roles")
@FieldMatch(first = "password", second = "confirmPassword", message = "Les champs mot de passe et confirmer mot de passe doivent étre identiques")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Le champs prénom est obligatoire")
    private String firstName;
    @NotEmpty(message = "Le champs prénom est obligatoire")
    private String lastName;
    @Email(message = "Adresse email invalide")
//    @UniqueEmail(message = "Adresse email existe")
    @NotEmpty(message = "Le champs d'adresse email est obligatoire")
    private String email;
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractére")
    private String password;
    private String confirmPassword;
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @NotEmpty(message = "Le champs role est obligatoire")
    private List<Role> roles = new ArrayList<>();

    public User(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
