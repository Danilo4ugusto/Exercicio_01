package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	//Definindo o construtor da classe
	public RedesController() {
		super();
	}

	/*
	 * Recebe o nome do Sistema Operacional como
	 * parâmetro e, de acordo com o S.O., faz a chamada de configuração de IP e
	 * filtra a saída do processo, retornando um String com o nome do Adaptador
	 * Ethernet e o IPv4 apenas (Não importa o número de adaptadores ethernet, devem
	 * aparecer todos). Os adaptadores que não tiverem IPv4 não devem ser mostrados.
	 */
	
public String identificaSO() {
		
		String os = System.getProperty("os.name");
		
		return os;
	}

	public String ip(String sO) {

		// Variável que irá guardar as informações de configuração
		String ipconfig = " ";

		// Condição que verifica se o sistema informado é "windows"
		if (sO.contains("Windows")) {
			// Utilizamos o try catch para fazer o tratamento de exceções, evitando estouro
			// de memória.
			try {

				// A variável processo receberá os dados do ipconfig através do Runtime
				Process processo = Runtime.getRuntime().exec("ipconfig");

				// A variável "fluxo" receberá o fluxo de bit atrvés da função getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na variável "leitor" o fluxo de dados que
				// foram inseridos na variável fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na variável "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				// Enquanto houver uma linha para ser escrita o comando executará
				while (linha != null) {

					// Caso seja encontrará a palavra "Ethernet:" ou "VirtualBox" o comando escreve
					// a linha
					if (linha.contains("Ethernet:") || linha.contains("VirtualBox") || linha.contains("Windows")) {
						ipconfig += linha + "\n";
					}

					// Caso seja encontrará a palavra "IPv4" o comando escreve os dados encontrados
					// nessa linha
					if (linha.contains("IPv4")) {
						ipconfig += linha + "\n";
					}

					// Escreve a próxima linha na variável linha
					linha = buffer.readLine();
				}

				// Encerrando a transmissão e liberando os espaços de memórias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso não seja possível a execução do try, o catch é acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na variável ipconfig
			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
				}
				ipconfig = e.getMessage();
			}

			// Condição que verifica se o sistema informado é "linux"
		} else if ((sO.contains("Linux"))) {

			// Utilizamos o try catch para fazer o tratamento de exceções, evitando estouro
			// de memória.
			try {

				// A variável processo receberá os dados do ifconfig (linux) através do Runtime
				Process processo = Runtime.getRuntime().exec("ifconfig");

				// A variável "fluxo" receberá o fluxo de bit atrvés da função getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na variável "leitor" o fluxo de dados que
				// foram inseridos na variável fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na variável "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				// Enquanto houver uma linha para ser escreta o comando executará
				while (linha != null) {

					// Caso seja encontrará a palavra "Ethernet:" ou "VirtualBox" o comando escreve
					// a linha
					if (linha.contains("flags:")) {
						ipconfig += linha + "\n";
					}

					// Caso seja encontrará a palavra "IPv4" o comando escreve os dados encontrados
					// nessa linha
					if (linha.contains("netmask")) {
						ipconfig += linha + "\n";
					}

					// Escreve a próxima linha na variável linha
					linha = buffer.readLine();
				}

				// Encerrando a transmissão e liberando os espaços de memórias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso não seja possível a execução do try, o catch é acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na variável ipconfig
			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
				} else {
					ipconfig = e.getMessage();
				}

			}

		} else {
			ipconfig = "Configurações do sistema não encontradas!" + "\n";
		}

		return ipconfig;

	}

	/*
	 * Recebe o nome do Sistema Operacional como
	 * parâmetro e, de acordo com o S.O., faz a chamada de ping com 10 iterações,
	 * filtra a saída, pegando apenas o tempo e dá a saída, em ms, do tempo médio do
	 * ping. (O endereço para ping, pode ser o www.google.com.br)
	 */

	public void ping(String sO) {

		// Inicialização das variáveis
		double media = 0f;
		double soma = 0;
		String time = "";
		String mensagem = " ";

		// Condição que verifica se o sistema informado é "windows"
		if ((sO.contains("Windows"))) {
			// Utilizamos o try catch para fazer o tratamento de exceções, evitando estouro
			// de memória.
			try {

				// A variável processo receberá os dados do PING e traz apenas 10 iterações (-n
				// 10) do sistema
				Process processo = Runtime.getRuntime().exec("PING -n 10 www.google.com.br");

				// A variável "fluxo" receberá o fluxo de bit atrvés da função getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na variável "leitor" o fluxo de dados que
				// foram inseridos na variável fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na variável "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				System.out.println("Aguarde, o sistema está processando as informações...");
				
				System.out.println();

				System.out.println("Verificando iterações do seguinte endereço: www.google.com.br");
				
				System.out.println();
				// Enquanto houver uma linha para ser escreta o comando executará
				while (linha != null) {

					// Caso seja encontrará a palavra "tempo:" o comando escrerá a linha

					if (linha.contains("tempo")) {
						int inicioSub = linha.indexOf("o="); // Obtendo o indice da primeira incidência desta String
						int finalSub = linha.indexOf("ms"); // Obtendo o indice da primeira incidência desta String

						// aqui utilizamos o valor das incidências para gerar a substring, obtendo
						// apenas o valor numérico e convertemos em Inteiro
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}

					// Escreve a próxima linha na variável linha
					linha = buffer.readLine();
				}

				// Realizado o cálculo da média de tempo de PING
				media = soma / 10;
				// Mensagem informando a média de tempo em ms do PING
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();

				// Encerrando a transmissão e liberando os espaços de memórias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso não seja possível a execução do try, o catch é acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na variável mensagem
			} catch (IOException e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}

			}
		} else if ((sO.contains("Linux"))) {
			// Utilizamos o try catch para fazer o tratamento de exceções, evitando estouro
			// de memória.
			try {

				// A variável processo receberá os dados do PING e traz apenas 10 iterações (-n
				// 10) do sistema.
				Process processo = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");

				// A variável "fluxo" receberá o fluxo de bit atrvés da função getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na variável "leitor" o fluxo de dados que
				// foram inseridos na variável fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na variável "linha"
				String linha = buffer.readLine();
				System.out.println();

				System.out.println("Aguarde, o sistema está processando as informações...");
				
				System.out.println();

				System.out.println("Verificando iterações do seguinte endereço: www.google.com.br");
				
				System.out.println();
				// Enquanto houver uma linha para ser escreta o comando executará
				while (linha != null) {

					// Caso seja encontrará a palavra "tempo:" o comando escrerá a linha

					if (linha.contains("seq")) {
						int inicioSub = linha.indexOf("e="); // Obtendo o indice da primeira incidência desta String
						int finalSub = linha.indexOf(" ms"); // Obtendo o indice da primeira incidência desta String

						// aqui utilizamos o valor das incidências para gerar a substring, obtendo
						// apenas o valor numérico e convertemos em Inteiro
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}

					// Escreve a próxima linha na variável linha
					linha = buffer.readLine();
				}

				// Realizado o cálculo da média de tempo de PING
				media = soma / 10;
				// Mensagem informando a média de tempo em ms do PING
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();

				// Encerrando a transmissão e liberando os espaços de memórias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso não seja possível a execução do try, o catch é acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na variável mensagem
			} catch (Exception e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}
			}

		} else {
			System.out.println("Configurações do sistema não encontradas!" + "\n");
		}

	}

}