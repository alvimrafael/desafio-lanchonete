package com.rafaelalvim.lanchonete.dto;

import com.rafaelalvim.lanchonete.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private ProdutoDTO produto;
    private int quantidade;

    public ItemPedidoDTO(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.produto = new ProdutoDTO(
                itemPedido.getProduto().getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getProduto().getPreco()
        );
        this.quantidade = itemPedido.getQuantidade();
    }
}
