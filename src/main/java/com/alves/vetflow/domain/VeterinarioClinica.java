package com.alves.vetflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tb_veterinario_clinica")
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarioClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Usuario veterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @Column(nullable = false, name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(nullable = false, name = "carga_horaria")
    private Instant cargaHoraria;

}
