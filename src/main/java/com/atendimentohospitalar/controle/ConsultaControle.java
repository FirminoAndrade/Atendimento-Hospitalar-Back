package com.atendimentohospitalar.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atendimentohospitalar.dto.ConsultaDTO;
import com.atendimentohospitalar.entidades.Consulta;
import com.atendimentohospitalar.servico.ConsultaServico;

@RestController
@RequestMapping("/api")
public class ConsultaControle {

	@Autowired
	private ConsultaServico consultaServico;

	@PostMapping("/prontuario/{prontuarioId}/consulta")
	public Consulta criarConsulta(@PathVariable Long prontuarioId, @RequestBody ConsultaDTO dto) {
		dto.setProntuarioId(prontuarioId);
		return consultaServico.criarConsulta(dto);
	}

	@PutMapping("/consulta/{id}")
	public Consulta atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
		return consultaServico.atualizarConsulta(id, dto);
	}

	@GetMapping("/consulta/{id}")
	public Consulta buscarConsulta(@PathVariable Long id) {
		return consultaServico.buscarPorId(id);
	}

	@GetMapping("/prontuarios/{prontuarioId}/consultas")
	public List<Consulta> listarPorProntuario(@PathVariable Long prontuarioId) {
		return consultaServico.listarPorProntuario(prontuarioId);
	}

	@GetMapping("/consultas/triagem")
	public List<Consulta> listarTriagem() {
		return consultaServico.listarTriagem();
	}

	@GetMapping("/consultas/retorno")
	public List<Consulta> listarRetornoMedico() {
		return consultaServico.listarRetornoMedico();
	}

	@GetMapping("/consultas/atendimento")
	public List<Consulta> listarAtendimentoMedico() {
		return consultaServico.listarAtendimentoMedico();
	}

	@GetMapping("/consultas/enfermaria")
	public List<Consulta> listarParaEnfermaria() {
		return consultaServico.listarParaEnfermaria();
	}
	
	@GetMapping("/consultas")
    public List<Consulta> listarTodas() {
        return consultaServico.listarTodas();
    }

}
