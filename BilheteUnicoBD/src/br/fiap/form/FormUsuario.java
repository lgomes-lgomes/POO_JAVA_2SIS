package br.fiap.form;
import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

import br.fiap.dao.BilheteUnicoDAO;

public class FormUsuario {

	public void menuUsuario(String cpf) {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenuUsuario()));
				
				switch (opcao) {
				case 1: 
					this.carregarBilhete(cpf);
					break;
				case 2:
					this.passarCatraca(cpf);
					break;
				case 3:
					this.consultarSaldo(cpf);
					break;
				case 4:
					break;
				default:
					showMessageDialog(null, "opção inválida");
				}
				
			}catch (NumberFormatException e) {
				// TODO: handle exception
				showMessageDialog(null, "Opção inválida!!!" + e);
			}
		}while(opcao != 4);
	}

	private String gerarMenuUsuario() {
		String menu = "Escolha uma operação:\n";
		menu += "1. Carregar Bilhete\n";
		menu += "2. Passar na Catraca\n";
		menu += "3. Consultar Saldo\n";
		menu += "4. Sair";
		return menu;
	}
	
	private void carregarBilhete(String cpf) {
		double valor = Double.parseDouble(showInputDialog("informe o valor desejado"));
		
		BilheteUnicoDAO daoBilhete = new BilheteUnicoDAO();
		
		daoBilhete.carregarBilhete(cpf, valor);
		
		showMessageDialog(null, daoBilhete.pesquisarCpf(cpf));
	}
	
	private void passarCatraca(String cpf) {
		BilheteUnicoDAO daoBilhete=  new BilheteUnicoDAO();
		
		daoBilhete.passarCatraca(cpf);
		showMessageDialog(null, daoBilhete.pesquisarCpf(cpf));
	}
	
	private void consultarSaldo(String cpf) {
		BilheteUnicoDAO daoBilhete = new BilheteUnicoDAO();
		
		showMessageDialog(null, daoBilhete);
	}

}
