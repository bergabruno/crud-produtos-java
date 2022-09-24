package br.com.fiap.appprodutoteste.produto.model.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    @NotBlank
    @NotNull
    private String nome;

    @NotNull(message = "quantidade tem que ser inserida")
    private Integer quantidade;

    @NotNull
    @DecimalMin(value="0.0")
    private BigDecimal valor;
}
