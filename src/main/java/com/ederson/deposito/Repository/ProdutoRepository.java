package com.ederson.deposito.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.deposito.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}