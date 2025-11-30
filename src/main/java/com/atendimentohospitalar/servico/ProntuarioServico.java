package com.atendimentohospitalar.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atendimentohospitalar.dto.ProntuarioDTO;
import com.atendimentohospitalar.entidades.Prontuario;
import com.atendimentohospitalar.repositorios.ProntuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProntuarioServico {

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	public Prontuario criarProntuario(ProntuarioDTO dto) {
		Optional.of(dto.getCpf()).filter(cpf -> !prontuarioRepository.existsByCpf(cpf))
				.orElseThrow(() -> new RuntimeException("CPF já cadastrado!"));

		Prontuario prontuario = new Prontuario();

		prontuario.setNome(dto.getNome());
		prontuario.setCpf(dto.getCpf());
		prontuario.setTelefone(dto.getTelefone());
		prontuario.setDataNascimento(dto.getDataNascimento());

		return prontuarioRepository.save(prontuario);
	}

	public Prontuario atualizarProntuario(Long id, Prontuario dto) {
		Prontuario prontuario = prontuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

		if (dto.getNome() != null)
			prontuario.setNome(dto.getNome());
		if (dto.getCpf() != null)
			prontuario.setCpf(dto.getCpf());
		if (dto.getTelefone() != null)
			prontuario.setTelefone(dto.getTelefone());
		if (dto.getDataNascimento() != null)
			prontuario.setDataNascimento(dto.getDataNascimento());

		return prontuarioRepository.save(prontuario);
	}

	public List<Prontuario> listarTodosProntuarios() {
		return Optional.ofNullable(prontuarioRepository.findAll()).filter(lista -> !lista.isEmpty())
				.orElseThrow(() -> new RuntimeException("Nenhum prontuário encontrado"));
	}

	public Prontuario buscarPorId(Long id) {
		return Optional.ofNullable(prontuarioRepository.findById(id).orElse(null))
				.orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
	}

	public Prontuario buscarPorCpf(String cpf) {
		return Optional.ofNullable(
				prontuarioRepository.findAll().stream().filter(p -> p.getCpf().equals(cpf)).findFirst().orElse(null))
				.orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
	}
}
