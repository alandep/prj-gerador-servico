package com.br.gerador.prjgeradorservico.model.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alan.franco
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Dominio extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6978404429462375676L;

	/* Inicio dos atributos de domínio */
	private Integer id;

	private String descricaoDominio;

	private List<Atributo> listaAtributo;

	@Override
	public Dominio clone() {

		/* Inicialmente copiamos todos os atributos de cliente */
		Dominio dominio = new Dominio();
		try {
			dominio = (Dominio) super.clone();

			/*
			 * Caso este objeto possua dependências de outros objetos será necessário
			 * programar o clone para estas dependências logo abaixo.
			 * 
			 * Ex: dominio.setDependencia((Dependencia) dominio.getDependencia().clone())
			 * 
			 * 
			 */
		} catch (CloneNotSupportedException e) {
			super.LOGGER.debug("Ocorreu uma exception ao realizar o clone deste objeto: " + this.toString());
			e.printStackTrace();
		}
		return dominio;
	}

}
