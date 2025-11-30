package com.atendimentohospitalar.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atendimentohospitalar.entidades.Consulta;
import com.atendimentohospitalar.entidades.Prontuario;
import com.atendimentohospitalar.entidades.StatusConsulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	List<Consulta> findByProntuario(Prontuario prontuario);
    List<Consulta> findByStatus(StatusConsulta status);

}
