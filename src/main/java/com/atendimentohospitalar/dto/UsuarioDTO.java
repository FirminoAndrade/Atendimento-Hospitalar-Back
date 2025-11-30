package com.atendimentohospitalar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String funcao;
	private String senha;

	public UsuarioDTO(Long id, String nome, String funcao) {
		this.id = id;
		this.nome = nome;
		this.funcao = funcao;
	}
}
