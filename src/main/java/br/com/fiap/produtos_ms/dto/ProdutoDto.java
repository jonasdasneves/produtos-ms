package br.com.fiap.produtos_ms.dto;

import br.com.fiap.produtos_ms.entities.Produto;
import br.com.fiap.produtos_ms.enums.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoriaEnum;

    public ProdutoDto(Long id) {
        this.id = id;
    }

    public Produto toEntity(ProdutoDto produto) {
        return new Produto(
                this.getId(),
                this.getNome(),
                this.getDescricao(),
                this.getPreco(),
                this.getCategoriaEnum()
        );
    }

    public ProdutoDto toDto(Produto produto) {
        return new ProdutoDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoriaEnum()
        );
    }
}

