package br.com.fiap.FiapRoupas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FiapRoupas.component.UsuarioComponent;
import br.com.fiap.FiapRoupas.model.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	public UsuarioComponent usuarioComponet;

	@GetMapping("/buscar/{nome}")
	public Usuario buscar(@PathVariable(value= "nome") String nome) {
		System.out.println("############");
		System.out.println("buscar:" + nome);
		System.out.println("############");
		
		return usuarioComponet.buscarUsuario(nome);
		
	}
	
	@PostMapping("/salvar")
	public void salvar(@RequestBody Usuario usuario) {
		usuarioComponet.salvar(usuario);
	}
}
