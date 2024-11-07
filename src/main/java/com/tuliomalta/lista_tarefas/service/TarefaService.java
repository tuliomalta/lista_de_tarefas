package com.tuliomalta.lista_tarefas.service;

import com.tuliomalta.lista_tarefas.domain.Tarefa;
import com.tuliomalta.lista_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodasOrdenadas() {
        return tarefaRepository.findAllByOrderByOrdemApresentacaoAsc();
    }

    @Transactional
    public Tarefa incluirTarefa(Tarefa tarefa) {
        // Adiciona lógica para definir a ordem de apresentação
        int ultimaOrdem = listarTodasOrdenadas().size() + 1;
        tarefa.setOrdemApresentacao(ultimaOrdem);
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public Tarefa editarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getNome().equals(tarefaAtualizada.getNome()) &&
                tarefaRepository.existsByNome(tarefaAtualizada.getNome())) {
            throw new RuntimeException("O nome da tarefa já existe.");
        }
        tarefa.setNome(tarefaAtualizada.getNome());
        tarefa.setCusto(tarefaAtualizada.getCusto());
        tarefa.setDataLimite(tarefaAtualizada.getDataLimite());
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    @Transactional
    public void reordenarTarefa(Long id, int novaOrdem) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        int ordemAtual = tarefa.getOrdemApresentacao();
        if (ordemAtual == novaOrdem) {
            return;
        }

        List<Tarefa> tarefas = listarTodasOrdenadas();
        tarefas.remove(tarefa);

        if (novaOrdem < 1 || novaOrdem > tarefas.size() + 1) {
            throw new RuntimeException("Nova ordem inválida.");
        }

        tarefas.add(novaOrdem - 1, tarefa);

        for (int i = 0; i < tarefas.size(); i++) {
            tarefas.get(i).setOrdemApresentacao(i + 1);
        }

        tarefaRepository.saveAll(tarefas);
    }
}

