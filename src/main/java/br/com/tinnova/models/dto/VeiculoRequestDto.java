package br.com.tinnova.models.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoRequestDto {
	
	private Long id;
	@NotNull @NotEmpty
	private String nome;
	private String descricao;
	@NotNull @NotEmpty
	private String marca;
	@NotNull
	private Integer ano;
	private Boolean vendido = Boolean.FALSE;

}
