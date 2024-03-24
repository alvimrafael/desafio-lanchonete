package com.rafaelalvim.lanchonete.repository;

import com.rafaelalvim.lanchonete.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByProdutoId(Long id);
}
