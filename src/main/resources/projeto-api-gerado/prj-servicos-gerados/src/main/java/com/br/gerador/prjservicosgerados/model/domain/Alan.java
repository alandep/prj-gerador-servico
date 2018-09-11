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
@Table(name = "tb_alan")
public class Alan extends BaseDomain {

	private static final long serialVersionUID = -9158814171554795891L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numeroConfsite")
	private String numeroConfsite;

	@Column(name = "nomePacote")
	private String nomePacote;

	@Column(name = "url")
	private String url;

	@Column(name = "status")
	private String status;

	@Column(name = "dtCriacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "registrationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@Column(name = "isNew")
	private Boolean isNew;

	@Column(name = "price", precision = 18, scale = 3)
	private Double price;

}
