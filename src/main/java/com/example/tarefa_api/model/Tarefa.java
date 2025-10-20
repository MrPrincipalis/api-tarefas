package com.example.tarefa_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;



@Entity
@Table(name = "TAREFA")
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false, length = 80)
  private String nome;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @Column(name = "data_entrega", nullable = false)
  private LocalDate dataEntrega;



  @NotBlank
  @Column(nullable = false, length = 80)
  private String responsavel;

  // Getters e Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public LocalDate getDataEntrega() { return dataEntrega; }
  public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
  public String getResponsavel() { return responsavel; }
  public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
