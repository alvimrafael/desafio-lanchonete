package com.rafaelalvim.lanchonete.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rafaelalvim.lanchonete.entity.Pedido;
import com.rafaelalvim.lanchonete.entity.Produto;
import com.rafaelalvim.lanchonete.entity.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private BigDecimal valorTotal;
    private StatusPedido status;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("valorTotal")
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    @JsonProperty("status")
    public StatusPedido getStatus() {
        return status;
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus();
    }
}
