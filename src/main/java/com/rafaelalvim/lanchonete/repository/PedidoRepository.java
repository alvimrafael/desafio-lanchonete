package com.rafaelalvim.lanchonete.repository;

import com.rafaelalvim.lanchonete.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
