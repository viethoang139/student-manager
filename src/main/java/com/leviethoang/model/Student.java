package com.leviethoang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "firstname should not be null or empty")
    private String firstname;
    @NotBlank(message = "lastname should not be null or empty")
    private String lastname;
    @NotBlank(message = "address should not be null or empty")
    private String address;
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "dob should not be null or empty")
    private String dob;
}
