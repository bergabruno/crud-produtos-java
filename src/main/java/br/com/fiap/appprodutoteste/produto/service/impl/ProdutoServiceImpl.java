package br.com.fiap.appprodutoteste.produto.service.impl;

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;
import br.com.fiap.appprodutoteste.produto.service.ProdutoService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoDTO produtoDTO) {
        log.info("Iniciando cadastro de produto");

        ModelMapper modelMapper = new ModelMapper();
        Produto produto = new Produto();
        modelMapper.map(produtoDTO, produto);


        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getAllProducts() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(Long id) {
        if(produtoRepository.existsById(id)){
            return produtoRepository.findById(id).get();
        }

        throw new RuntimeException("Nao foi encontrado nenhum produto com este ID");

    }

    @Override
    public void deleteById(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Nao foi encontrado nenhum produto com este ID");
        }

        produtoRepository.deleteById(id);

    }

    @Override
    public Produto update(Long id, ProdutoDTO produtoDTO) {
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Nao foi encontrado nenhum produto com este ID");
        }

        Produto produto = findById(id);

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(produtoDTO, produto);

        return produto;
    }
}
