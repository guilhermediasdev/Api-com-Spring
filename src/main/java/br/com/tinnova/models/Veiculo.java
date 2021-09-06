package br.com.tinnova.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "TB_VEICULO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

	@Id
	@Column(name = "ID_VEICULO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NM_VEICULO")
	@NonNull
	private String nome;

	@Column(name = "NM_MARCA")
	@NonNull
	private String marca;

	@Column(name = "DT_ANO")
	@NonNull
	private Integer ano;

	@Column(name = "DSC_VEICULO")
	@NonNull
	private String descricao;

	@Column(name = "ST_VEICULO")
	private Boolean vendido = Boolean.FALSE;

	@Column(name = "DT_CREATED")
	private LocalDate created = LocalDate.now();

	@Column(name = "DT_UPDATED")
	private LocalDate updated;

}
