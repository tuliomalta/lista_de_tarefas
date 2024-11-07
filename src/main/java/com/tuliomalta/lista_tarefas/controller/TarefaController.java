package com.tuliomalta.lista_tarefas.controller;

import com.tuliomalta.lista_tarefas.domain.Tarefa;
import com.tuliomalta.lista_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTodasOrdenadas();
    }

    @PostMapping
    public Tarefa incluirTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.incluirTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa editarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.editarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
    }

    @PutMapping("/{id}/reordenar")
    public void reordenarTarefa(@PathVariable Long id, @RequestParam int novaOrdem) {
        tarefaService.reordenarTarefa(id, novaOrdem);
    }
}

