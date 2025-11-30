package com.atendimentohospitalar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;

}
