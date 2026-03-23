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

@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoriaEnum;

    public Produto toEntity(ProdutoDto produto) {
        return new Produto(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoriaEnum()
        );
    }

    public ProdutoDto toDto(Produto produto) {
        return new ProdutoDto(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoriaEnum()
        );
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public CategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
        this.categoriaEnum = categoriaEnum;
    }
}
