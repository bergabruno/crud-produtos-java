package br.com.fiap.appprodutoteste.produto.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;
import br.com.fiap.appprodutoteste.produto.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.appprodutoteste.produto.model.Produto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {

		List<Produto> produtos = produtoService.getAllProducts();

		return ResponseEntity.ok(produtos);
	}

	@PostMapping("/criar")
	public ResponseEntity<Produto> insert(@Valid @RequestBody ProdutoDTO produto) {
		Produto newProduct = produtoService.salvar(produto);

		return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
	}
}
