package com.estudo.gerenciamento.service;

import com.estudo.gerenciamento.dto.TarefaDTO;
import com.estudo.gerenciamento.entity.TarefaEntity;
import com.estudo.gerenciamento.repository.ITarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final ITarefaRepository tarefaRepository;

    public TarefaService(ITarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaEntity criarTarefa(TarefaDTO tarefaDTO) {
        TarefaEntity tarefa = new TarefaEntity();

        tarefa.setDataFinal(tarefaDTO.getDataFinal());
        tarefa.setPrazo(tarefaDTO.getPrazo());
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setStatus(tarefaDTO.getStatus());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setDataCriacao(LocalDate.now());
        return tarefaRepository.save(tarefa);
    }

    public List<TarefaEntity> listarTarefas(){
        return tarefaRepository.findAll();
    }

    public void finalizarTarefa(TarefaDTO tarefaDTO) {
        TarefaEntity tarefa = tarefaRepository.findById(tarefaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + tarefaDTO.getId()));

        tarefa.setStatus(tarefaDTO.getStatus());
        tarefa.setDataFinal(LocalDate.now());

        tarefaRepository.save(tarefa);
    }
}
