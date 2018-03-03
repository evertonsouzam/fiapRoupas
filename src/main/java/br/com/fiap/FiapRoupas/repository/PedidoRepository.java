package br.com.fiap.FiapRoupas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.FiapRoupas.model.Pedido;

@Repository
public interface PedidoRepository  extends MongoRepository<Pedido, String>{

	List<Pedido> findByCoo(String coo);
}
