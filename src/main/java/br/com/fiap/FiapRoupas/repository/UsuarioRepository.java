package br.com.fiap.FiapRoupas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.FiapRoupas.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

	List<Usuario> findByNome(String nome);
}