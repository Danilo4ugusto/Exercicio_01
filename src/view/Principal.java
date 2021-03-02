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

		// In�cio de la�o de repeti��o com switch case, alternativas para o usu�rio.
		while (opc != 9) {

			opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha a op��o desejada: \n 1 - Configura��es do Sistema Operacional \n 2 - M�dia de PING" + "\n"
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
				System.out.println("Op��o Inv�lida!");
				break;

			}
			

		}

		sc.close();

	}

}