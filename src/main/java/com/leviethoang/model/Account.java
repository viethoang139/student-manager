package com.leviethoang.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "username should not be null")
    @NotEmpty(message = "username should not be empty")
    private String username;
    @NotNull(message = "password should not be null")
    @NotEmpty(message = "password should not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean enabled = true;
    private boolean credentialsexpired = false;
    private boolean expired = false;
    private boolean locked = false;


    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "AccountRole",
            joinColumns = @JoinColumn(name = "accountId" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId" , referencedColumnName = "id")
    )
    private Set<Role> roles;

}
