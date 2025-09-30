package com.projeto.tcc.dto.update;

import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateEmployeeDTO(

        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String name,

        Integer employeeID, // agora pode ser null, sem @NotNull

        Long sector, // pode ser null

        List<String> roles, // pode ser null

        String shift, // pode ser null

        String status, // pode ser null

        String photo, // pode ser null

        Long user, // pode ser null

        Boolean availability // pode ser null

) {
}
