package br.com.fiap.FiapRoupas.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.FiapRoupas.model.Produto;
import br.com.fiap.FiapRoupas.repository.ProdutoRepository;

@Component
public class ProdutoComponent {
	
	@Autowired
	ProdutoRepository produtoRepository;

	
	public void salvar(Produto produto) {
		List<Produto> produtos= produtoRepository.findByDescricao(produto.getDescricao());
		if(!produtos.isEmpty()) {
			produto.setId(produtos.get(0).getId());
		}
		produtoRepository.save(produto);
	}
}
