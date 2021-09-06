package br.com.tinnova.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tinnova.models.Veiculo;
import br.com.tinnova.models.dto.VeiculoRequestDto;
import br.com.tinnova.repositories.VeiculoRepository;
import br.com.tinnova.services.VeiculoService;

@Service
@Transactional(readOnly = true)
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	public VeiculoRepository veiculoRepository;

	@Override
	public List<Veiculo> listarTodosVeiculo() {
		return veiculoRepository.findAll();
	}

	@Override
	public Veiculo consultarVeiculoPeloID(Long id) {
		return veiculoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Veiculo cadastrar(VeiculoRequestDto veiculo) {
		Veiculo v = new Veiculo();
		v.setNome(veiculo.getNome());
		v.setDescricao(veiculo.getDescricao());
		v.setMarca(veiculo.getMarca());
		v.setAno(veiculo.getAno());
		v.setVendido(veiculo.getVendido());
		return veiculoRepository.save(v);
	}

	@Override
	@Transactional
	public Veiculo atualizar(VeiculoRequestDto veiculo) {
		Veiculo veiculoEncontrado = consultarVeiculoPeloID(veiculo.getId());
		if (veiculoEncontrado != null) {
			veiculoEncontrado.setNome(veiculo.getNome());
			veiculoEncontrado.setDescricao(veiculo.getDescricao());
			veiculoEncontrado.setAno(veiculo.getAno());
			veiculoEncontrado.setMarca(veiculo.getMarca());
			veiculoEncontrado.setVendido(veiculo.getVendido());
			veiculoEncontrado.setUpdated(LocalDate.now());
			return veiculoRepository.save(veiculoEncontrado);
		}
		return veiculoEncontrado;
	}

	@Override
	@Transactional
	public Veiculo atualizarStatusVendido(Long id, Boolean status) {
		Veiculo veiculoEncontrado = consultarVeiculoPeloID(id);
		if (veiculoEncontrado != null) {
			veiculoEncontrado.setVendido(status);
			veiculoEncontrado.setUpdated(LocalDate.now());
		}
		return veiculoRepository.save(veiculoEncontrado);
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		veiculoRepository.deleteById(id);
	}

	@Override
	public List<Veiculo> consultarVeiculosPelaMarca(String marca) {
		return veiculoRepository.findAllByMarca(marca);
	}

	@Override
	public List<Veiculo> consultarVeiculosPeloAno(Integer ano) {
		return veiculoRepository.findAllByAno(ano);
	}

	@Override
	public List<Veiculo> consultarVeiculosPeloVendido(Boolean status) {
		return veiculoRepository.findAllByVendido(status);
	}

}
