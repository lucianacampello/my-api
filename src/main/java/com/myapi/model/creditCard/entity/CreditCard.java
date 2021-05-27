package com.myapi.model.creditCard.entity;

import com.myapi.model.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard implements Serializable {
    private static final long serialVersionUID = -554406069662543663L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 14, max = 16)
    @Column(nullable = false, unique = true)
    private String number;

    @NotNull
    @Column(nullable = false)
    private LocalDate expirationDate;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
