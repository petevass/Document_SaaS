package com.mycompany.app.newgoogldocs.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationForm {

    @NotBlank
    @Size(min=3, max=512)
    String email;
    @NotBlank
    @Size(min=5, max=128,message = "Use at least 5 characters")
    String password;

}
