package com.br.gerador.prjgeradorservico.model.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.factory.GeradorArquivosFactory;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.repository.IDbChangeLogRepositoryFachada;
import com.br.gerador.prjgeradorservico.model.services.fachada.IGeradorServiceFachada;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorService extends BaseService implements IGeradorServiceFachada {

	@Autowired
	private IDbChangeLogRepositoryFachada dbChangeLogRepository;

	private Process childProcess;

	@Override
	public String gerarServico(Dominio dominio) {
		/*
		 * Percorre o a fábrica e solicita a criação de todos os arquivos especificados
		 */
		Integer idChangeLog = dbChangeLogRepository.findMaxIdChangeLog();
		for (GeradorArquivosFactory geradorArquivosFactory : GeradorArquivosFactory.values()) {
			geradorArquivosFactory.setDominio(dominio);
			geradorArquivosFactory.setUltimoIdLiquibase(idChangeLog);
			geradorArquivosFactory.gerar();
		}

		/* Realizar build da nova API */
		realizarBuildAPIGerada();

		/* Executar jar da API gerada que contem os serviços do novo domínio */
		executarAPIGerada();

		return null;
	}

	private void realizarBuildAPIGerada() {
		try {
			File arquivoDeBuild = new File("src/main/resources/projeto-api-gerado/prj-servicos-gerados/build.bat");

			finalizarExecucaoProjetoServicoGerado();
			Process childProcess = Runtime.getRuntime().exec(arquivoDeBuild.getAbsolutePath());
			BufferedReader childOut = new BufferedReader(new InputStreamReader(childProcess.getInputStream()));
			String line;
			while ((line = childOut.readLine()) != null) {
				System.out.println(line);
			}
			childProcess.waitFor();

		} catch (IOException e1) {
			LOGGER.error("Ocorreu um erro interno: IOException >> GeradorService >> realizarBuildAPIGerada()");
		} catch (InterruptedException e) {
			LOGGER.error("Ocorreu um erro interno: InterruptedException >> GeradorService >> realizarBuildAPIGerada()");
		}
	}

	private void executarAPIGerada() {
		try {
			File arquivoDeDeploy = new File("src/main/resources/projeto-api-gerado/prj-servicos-gerados/run.bat");

			childProcess = Runtime.getRuntime().exec(arquivoDeDeploy.getAbsolutePath());
			BufferedReader childOut = new BufferedReader(new InputStreamReader(childProcess.getInputStream()));
			String line;
			while ((line = childOut.readLine()) != null) {
				System.out.println(line);
			}
			childProcess.waitFor();

		} catch (IOException e1) {
			LOGGER.error("Ocorreu um erro interno: IOException >> GeradorService >> executarAPIGerada()");
		} catch (InterruptedException e) {
			LOGGER.error("Ocorreu um erro interno: InterruptedException >> GeradorService >> executarAPIGerada()");
		}
	}

	private void finalizarExecucaoProjetoServicoGerado() {		
		try {			
			Process processoListarPID = Runtime.getRuntime().exec("src/main/resources/projeto-api-gerado/prj-servicos-gerados/kill.bat");
			BufferedReader childOut = new BufferedReader(new InputStreamReader(processoListarPID.getInputStream()));
			String line;
			while ((line = childOut.readLine()) != null) {
				System.out.println(line);
			
				String pids[] = line.split(" ");
				
				Process processoMatarPID = Runtime.getRuntime().exec("taskkill /f /PID "+pids[pids.length-1]+"");
				BufferedReader retornoDoComandoKill = new BufferedReader(new InputStreamReader(processoMatarPID.getInputStream()));
				String lineKill;
				while ((lineKill = retornoDoComandoKill.readLine()) != null) {
					System.out.println(lineKill);
				}
				processoMatarPID.waitFor();
			}
			processoListarPID.waitFor();

		} catch (IOException e1) {
			LOGGER.error("Ocorreu um erro interno: IOException >> GeradorService >> executarAPIGerada()");
		} catch (InterruptedException e) {
			LOGGER.error("Ocorreu um erro interno: InterruptedException >> GeradorService >> executarAPIGerada()");
		}
	}

}
