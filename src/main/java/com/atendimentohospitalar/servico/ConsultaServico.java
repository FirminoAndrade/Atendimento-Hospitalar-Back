package com.atendimentohospitalar.servico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atendimentohospitalar.dto.ConsultaDTO;
import com.atendimentohospitalar.entidades.Consulta;
import com.atendimentohospitalar.entidades.Prontuario;
import com.atendimentohospitalar.entidades.StatusConsulta;
import com.atendimentohospitalar.repositorios.ConsultaRepository;
import com.atendimentohospitalar.repositorios.ProntuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConsultaServico {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	public Consulta criarConsulta(ConsultaDTO dto) {

		Prontuario prontuario = prontuarioRepository.findById(dto.getProntuarioId())
				.orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

		Consulta consulta = new Consulta();

		consulta.setNomePaciente(prontuario.getNome());
		consulta.setProntuario(prontuario);
		consulta.setStatus(StatusConsulta.AGUARDANDO_TRIAGEM);
		consulta.setDataHoraConsulta(LocalDateTime.now());

		return consultaRepository.save(consulta);
	}

	public Consulta buscarPorId(Long id) {
		return consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
	}

	public Consulta atualizarConsulta(Long consultaId, ConsultaDTO dto) {
		Consulta consulta = consultaRepository.findById(consultaId)
				.orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

		if (dto.getSintomas() != null)
			consulta.setSintomas(dto.getSintomas());
		if (dto.getClassificacaoRisco() != null)
			consulta.setClassificacaoRisco(dto.getClassificacaoRisco());
		if (dto.getPressaoArterial() != null)
			consulta.setPressaoArterial(dto.getPressaoArterial());
		if (dto.getFrequenciaCardiaca() != null)
			consulta.setFrequenciaCardiaca(dto.getFrequenciaCardiaca());
		if (dto.getTemperatura() != null)
			consulta.setTemperatura(dto.getTemperatura());
		if (dto.getSaturacao() != null)
			consulta.setSaturacao(dto.getSaturacao());
		if (dto.getDiagnosticoMedico() != null)
			consulta.setDiagnosticoMedico(dto.getDiagnosticoMedico());
		if (dto.getPrescricaoMedica() != null)
			consulta.setPrescricaoMedica(dto.getPrescricaoMedica());
		if (dto.getStatus() != null)
			consulta.setStatus(dto.getStatus());
		if (dto.getDataHoraConsulta() != null)
			consulta.setDataHoraConsulta(dto.getDataHoraConsulta());

		return consultaRepository.save(consulta);
	}

	public List<Consulta> listarPorProntuario(Long prontuarioId) {
		Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
				.orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

		return Optional.ofNullable(consultaRepository.findByProntuario(prontuario)).filter(lista -> !lista.isEmpty())
				.orElseThrow(() -> new RuntimeException("Nenhuma consulta encontrada para este prontuário"));
	}

	public List<Consulta> listarTriagem() {
		return Optional.ofNullable(consultaRepository.findByStatus(StatusConsulta.AGUARDANDO_TRIAGEM))
				.filter(lista -> !lista.isEmpty())
				.orElseThrow(() -> new RuntimeException("Nenhuma consulta encontrada com status AGUARDANDO TRIAGEM"));
	}

	public List<Consulta> listarRetornoMedico() {
		return Optional.ofNullable(consultaRepository.findByStatus(StatusConsulta.RETORNO_MEDICO))
				.filter(lista -> !lista.isEmpty())
				.orElseThrow(() -> new RuntimeException("Nenhuma consulta encontrada com status RETORNO MEDICO"));
	}

	public List<Consulta> listarAtendimentoMedico() {
		return Optional.ofNullable(consultaRepository.findByStatus(StatusConsulta.AGUARDANDO_ATENDIMENTO))
				.filter(lista -> !lista.isEmpty()).orElseThrow(
						() -> new RuntimeException("Nenhuma consulta encontrada com status AGUARDANDO ATENDIMENTO"));
	}

	public List<Consulta> listarParaEnfermaria() {
		return Optional.ofNullable(consultaRepository.findByStatus(StatusConsulta.NECESSITA_MEDICACAO))
				.filter(lista -> !lista.isEmpty())
				.orElseThrow(() -> new RuntimeException("Nenhuma consulta encontrada com status NECESSITA MEDICACAO"));
	}
}
