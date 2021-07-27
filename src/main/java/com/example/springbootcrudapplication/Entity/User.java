package com.example.springbootcrudapplication.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the user", example = "David")
    @NotBlank(message = "First name must be set")
    String firstName;

    @Schema(description = "Name must be set", example = "Bowie")
    @NotBlank
    String name;

    @Schema(description = "Email of the user", example = "dw@example.com")
    @Email
    @NotBlank
    String email;
}
