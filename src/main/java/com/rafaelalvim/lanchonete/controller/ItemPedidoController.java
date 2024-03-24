package com.rafaelalvim.lanchonete.controller;

import com.rafaelalvim.lanchonete.dto.CriarItemPedidoDTO;
import com.rafaelalvim.lanchonete.dto.ItemPedidoDTO;
import com.rafaelalvim.lanchonete.entity.ItemPedido;
import com.rafaelalvim.lanchonete.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> criarItemPedido(@RequestBody CriarItemPedidoDTO criarItemPedidoDTO) {
        ItemPedidoDTO itemPedidoDTO = itemPedidoService.criarItemPedido(criarItemPedidoDTO);
        return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
    }
}
