package br.com.fiap.appprodutoteste.produto.service.impl;

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.model.dto.EmailDTO;
import br.com.fiap.appprodutoteste.produto.model.dto.ProdutoDTO;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;
import br.com.fiap.appprodutoteste.produto.service.ProdutoService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoDTO produtoDTO) {
        log.info("Enviando dados do produto para email");

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8082/api/v1/email/send-email";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, String> map = new HashMap<>();
        map.put("emailTo", "brunoberga77@gmail.com");
        map.put("subject", "CADASTRO DE PRODUTO");
        map.put("text", "O produto: " + produtoDTO.getNome() + " foi cadastrado com sucesso na base de dados.\nMais Informacoes:" +
                "\nPRODUTO: \n\nNOME: " + produtoDTO.getNome() + "\nQUANTIDADE: " + produtoDTO.getQuantidade() + "\nVALOR: " + produtoDTO.getValor());

        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<EmailDTO> response = restTemplate.postForEntity(url, request, EmailDTO.class);

        EmailDTO emailDTO2 = response.getBody();

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
