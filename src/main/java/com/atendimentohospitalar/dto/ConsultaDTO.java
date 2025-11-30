package com.atendimentohospitalar.dto;

import java.time.LocalDateTime;

import com.atendimentohospitalar.entidades.StatusConsulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {

	private Long prontuarioId;
	private String nomePaciente;
	private LocalDateTime dataHoraConsulta;
	private String sintomas;
	private String classificacaoRisco;
	private String pressaoArterial;
	private Float frequenciaCardiaca;
	private Float temperatura;
	private Float saturacao;
	private String diagnosticoMedico;
	private String prescricaoMedica;
	private StatusConsulta status;
}