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
	 * par�metro e, de acordo com o S.O., faz a chamada de configura��o de IP e
	 * filtra a sa�da do processo, retornando um String com o nome do Adaptador
	 * Ethernet e o IPv4 apenas (N�o importa o n�mero de adaptadores ethernet, devem
	 * aparecer todos). Os adaptadores que n�o tiverem IPv4 n�o devem ser mostrados.
	 */
	
public String identificaSO() {
		
		String os = System.getProperty("os.name");
		
		return os;
	}

	public String ip(String sO) {

		// Vari�vel que ir� guardar as informa��es de configura��o
		String ipconfig = " ";

		// Condi��o que verifica se o sistema informado � "windows"
		if (sO.contains("Windows")) {
			// Utilizamos o try catch para fazer o tratamento de exce��es, evitando estouro
			// de mem�ria.
			try {

				// A vari�vel processo receber� os dados do ipconfig atrav�s do Runtime
				Process processo = Runtime.getRuntime().exec("ipconfig");

				// A vari�vel "fluxo" receber� o fluxo de bit atrv�s da fun��o getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na vari�vel "leitor" o fluxo de dados que
				// foram inseridos na vari�vel fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na vari�vel "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				// Enquanto houver uma linha para ser escrita o comando executar�
				while (linha != null) {

					// Caso seja encontrar� a palavra "Ethernet:" ou "VirtualBox" o comando escreve
					// a linha
					if (linha.contains("Ethernet:") || linha.contains("VirtualBox") || linha.contains("Windows")) {
						ipconfig += linha + "\n";
					}

					// Caso seja encontrar� a palavra "IPv4" o comando escreve os dados encontrados
					// nessa linha
					if (linha.contains("IPv4")) {
						ipconfig += linha + "\n";
					}

					// Escreve a pr�xima linha na vari�vel linha
					linha = buffer.readLine();
				}

				// Encerrando a transmiss�o e liberando os espa�os de mem�rias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso n�o seja poss�vel a execu��o do try, o catch � acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na vari�vel ipconfig
			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diret�rio n�o encontrado! \n"
							+ "Poss�vel causa: Chamada de processo Windows em Linux. \n";
				}
				ipconfig = e.getMessage();
			}

			// Condi��o que verifica se o sistema informado � "linux"
		} else if ((sO.contains("Linux"))) {

			// Utilizamos o try catch para fazer o tratamento de exce��es, evitando estouro
			// de mem�ria.
			try {

				// A vari�vel processo receber� os dados do ifconfig (linux) atrav�s do Runtime
				Process processo = Runtime.getRuntime().exec("ifconfig");

				// A vari�vel "fluxo" receber� o fluxo de bit atrv�s da fun��o getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na vari�vel "leitor" o fluxo de dados que
				// foram inseridos na vari�vel fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na vari�vel "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				// Enquanto houver uma linha para ser escreta o comando executar�
				while (linha != null) {

					// Caso seja encontrar� a palavra "Ethernet:" ou "VirtualBox" o comando escreve
					// a linha
					if (linha.contains("flags:")) {
						ipconfig += linha + "\n";
					}

					// Caso seja encontrar� a palavra "IPv4" o comando escreve os dados encontrados
					// nessa linha
					if (linha.contains("netmask")) {
						ipconfig += linha + "\n";
					}

					// Escreve a pr�xima linha na vari�vel linha
					linha = buffer.readLine();
				}

				// Encerrando a transmiss�o e liberando os espa�os de mem�rias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso n�o seja poss�vel a execu��o do try, o catch � acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na vari�vel ipconfig
			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diret�rio n�o encontrado! \n"
							+ "Poss�vel causa: Chamada de processo Linux em Windows. \n";
				} else {
					ipconfig = e.getMessage();
				}

			}

		} else {
			ipconfig = "Configura��es do sistema n�o encontradas!" + "\n";
		}

		return ipconfig;

	}

	/*
	 * Recebe o nome do Sistema Operacional como
	 * par�metro e, de acordo com o S.O., faz a chamada de ping com 10 itera��es,
	 * filtra a sa�da, pegando apenas o tempo e d� a sa�da, em ms, do tempo m�dio do
	 * ping. (O endere�o para ping, pode ser o www.google.com.br)
	 */

	public void ping(String sO) {

		// Inicializa��o das vari�veis
		double media = 0f;
		double soma = 0;
		String time = "";
		String mensagem = " ";

		// Condi��o que verifica se o sistema informado � "windows"
		if ((sO.contains("Windows"))) {
			// Utilizamos o try catch para fazer o tratamento de exce��es, evitando estouro
			// de mem�ria.
			try {

				// A vari�vel processo receber� os dados do PING e traz apenas 10 itera��es (-n
				// 10) do sistema
				Process processo = Runtime.getRuntime().exec("PING -n 10 www.google.com.br");

				// A vari�vel "fluxo" receber� o fluxo de bit atrv�s da fun��o getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na vari�vel "leitor" o fluxo de dados que
				// foram inseridos na vari�vel fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na vari�vel "linha"
				String linha = buffer.readLine();
				System.out.println(linha);

				System.out.println("Aguarde, o sistema est� processando as informa��es...");
				
				System.out.println();

				System.out.println("Verificando itera��es do seguinte endere�o: www.google.com.br");
				
				System.out.println();
				// Enquanto houver uma linha para ser escreta o comando executar�
				while (linha != null) {

					// Caso seja encontrar� a palavra "tempo:" o comando escrer� a linha

					if (linha.contains("tempo")) {
						int inicioSub = linha.indexOf("o="); // Obtendo o indice da primeira incid�ncia desta String
						int finalSub = linha.indexOf("ms"); // Obtendo o indice da primeira incid�ncia desta String

						// aqui utilizamos o valor das incid�ncias para gerar a substring, obtendo
						// apenas o valor num�rico e convertemos em Inteiro
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}

					// Escreve a pr�xima linha na vari�vel linha
					linha = buffer.readLine();
				}

				// Realizado o c�lculo da m�dia de tempo de PING
				media = soma / 10;
				// Mensagem informando a m�dia de tempo em ms do PING
				System.out.printf("O Tempo m�dio de PING das itera��es solicitadas foi de: %.2fms%n", media);
				System.out.println();

				// Encerrando a transmiss�o e liberando os espa�os de mem�rias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso n�o seja poss�vel a execu��o do try, o catch � acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na vari�vel mensagem
			} catch (IOException e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diret�rio n�o encontrado! \n"
							+ "Poss�vel causa: Chamada de processo Linux em Windows. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}

			}
		} else if ((sO.contains("Linux"))) {
			// Utilizamos o try catch para fazer o tratamento de exce��es, evitando estouro
			// de mem�ria.
			try {

				// A vari�vel processo receber� os dados do PING e traz apenas 10 itera��es (-n
				// 10) do sistema.
				Process processo = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");

				// A vari�vel "fluxo" receber� o fluxo de bit atrv�s da fun��o getInputStream()
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na vari�vel "leitor" o fluxo de dados que
				// foram inseridos na vari�vel fluxo
				InputStreamReader leitor = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(leitor);

				// Escrevendo uma linha na vari�vel "linha"
				String linha = buffer.readLine();
				System.out.println();

				System.out.println("Aguarde, o sistema est� processando as informa��es...");
				
				System.out.println();

				System.out.println("Verificando itera��es do seguinte endere�o: www.google.com.br");
				
				System.out.println();
				// Enquanto houver uma linha para ser escreta o comando executar�
				while (linha != null) {

					// Caso seja encontrar� a palavra "tempo:" o comando escrer� a linha

					if (linha.contains("seq")) {
						int inicioSub = linha.indexOf("e="); // Obtendo o indice da primeira incid�ncia desta String
						int finalSub = linha.indexOf(" ms"); // Obtendo o indice da primeira incid�ncia desta String

						// aqui utilizamos o valor das incid�ncias para gerar a substring, obtendo
						// apenas o valor num�rico e convertemos em Inteiro
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}

					// Escreve a pr�xima linha na vari�vel linha
					linha = buffer.readLine();
				}

				// Realizado o c�lculo da m�dia de tempo de PING
				media = soma / 10;
				// Mensagem informando a m�dia de tempo em ms do PING
				System.out.printf("O Tempo m�dio de PING das itera��es solicitadas foi de: %.2fms%n", media);
				System.out.println();

				// Encerrando a transmiss�o e liberando os espa�os de mem�rias dos processos que
				// foram iniciados.
				buffer.close();
				leitor.close();
				fluxo.close();

				// Caso n�o seja poss�vel a execu��o do try, o catch � acionando e retorna
				// mensagem de erro, sendo essa mensagem inserida na vari�vel mensagem
			} catch (Exception e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diret�rio n�o encontrado! \n"
							+ "Poss�vel causa: Chamada de processo Windows em Linux. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}
			}

		} else {
			System.out.println("Configura��es do sistema n�o encontradas!" + "\n");
		}

	}

}