package com.sistema.cadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sistema.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		
		public Cliente findByCodigoIgnoreCase(@Param("codigo") String codigo);
		
		public List<Cliente> findAllByNomeContainingIgnoreCaseOrderByNome(@Param("nome") String nome);
		
		public Cliente findByNome(@Param("nome") String nome);
		
		public Optional<Cliente> findByIdAndCodigo(Long id, String codigo);

}
