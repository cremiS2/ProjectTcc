package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.dto.mappers.MachineMapper;
import com.projeto.tcc.dto.exit.MachineResultDTO;
import com.projeto.tcc.service.MachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("machine")
@RequiredArgsConstructor
public class MachineController implements GenericController{

    private final MachineService machineService;
    private final MachineMapper mapper;

    @PostMapping
    public ResponseEntity<Void> saveMachine(@RequestBody @Valid MachineDTO machineDTO) {
        var entidade = machineService.saveMachine(machineDTO);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MachineResultDTO> verPorId(@PathVariable("id") Long idMaquina){
        return ResponseEntity.ok(machineService.getMaquinaId(idMaquina));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarMaquina(@PathVariable Long id, @RequestBody MachineDTO dto){
        machineService.updateMaquina(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  deletarMaquina(@PathVariable Long id){
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<MachineResultDTO>> pesquisa(
            @RequestParam(value = "machine-name", required = false)
            String name,
            @RequestParam(value = "sector-name", required = false)
            String nameSector,
            @RequestParam(value = "status-machine", required = false)
            String statusMachine,
            @RequestParam(value = "page-number", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize
    ){

        var maquinasPesquisa = machineService.search(
                name,
                statusMachine,
                nameSector,
                pageNumber,
                pageSize
        ).map(mapper::toDTO);

        return ResponseEntity.ok().body(maquinasPesquisa);
    }

}
