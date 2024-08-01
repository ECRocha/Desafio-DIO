package com.ederson.deposito.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.deposito.DTO.ProdutoDTO;
import com.ederson.deposito.Facade.ProdutoFacade;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoFacade produtoFacade;

    @Operation(summary = "Adicionar um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoFacade.adicionarProduto(produtoDTO);
        return ResponseEntity.ok(novoProduto);
    }

    @Operation(summary = "Buscar um produto pelo ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id) {
        Optional<ProdutoDTO> produtoDTO = produtoFacade.buscarProduto(id);
        return produtoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um produto pelo ID")
    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoAtualizado = produtoFacade.atualizarProduto(produtoDTO);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar um produto pelo ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoFacade.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

