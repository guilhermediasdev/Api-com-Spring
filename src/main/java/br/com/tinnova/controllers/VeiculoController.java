package br.com.tinnova.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.models.Veiculo;
import br.com.tinnova.models.dto.VeiculoRequestDto;
import br.com.tinnova.services.VeiculoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public ResponseEntity<List<Veiculo>> listarTodosVeiculos() {
		return new ResponseEntity<>(veiculoService.listarTodosVeiculo(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Veiculo>consultarVeiculoPeloID(@PathVariable Long id) {
		Veiculo veiculo =  veiculoService.consultarVeiculoPeloID(id);
		if(veiculo != null)
			return new ResponseEntity<>(veiculo, HttpStatus.OK);
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path = "/filter")
	public ResponseEntity<List<Veiculo>> consultarVeiculosPeloFilter( 
			Boolean vendido,  
			Integer ano, 
			String marca
			) {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		if(vendido != null ) {
			veiculos = veiculoService.consultarVeiculosPeloVendido(vendido);
		} else if(ano != null) {
			veiculos = veiculoService.consultarVeiculosPeloAno(ano);
		} else if(marca != null) {
			veiculos = veiculoService.consultarVeiculosPelaMarca(marca);
		}
		return new ResponseEntity<>(veiculos, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid VeiculoRequestDto veiculo) {
		Veiculo veiculoCadastrado = veiculoService.cadastrar(veiculo); 
		return new ResponseEntity<>(veiculoCadastrado, HttpStatus.CREATED);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> atualizarVeiculo(@RequestBody @Valid VeiculoRequestDto veiculo) {
		Veiculo veiculoCadastrado = veiculoService.atualizar(veiculo); 
		if(veiculoCadastrado!= null)
			return new ResponseEntity<>(veiculoCadastrado, HttpStatus.OK);
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> atualizarStatusVendidoVeiculo(@PathVariable Long id, @RequestParam Boolean status) {
		veiculoService.atualizarStatusVendido(id, status); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> apagarVeiculo(@PathVariable 	Long id) {
		Veiculo veiculo =  veiculoService.consultarVeiculoPeloID(id);
		if(veiculo != null) {
			veiculoService.deletar(id); 
			return new ResponseEntity<>(veiculo, HttpStatus.OK);			
		}
		return ResponseEntity.notFound().build();
	}
	

}
