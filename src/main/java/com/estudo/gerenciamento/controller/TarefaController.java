package com.estudo.gerenciamento.controller;

import com.estudo.gerenciamento.dto.ErroResponse;
import com.estudo.gerenciamento.dto.TarefaDTO;
import com.estudo.gerenciamento.entity.TarefaEntity;
import com.estudo.gerenciamento.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        try {
            TarefaEntity tarefa = tarefaService.criarTarefa(tarefaDTO);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            ErroResponse error = new ErroResponse("Erro ao tentar criar tarefa " + e.getMessage());
           return  ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(error);
        }


    }

    @GetMapping("/consultarTarefas")
    public ResponseEntity<List<TarefaEntity>> listarTarefas() {
        try {
            List<TarefaEntity> tarefa = tarefaService.listarTarefas();
            if(tarefa.isEmpty()) {
                return ResponseEntity.ofNullable(tarefa);
            } else {
                return ResponseEntity.ok(tarefa);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar tarefas");
        }
    }
}
