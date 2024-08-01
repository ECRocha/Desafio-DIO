package com.ederson.deposito.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.deposito.DTO.ProdutoDTO;
import com.ederson.deposito.Model.Produto;
import com.ederson.deposito.Repository.ProdutoRepository;

import jakarta.transaction.Transactional;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	private ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO dto = new ProdutoDTO();
		dto.setId(produto.getId());
		dto.setNome(produto.getNome());
		dto.setPreco(produto.getPreco());
		return dto;
	}

	private Produto toEntity(ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setId(dto.getId());
		produto.setNome(dto.getNome());
		produto.setPreco(dto.getPreco());
		return produto;
	}

	@Transactional
	public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
		Produto produto = toEntity(produtoDTO);
		Produto produtoSalvo = produtoRepository.save(produto);
		return toDTO(produtoSalvo);
	}

	public Optional<ProdutoDTO> buscarProduto(Long id) {
		return produtoRepository.findById(id).map(this::toDTO);
	}

	@Transactional
	public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO) {
		if (produtoRepository.existsById(produtoDTO.getId())) {
			Produto produto = toEntity(produtoDTO);
			Produto produtoAtualizado = produtoRepository.save(produto);
			return toDTO(produtoAtualizado);
		}
		return null;
	}

	@Transactional
	public void deletarProduto(Long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
		} else {
			throw new RuntimeException("Produto com ID " + id + " não encontrado.");
		}
	}
}
