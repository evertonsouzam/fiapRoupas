package br.com.fiap.FiapRoupas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.FiapRoupas.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{

	List<Produto> findByDescricao(String nome);
}
