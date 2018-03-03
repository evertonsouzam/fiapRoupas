package br.com.fiap.FiapRoupas.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.FiapRoupas.model.Usuario;
import br.com.fiap.FiapRoupas.repository.UsuarioRepository;

@Component
public class UsuarioComponent {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) {
		List<Usuario> usuarios = usuarioRepository.findByNome(usuario.getNome());
		if(!usuarios.isEmpty()) {
			usuario.setId(usuarios.get(0).getId());
		}
		usuarioRepository.save(usuario);
	}
	
	public Usuario buscarUsuario(String nome) {

		List<Usuario> usuarios = usuarioRepository.findByNome(nome);
		if(usuarios.isEmpty()) {
			System.out.println("Nao achou o usuario" + nome);
			return new Usuario();
		}else {
			System.out.println("vai retornar o usuario" + nome);
			return usuarios.get(0);
		}
		
				
	}
		
}