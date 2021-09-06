package br.com.tinnova.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroDeFormularioDto {

	public String campo;
	private String erro;
}
