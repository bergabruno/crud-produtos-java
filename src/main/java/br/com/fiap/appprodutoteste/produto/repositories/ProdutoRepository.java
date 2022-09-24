package br.com.fiap.appprodutoteste.produto.repositories;


import br.com.fiap.appprodutoteste.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
