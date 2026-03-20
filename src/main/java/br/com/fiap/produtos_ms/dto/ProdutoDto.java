package br.com.fiap.produtos_ms.dto;

public record ProdutoDto
        (String nome,
        String descricao,
        Double preco,
        String categoriaEnum) {

    public ProdutoDto toDto(ProdutoDto produto) {
        return new ProdutoDto(
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.categoriaEnum()
        );
    }

    public ProdutoDto fromDto(ProdutoDto produto) {
        return new ProdutoDto(
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.categoriaEnum()
        );
    }
}
