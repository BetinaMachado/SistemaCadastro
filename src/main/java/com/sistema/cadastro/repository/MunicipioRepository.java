package com.sistema.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sistema.cadastro.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

	public Municipio findByCodigoIgnoreCase(@Param("codigo") String codigo);
	
	public List<Municipio> findByNomeContainingIgnoreCaseOrderByNome(@Param("nome") String nome);
	
	public List<Municipio> findByUf(@Param("uf") String uf);
	
	public Municipio findByNomeAndUf(String nome, String uf);
		
}
