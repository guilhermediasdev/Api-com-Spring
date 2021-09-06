package br.com.tinnova.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tinnova.models.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	List<Veiculo> findAllByMarca(String marca);
	
	List<Veiculo> findAllByAno(Integer ano);
	
	List<Veiculo> findAllByVendido(Boolean status);
	
}
