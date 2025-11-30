package com.atendimentohospitalar.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prontuario_id")
	private Prontuario prontuario;

	@Enumerated(EnumType.STRING)
	private StatusConsulta status;
	private String nomePaciente;
	private String sintomas;
	private String classificacaoRisco;
	private String pressaoArterial;
	private Float frequenciaCardiaca;
	private Float temperatura;
	private Float saturacao;
	private String diagnosticoMedico;
	private String prescricaoMedica;
	private LocalDateTime dataHoraConsulta;
}
