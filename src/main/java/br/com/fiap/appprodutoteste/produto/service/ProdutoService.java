package br.com.fiap.appprodutoteste.produto.service;

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {


    Produto salvar(ProdutoDTO produtoDTO);

    List<Produto> getAllProducts();



}
