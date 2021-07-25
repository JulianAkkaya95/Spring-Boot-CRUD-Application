package com.example.springbootcrudapplication.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the user", example = "foo")
    String firstName;

    @Schema(description = "First name of the user", example = "bar")
    String name;

    @Schema(description = "Email of the user", example = "foo@bar.eu")
    String email;
}
