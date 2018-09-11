package com.br.gerador.prjservicosgerados.model.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alan.franco
 * 
 *         Objeto de domínico criado para tratar/serealizar as exceções da API
 *         de serviço
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class RetornoException extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7474184251288165802L;

	private int codigo;

	private String status;

	private String mensagem;

}
