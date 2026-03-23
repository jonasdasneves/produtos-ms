package br.com.fiap.produtos_ms.entities;

import br.com.fiap.produtos_ms.enums.CategoriaEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity(name = "PRODUTO")
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 10, max = 150, message = "O nome do produto deve ter entre 10 e 150 caracteres")
    private String nome;

    @Length(min = 10, max = 150, message = "A descrição do produto deve ter entre 10 e 150 caracteres")
    private String descricao;

    @Digits(fraction = 2, integer = 6, message = "O preço do produto deve ter até 8 digitos com 2 dígitos após a vírgula")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço não pode ser negativo")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoriaEnum;

    public Produto(String nome, String descricao, BigDecimal preco, CategoriaEnum categoriaEnum) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaEnum = categoriaEnum;
    }
}
