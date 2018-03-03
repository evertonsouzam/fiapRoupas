package br.com.fiap.FiapRoupas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FiapRoupas.component.ProdutoComponent;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	public ProdutoComponent produtoComponet;
}
