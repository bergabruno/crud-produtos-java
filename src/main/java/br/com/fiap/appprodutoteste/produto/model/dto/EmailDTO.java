package br.com.fiap.appprodutoteste.produto.model.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmailDTO {

    private String id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private String sendDateEmail;
    private String statusEmail;

}
