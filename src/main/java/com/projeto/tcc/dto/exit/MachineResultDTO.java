package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomMachineModelDTO;
import com.projeto.tcc.dto.exit.custom.CustomSectorDTO;
import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.enums.StatusMachine;

import java.time.LocalDate;
import java.util.List;

public record MachineResultDTO(
        Long id,
        String name,
        Integer serieNumber,
        CustomMachineModelDTO modelMachine,
        StatusMachine status,
        Float oee,
        String photo,
        Integer throughput,
        LocalDate lastMaintence,
        CustomSectorDTO sector,
        List<AllocatedEmployeeMachineResultDTO> allocatedEmployeeMachine
) {
}
