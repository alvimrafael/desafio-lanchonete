package com.rafaelalvim.lanchonete.repository;

import com.rafaelalvim.lanchonete.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
