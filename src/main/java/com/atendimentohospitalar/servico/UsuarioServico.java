package com.atendimentohospitalar.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.atendimentohospitalar.dto.UsuarioDTO;
import com.atendimentohospitalar.entidades.Usuario;
import com.atendimentohospitalar.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario autenticar(Long id, String senha) {
	    Usuario usuario = repository.findById(id).orElse(null);

	    if (usuario == null) return null;

	    if (passwordEncoder.matches(senha, usuario.getSenha())) {
	        return usuario;
	    }

	    return null;
	}
	
	 public Usuario buscarPorId(Long id) {
	        return repository.findById(id).orElseThrow(
	            () -> new RuntimeException("Usuário não encontrada")
	        );
	    }

	public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		Usuario u = new Usuario();
		u.setNome(dto.getNome());
		u.setFuncao(dto.getFuncao());
		u.setSenha(passwordEncoder.encode(dto.getSenha()));
		Usuario salvo = repository.save(u);
		return new UsuarioDTO(salvo.getId(), salvo.getNome(), salvo.getFuncao());
	}

	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
		Usuario u = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		u.setNome(dto.getNome());
		u.setFuncao(dto.getFuncao());

		if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
			u.setSenha(passwordEncoder.encode(dto.getSenha()));
		}

		Usuario atualizado = repository.save(u);
		return new UsuarioDTO(atualizado.getId(), atualizado.getNome(), atualizado.getFuncao());
	}

	public List<UsuarioDTO> listarTodosUsuarios() {
		return repository.findAll().stream().map(u -> {
			UsuarioDTO dto = new UsuarioDTO();
			dto.setId(u.getId());
			dto.setNome(u.getNome());
			dto.setFuncao(u.getFuncao());
			// não colocar senha no retorno
			return dto;
		}).toList();
	}

	public void deletarUsuario(Long id) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
		repository.deleteById(id);
	}
}
