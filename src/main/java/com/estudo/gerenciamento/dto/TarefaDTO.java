package com.estudo.gerenciamento.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDate prazo;
    private LocalDate dataFinal;
}
