package com.sistema.cadastro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sistema.cadastro.model.Cliente;
import com.sistema.cadastro.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Cliente> getByCodigo(@PathVariable String codigo) {
		return ResponseEntity.ok(clienteRepository.findByCodigoIgnoreCase(codigo));
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome) {
		List<Cliente> listCliente = clienteRepository.findAllByNomeContainingIgnoreCaseOrderByNome(nome);
		if (listCliente.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listCliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> postCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteDoBanco = clienteRepository.findByCodigoIgnoreCase(cliente.getCodigo());
		if (clienteDoBanco != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JÃ¡ existe um codigo com este nome");
		}
		if (clienteRepository.existsById(cliente.getMunicipio().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NÃ£o existe este municipio");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> putCliente(@PathVariable Long id, @Valid @RequestBody Cliente dados) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente nÃ£o existe");
		}
		Cliente clienteCodigoBanco = clienteRepository.findByCodigoIgnoreCase(dados.getCodigo());
		if (clienteCodigoBanco != null && clienteCodigoBanco.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CÃ³digo jÃ¡ existe em outro cliente.");
		}
		dados.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(dados));		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMunicipio(@PathVariable Long id) {
		return clienteRepository.findById(id).map(resposta -> {
			clienteRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
