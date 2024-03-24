package com.rafaelalvim.lanchonete.dto;

import com.rafaelalvim.lanchonete.entity.Pedido;
import com.rafaelalvim.lanchonete.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CriarItemPedidoDTO {
    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
