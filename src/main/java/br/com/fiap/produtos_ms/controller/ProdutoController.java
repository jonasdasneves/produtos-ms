package br.com.fiap.produtos_ms.controller;

import br.com.fiap.produtos_ms.dto.ProdutoDto;
import br.com.fiap.produtos_ms.entities.Produto;
import br.com.fiap.produtos_ms.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class ProdutoController extends CommonController{

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/produtos";
    }

    @GetMapping("/produtos")
    public String listAll(Model model){
        List<Produto> produtos = service.findAll();

        if (produtos.isEmpty()){
            return "produtos-vazio";
        }
        else{
            model.addAttribute("produtos", produtos);
            return "produtos";
        }
    }

    @GetMapping("/produtos/novo")
    public String telaCadastro(Model model) {

        model.addAttribute("produto", new ProdutoDto());

        return "produto-form";
    }

    @GetMapping("/produtos/editar/{id}")
    public String telaEdicao(Model model, @PathVariable Long id) {
        Produto produto = service.findById(id);

        if (produto != null) {
            model.addAttribute("produto", produto);

            return "produto-form";
        }
        else{
            ProdutoDto novoProduto = new ProdutoDto(id);
            model.addAttribute("produto", novoProduto);

            return "produto-form";
        }
    }

    @GetMapping("/produtos/{id}")
    public String findProdutoById(Model model, @PathVariable Long id) {

        Produto produto = service.findById(id);

        if (produto != null) {
            model.addAttribute("produto", produto);

            return "produto-detalhes";
        }
        else{
            ProdutoDto novoProduto = new ProdutoDto(id);
            model.addAttribute("produto", novoProduto);

            return "produto-form";
        }
    }

    @DeleteMapping("/produtos/{id}")
    public String deleteProdutoById(@PathVariable Long id) {

        this.service.delete(id);

        return "redirect:/produtos";
    }

    @PostMapping("/produtos/save")
    public String save(Model model, @ModelAttribute ProdutoDto produto){

        Produto novoProduto = produto.toEntity(produto);

        this.service.save(novoProduto);

        return "redirect:/";
    }

    @GetMapping("403")
    public String error403(){
        return "403";
    }
}
