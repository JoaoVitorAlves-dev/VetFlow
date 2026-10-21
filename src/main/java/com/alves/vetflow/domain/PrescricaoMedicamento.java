package com.alves.vetflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_prescricao_medicamento")
@AllArgsConstructor
@NoArgsConstructor
public class PrescricaoMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescricao_id", nullable = false)
    private Prescricao prescricao;

//    private Medicamento medicamento;

    @Column(nullable = false)
    private String dosagem;

    @Column(name = "duracao_dias", nullable = false)
    private Integer duracaoDias;

}
