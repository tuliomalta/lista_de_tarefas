package com.tuliomalta.lista_tarefas.repository;

import com.tuliomalta.lista_tarefas.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByNome(String nome);

    Tarefa findByOrdemApresentacao(int ordemApresentacao);

    List<Tarefa> findAllByOrderByOrdemApresentacaoAsc();
}

