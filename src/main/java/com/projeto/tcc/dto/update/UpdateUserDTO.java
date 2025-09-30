package com.projeto.tcc.dto.update
        ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        Long id, // pode ser usado para validação ou ignorado no update

        @Size(max = 100, message = "Excedeu a quantidade permitida de caracteres")
        String username,

        @Email(message = "Preencha em formato de e-mail")
        String email,

        String password
) { }
