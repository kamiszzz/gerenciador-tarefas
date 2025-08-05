package com.estudo.gerenciamento.repository;

import com.estudo.gerenciamento.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITarefaRepository extends JpaRepository<TarefaEntity, Long> {
}
