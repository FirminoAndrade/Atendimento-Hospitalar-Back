package com.atendimentohospitalar.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atendimentohospitalar.entidades.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    boolean existsByCpf(String cpf);
}
