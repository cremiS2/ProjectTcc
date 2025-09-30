package com.projeto.tcc.dto.update;

import jakarta.validation.constraints.Size;

public record UpdateMachineModelDTO(
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String modelName,

        @Size(max = 300, message = "Campo excede o tamanho máximo de 300 caracteres")
        String modelDescription
) {
}
