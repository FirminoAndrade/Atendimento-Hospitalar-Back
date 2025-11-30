package com.atendimentohospitalar.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atendimentohospitalar.dto.UsuarioDTO;
import com.atendimentohospitalar.entidades.Usuario;
import com.atendimentohospitalar.servico.UsuarioServico;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

	@Autowired
	private UsuarioServico usuarioServico;

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam Long id, @RequestParam String senha) {
	    Usuario user = usuarioServico.autenticar(id, senha);
	    if(user == null){
	        return ResponseEntity.status(401).build();
	    }
	    return ResponseEntity.ok(user);
	}
	

	@PostMapping
	public UsuarioDTO cadastrarUsuario(@RequestBody UsuarioDTO dto) {
		return usuarioServico.salvarUsuario(dto);
	}

	@PutMapping("/{id}")
	public UsuarioDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		return usuarioServico.atualizarUsuario(id, dto);
	}
	
	@GetMapping("/{id}")
	public Usuario buscarPorId(@PathVariable Long id) {
	    return usuarioServico.buscarPorId(id);
	}

	@GetMapping
	public List<UsuarioDTO> listarUsuario() {
		return usuarioServico.listarTodosUsuarios();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioServico.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
