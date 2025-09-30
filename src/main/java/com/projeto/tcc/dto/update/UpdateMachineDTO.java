package com.projeto.tcc.dto.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateMachineDTO(

        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String name,

        Long sector,

        String status, // pode ser null (OPERANDO, PARADA, etc)

        Float oee, // pode ser null

        Integer throughput, // pode ser null

        LocalDate lastMaintenance, // pode ser null

        String photo, // pode ser null

        @Max(value = 99999, message = "É necessário enviar exatamente 5 números nesse campo")
        @Min(value = 10000, message = "É necessário enviar exatamente 5 números nesse campo")
        Integer serieNumber,

        Long machineModel

) {}
