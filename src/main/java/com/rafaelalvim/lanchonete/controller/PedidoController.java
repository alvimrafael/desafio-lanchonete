package com.rafaelalvim.lanchonete.controller;

import com.rafaelalvim.lanchonete.dto.*;
import com.rafaelalvim.lanchonete.entity.Pedido;
import com.rafaelalvim.lanchonete.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido() {
        PedidoDTO pedidoDTO = pedidoService.criarPedido();
        return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{idPedido}/adicionar-produto")
    public ResponseEntity<PedidoDTO> adicionarProduto(@PathVariable Long idPedido, @RequestBody AdicionarProdutoDTO adicionarProdutoDTO) {
        PedidoDTO pedidoDTO = pedidoService.adicionarProduto(idPedido, adicionarProdutoDTO);
        return ResponseEntity.ok(pedidoDTO);
    }

    @PostMapping("/{idPedido}/retirar-produto")
    public ResponseEntity<PedidoDTO> retirarProduto(@PathVariable Long idPedido, @RequestBody ProdutoQuantidadeDTO produtoQuantidadeDTO) {
        PedidoDTO pedidoDTO = pedidoService.retirarProduto(idPedido, produtoQuantidadeDTO);
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping("/{idPedido}/calcular-preco-total")
    public ResponseEntity<BigDecimal> calcularPrecoTotal(@PathVariable Long idPedido) {
        BigDecimal precoTotal = pedidoService.calcularPrecoTotal(idPedido);
        return ResponseEntity.ok(precoTotal);
    }

    @PostMapping("/{idPedido}/fechar")
    public ResponseEntity<FechamentoDTO> fecharPedido(@PathVariable Long idPedido, @RequestBody FecharPedidoDTO fecharPedidoDTO) {
        FechamentoDTO fechamentoDTO = pedidoService.fecharPedido(idPedido, fecharPedidoDTO);
        return ResponseEntity.ok(fechamentoDTO);
    }

    @PostMapping("/{idPedido}/calcular-preco-total-por-lista")
    public ResponseEntity<BigDecimal> calcularPrecoTotalPorLista(@PathVariable Long idPedido, @RequestBody List<ProdutoQuantidadeDTO> produtosQuantidade) {
        BigDecimal precoTotal = pedidoService.calcularPrecoTotalPorLista(idPedido, produtosQuantidade);
        return ResponseEntity.ok(precoTotal);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> buscarPedido(@PathVariable Long idPedido) {
        PedidoDTO pedidoDTO = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(pedidoDTO);
    }
}
