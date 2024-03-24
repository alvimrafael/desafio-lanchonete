package com.rafaelalvim.lanchonete.service;

import com.rafaelalvim.lanchonete.dto.CriarItemPedidoDTO;
import com.rafaelalvim.lanchonete.dto.ItemPedidoDTO;
import com.rafaelalvim.lanchonete.entity.ItemPedido;
import com.rafaelalvim.lanchonete.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoDTO criarItemPedido(CriarItemPedidoDTO criarItemPedidoDTO) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(criarItemPedidoDTO.getPedido());
        itemPedido.setProduto(criarItemPedidoDTO.getProduto());
        itemPedido.setQuantidade(criarItemPedidoDTO.getQuantidade());

        itemPedido = itemPedidoRepository.save(itemPedido);
        return new ItemPedidoDTO(itemPedido);
    }
}
