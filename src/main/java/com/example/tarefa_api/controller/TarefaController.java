package com.example.tarefa_api.controller;

import com.example.tarefa_api.model.Tarefa;
import com.example.tarefa_api.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

  private final TarefaRepository repo;

  public TarefaController(TarefaRepository repo) {
    this.repo = repo;
  }

  // TESTE 1 - CRIAR
  @PostMapping
  public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa t) {
    Tarefa salva = repo.save(t);
    return ResponseEntity.created(URI.create("/api/tarefas/" + salva.getId()))
                         .body(salva);
  }

  // TESTE 2 - LISTAR
  @GetMapping
  public List<Tarefa> listar() {
    return repo.findAll();
  }

  // TESTE 2 - BUSCAR POR ID
  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
  }

  // TESTE 3 - ATUALIZAR
  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa in) {
    return repo.findById(id).map(ex -> {
      ex.setNome(in.getNome());
      ex.setDataEntrega(in.getDataEntrega());
      ex.setResponsavel(in.getResponsavel());
      return ResponseEntity.ok(repo.save(ex));
    }).orElse(ResponseEntity.notFound().build());
  }

  // TESTE 4 - REMOVER
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
