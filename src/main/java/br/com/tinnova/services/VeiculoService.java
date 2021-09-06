package br.com.tinnova.services;

import java.util.List;

import br.com.tinnova.models.Veiculo;
import br.com.tinnova.models.dto.VeiculoRequestDto;

public interface VeiculoService {
	
	List<Veiculo> listarTodosVeiculo();
	
	Veiculo consultarVeiculoPeloID(Long id);
	
	List<Veiculo> consultarVeiculosPelaMarca(String marca);
	
	List<Veiculo> consultarVeiculosPeloAno(Integer ano);
	
	List<Veiculo> consultarVeiculosPeloVendido(Boolean status);
	
	Veiculo cadastrar(VeiculoRequestDto veiculo);
	
	Veiculo atualizar(VeiculoRequestDto veiculo);
	
	Veiculo atualizarStatusVendido(Long id, Boolean status);
	
	void deletar(Long id);
}
