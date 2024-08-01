package com.ederson.deposito.Facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ederson.deposito.DTO.ProdutoDTO;
import com.ederson.deposito.Service.ProdutoService;

@Component
public class ProdutoFacade {

    @Autowired
    private ProdutoService produtoService;

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
        return produtoService.adicionarProduto(produtoDTO);
    }

    public Optional<ProdutoDTO> buscarProduto(Long id) {
        return produtoService.buscarProduto(id);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(produtoDTO);
    }

    public void deletarProduto(Long id) {
        produtoService.deletarProduto(id);
    }
}

