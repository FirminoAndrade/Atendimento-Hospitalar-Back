package com.atendimentohospitalar.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atendimentohospitalar.dto.ProntuarioDTO;
import com.atendimentohospitalar.entidades.Prontuario;
import com.atendimentohospitalar.servico.ProntuarioServico;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioControle {

	@Autowired
	private ProntuarioServico prontuarioServico;

	@PostMapping
	public Prontuario criarProntuario(@RequestBody ProntuarioDTO dto) {
		return prontuarioServico.criarProntuario(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Prontuario> atualizarProntuario(@PathVariable Long id, @RequestBody Prontuario prontuario) {
		try {
			Prontuario atualizado = prontuarioServico.atualizarProntuario(id, prontuario);
			return ResponseEntity.ok(atualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<Prontuario> listarTodos() {
		return prontuarioServico.listarTodosProntuarios();
	}

	@GetMapping("/{id}")
	public Prontuario buscarPorId(@PathVariable Long id) {
		return prontuarioServico.buscarPorId(id);
	}

	@GetMapping("/cpf/{cpf}")
	public Prontuario buscarPorCpf(@PathVariable String cpf) {
		return prontuarioServico.buscarPorCpf(cpf);
	}
}
