package view;

import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Principal {

	public static void main(String[] args) {

		// Instancia de classe Scanner
		Scanner sc = new Scanner(System.in);

		// Instancia da classe REdesController
		RedesController redes = new RedesController();

		int opc = 0;
		String sO;

		// Início de laço de repetição com switch case, alternativas para o usuário.
		while (opc != 9) {

			opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção desejada: \n 1 - Configurações do Sistema Operacional \n 2 - Média de PING" + "\n"
							+ " 9 - Finalizar Programa"));
			
			

			switch (opc) {

			case 1:
				System.out.println(redes.ip(redes.identificaSO()));
				break;

			case 2:
				redes.ping(redes.identificaSO());
				break;

			case 9:
				System.out.println("Processo Finalizado!");
				break;
				

			default:
				System.out.println("Opção Inválida!");
				break;

			}
			

		}

		sc.close();

	}

}