package com.rafaelalvim.lanchonete.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ProdutoQuantidadeDTO {
    private Long idProduto;
    private int quantidade;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
