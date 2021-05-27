package com.myapi.model.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapi.model.creditCard.entity.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -1809024519662046454L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Size(max = 13)
    private Role role = Role.CLIENT;

    @OneToMany(targetEntity = CreditCard.class, mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CreditCard> creditCards;
}



