package com.rafaelalvim.lanchonete.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rafaelalvim.lanchonete.entity.Pedido;
import com.rafaelalvim.lanchonete.entity.StatusPedido;

import java.math.BigDecimal;

public class FechamentoDTO {
    private Long id;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private BigDecimal troco;

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

    @JsonProperty("troco")
    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
