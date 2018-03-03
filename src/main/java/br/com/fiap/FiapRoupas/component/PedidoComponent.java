package br.com.fiap.FiapRoupas.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.FiapRoupas.Util.CupomHelper;
import br.com.fiap.FiapRoupas.controller.UsuarioController;
import br.com.fiap.FiapRoupas.model.Pedido;
import br.com.fiap.FiapRoupas.model.Produto;
import br.com.fiap.FiapRoupas.model.Usuario;
import br.com.fiap.FiapRoupas.repository.PedidoRepository;

@Component
public class PedidoComponent {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public void salvar(Pedido pedido) {
		List<Pedido> pedidos  = pedidoRepository.findByCoo(pedido.getCoo());
		if(!pedidos.isEmpty()) {
			pedido.setId(pedidos.get(0).getId());
		}
		pedidoRepository.save(pedido);
	}
	
	public Pedido buscarPedido(String coo) {

		List<Pedido> pedidos = pedidoRepository.findByCoo(coo);
		if(pedidos.isEmpty()) {
			System.out.println("Nao achou o pedido" + coo);
			return new Pedido();
		}else {
			System.out.println("vai retornar o pedido" + coo);
			return pedidos.get(0);
		}
			
	}

	public boolean gerarNota(String coo) {
		
		CupomHelper cupom = new CupomHelper();
		
		if (coo != null && coo == "-1") {
			List <Pedido> pedidos = pedidoRepository.findAll();
			if (!pedidos.isEmpty()) {
				for(Pedido pedido : pedidos) {
					cupom.imprimirCupom(pedido);
				}
				return true;
			}
		}else {
			Pedido pedido = buscarPedido(coo);
			if (pedido != null) {
				cupom.imprimirCupom(buscarPedido(coo));
				return true;
			}
		}
		
		return false;
	}				
	
	public List <Pedido> buscarPedidos(){
		return pedidoRepository.findAll();
	}
	
//	public void criarPedido() {
//		Pedido pedido = new Pedido();
//		pedido.setCoo("1");
//		pedido.setHash("123456");
//		//UsuarioController usuariocontroller = new UsuarioController();
//		Usuario usuario = new Usuario();
//		usuario.setNome("Everton");
//		
//		pedido.setUsuario(usuario);
//		
//		
//		
//		Produto produto = new Produto();
//		produto.setId("1");
//		produto.setDescricao("Feijão");
//		produto.setValor(10.0);
//		pedido.getProdutos().add(produto);
//		produto = new Produto();
//		produto.setId("2");
//		produto.setDescricao("Arroz");
//		produto.setValor(14.0);
//		pedido.getProdutos().add(produto);
//		produto = new Produto();
//		produto.setId("3");
//		produto.setDescricao("Carne");
//		produto.setValor(20.0);
//		pedido.getProdutos().add(produto);
//		
//		salvar(pedido);
//	}
//	
}
