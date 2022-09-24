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

import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;

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

        produtoRepository.save(produto);
        String sucesso =  enviarEmail(produto);
        if(sucesso.equalsIgnoreCase("SEND")){
            log.info("Email enviado com sucesso!");
        }

        return produto;
    }

    @Override
    public List<Produto> getAllProducts() {
        return produtoRepository.findAll();
    }


    private String enviarEmail(Produto produto) {
        log.info("Enviando dados do produto para email");

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8082/send-email";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, String> map = new HashMap<>();
        map.put("emailTo", "brunoberga77@gmail.com");
        map.put("subject", "CADASTRO DE PRODUTO");
        map.put("text", "O produto: " + produto.getNome() + " foi cadastrado com sucesso na base de dados.\nMais Informacoes:" +
                "\nPRODUTO: \n\nNOME: " + produto.getNome() + "\nQUANTIDADE: " + produto.getQuantidade() + "\nVALOR: " + produto.getValor());

        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<EmailDTO> response = restTemplate.postForEntity(url, request, EmailDTO.class);

        EmailDTO emailDTO2 = response.getBody();
        return emailDTO2.getStatusEmail();

//        String uri = "send-email";
//        String url = "http://localhost:8082/";
//        EmailDTO emailDTO = new EmailDTO();
//        emailDTO.setEmailTo("brunoberga77@gmail.com");
//        emailDTO.setSubject("CADASTRO DE PRODUTOS");
//        emailDTO.setText("TESTE");
//
//        Mono<EmailDTO> emailDTOMono = WebClient
//                .create("url")
//                .post()
//                .uri(uri)
//                .body(BodyInserters.fromValue(emailDTO))
//                .retrieve()
//                .bodyToMono(EmailDTO.class);
    }
}
