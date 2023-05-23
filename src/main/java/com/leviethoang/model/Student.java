package com.leviethoang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull(message = "firstname should not be null")
    @NotEmpty(message = "firstname should not be empty")
    private String firstname;
    @NotNull(message = "lastname should not be null")
    @NotEmpty(message = "lastname should not be empty")
    private String lastname;
    @NotNull(message = "address should not be null")
    @NotEmpty(message = "address should not be empty")
    private String address;
    @Email(message = "invalid email")
    private String email;
    @NotNull(message = "dob should not be null")
    @NotEmpty(message = "dob should not be empty")
    private String dob;
}
