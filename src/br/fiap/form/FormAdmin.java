package br.fiap.form;
import static javax.swing.JOptionPane.*;

import java.util.List;

import br.fiap.bilhete.BilheteUnico;
import br.fiap.dao.BilheteUnicoDAO;
import br.fiap.dao.UsuarioDAO;
import br.fiap.usuario.Usuario;

import static java.lang.Integer.parseInt;

public class FormAdmin {

	private String bilhetes = "";
	
	public void menuAdmin() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenuAdmin()));
				switch(opcao) {
					case 1:
						emitirBilhete();
						break;
					case 2:
						imprimirBilhete();
						break;
					case 3:
						consultar();
					
				}
			}catch (NumberFormatException e) {
				showMessageDialog(null, "Opção inválida!!!\n" + e);
			}
		}while(opcao != 4);

	}

	private void imprimirBilhete() {
		BilheteUnicoDAO daoBilhete = new BilheteUnicoDAO();
		
		List<BilheteUnico> list = daoBilhete.listAll();
		
		if(list.isEmpty()) {
			showMessageDialog(null, "Nenhum registro encontrado!!");
		} else {
			
			list.forEach((bilhete) -> bilhetes += bilhete + "\n");
			
			showMessageDialog(null, bilhetes);
		}
	}

	private void consultar() {
		BilheteUnicoDAO daoBilhete = new BilheteUnicoDAO();
		String cpf = showInputDialog("CPF do usuário");
		BilheteUnico bilhete = daoBilhete.pesquisarCpf(cpf);
		
		if(bilhete == null) {
			showMessageDialog(null, "CPF não cadastrado!!!");
		} else {
			showMessageDialog(null, bilhete);
		}
	}

	private void emitirBilhete() {
		UsuarioDAO daoUsuario = new UsuarioDAO();
		BilheteUnicoDAO daoBilhete = new BilheteUnicoDAO();
		String cpf, nome, tipo;
		String[] opcao = {"Estudande", "Professor(a)", "Normal"};
		
		cpf = showInputDialog("Digite seu CPF");
		if(daoUsuario.pesquisarCpf(cpf)) {
			showMessageDialog(null, "CPF cadastrado.");
		}else {
			nome = showInputDialog("Nome do usuário");
			tipo = (String) showInputDialog(null, "Tipo de bilhete", "Tipo de bilhete", 0, null, opcao, opcao[2]);
			daoUsuario.inserir(new Usuario(nome, cpf, tipo));
			daoBilhete.inserirBilhete(new BilheteUnico(cpf));
		}
		
		
	}

	private String gerarMenuAdmin() {
		String menu = "Escolha uma operação:\n";
		menu += "1. Emitir Bilhete\n";
		menu += "2. Imprimir Bilhete\n";
		menu += "3. Consultar Bilhete\n";
		menu += "4. Sair";
		return menu;
				
	}
	
}
