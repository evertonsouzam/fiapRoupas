package br.com.fiap.FiapRoupas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FiapRoupas.component.PedidoComponent;
import br.com.fiap.FiapRoupas.model.Pedido;
@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	
	@Autowired
	public PedidoComponent pedidoComponet;
	
	@GetMapping("/buscar/{id}")
	public Pedido buscar(@PathVariable(value= "id") String id) {
		System.out.println("############");
		System.out.println("buscar:" + id);
		System.out.println("############");
		
		return pedidoComponet.buscarPedido(id);
		
	}
	
	@PostMapping("/salvar")
	public void salvar(@RequestBody Pedido pedido) {
		pedidoComponet.salvar(pedido);
	}
	
	
	@GetMapping("/buscarTodos")
	public List<Pedido> buscar() {
		System.out.println("############");
		System.out.println("buscar Todos" );
		System.out.println("############");
		
		return pedidoComponet.buscarPedidos();
		
	}
	
	@GetMapping("/gerarNota/{coo}")
	public boolean gerarNota(@PathVariable(value= "coo") String coo) {
		
		return pedidoComponet.gerarNota(coo);
			
	}
	
	
//	@GetMapping("/criarPedido")
//	public void criarPedido() {
//		
//		pedidoComponet.criarPedido();
//		
//		
//	}
	
	
	
	
}
