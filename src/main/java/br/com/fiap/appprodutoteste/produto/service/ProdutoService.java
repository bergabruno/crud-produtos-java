package br.com.fiap.appprodutoteste.produto.service;

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {


    Produto salvar(ProdutoDTO produtoDTO);

    List<Produto> getAllProducts();

    Produto findById(Long id);

    void deleteById(Long id);

    Produto update(Long id,ProdutoDTO produtoDTO);

}
