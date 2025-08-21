package com.estudo.gerenciamento.controller;

import com.estudo.gerenciamento.dto.ErroResponse;
import com.estudo.gerenciamento.dto.TarefaDTO;
import com.estudo.gerenciamento.entity.TarefaEntity;
import com.estudo.gerenciamento.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(description = "Realiza criação de tarefas")
    @PostMapping("/criar")
    public ResponseEntity<List<TarefaEntity>> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        try {
            List<TarefaEntity> tarefa = tarefaService.criarTarefa(tarefaDTO);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tarefas");
        }
    }
    @Operation(description = "Realiza remoção de tarefas")
    @PostMapping("/remover/{id}")
    public ResponseEntity<?> removerTarefa(@PathVariable Long id) {
        try {
            log.info("remove tarefa pelo id");
            tarefaService.removerTarefa(id);
            return ResponseEntity.ok("Tarefa removida com sucesso");
        } catch (Exception e) {
            ErroResponse error = new ErroResponse("Erro ao tentar remover tarefa " + e.getMessage());
            return  ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(error);
        }
    }
    @Operation(description = "Consulta tarefas cadastradas")
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

    @Operation(description = "Finaliza tarefa")
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<TarefaEntity> finalizarTarefa(
            @PathVariable Long id) {

        try {
            TarefaEntity tarefa = tarefaService.finalizarTarefa(id);
            return  ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            throw  new RuntimeException("erro ao finalizar tarefa");
        }
    }
}
