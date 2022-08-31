package br.fiap.form;
import static javax.swing.JOptionPane.*;

import br.fiap.dao.UsuarioDAO;

import static java.lang.Integer.parseInt;

public class FormPrincipal {

	
	public void menuPrincipal() {
		String opcao = null;
		
		do {
			opcao = showInputDialog("Digite sua senha ou CPF ou Sair");
			if(opcao.equalsIgnoreCase("admin")) {
				new FormAdmin().menuAdmin();
			}else if(!(opcao.equalsIgnoreCase("admin"))) {
				if(new UsuarioDAO().pesquisarCpf(opcao)) {
					new FormUsuario().menuUsuario(opcao);
				}
			}
		} while(!opcao.equalsIgnoreCase("sair"));		
	}
}
