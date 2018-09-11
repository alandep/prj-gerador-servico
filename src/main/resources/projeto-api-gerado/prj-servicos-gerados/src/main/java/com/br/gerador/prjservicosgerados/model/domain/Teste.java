package com.br.gerador.prjservicosgerados.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "tb_gerador")
public class Teste extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9158814171554795891L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "confsite_number")
	private String numeroConfsite;

	@Column(name = "package_name")
	private String nomePacote;

	private String url;

	private String status;

	@Column(name = "creation_date")
	private Date dtCriacao;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "registration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate; /* Data cadastro, data e hora, não nulo */

	@Column(name = "is_new")
	private Boolean isNew; /* É novo?, boleano, não nulo */

	@Column(name = "price", precision = 18, scale = 3)
	private Double price; /* Preço, decimal positivo, não nulo */

}
