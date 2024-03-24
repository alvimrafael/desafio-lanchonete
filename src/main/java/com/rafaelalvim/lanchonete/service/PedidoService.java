package com.rafaelalvim.lanchonete.service;

import com.rafaelalvim.lanchonete.dto.*;
import com.rafaelalvim.lanchonete.entity.ItemPedido;
import com.rafaelalvim.lanchonete.entity.Pedido;
import com.rafaelalvim.lanchonete.entity.Produto;
import com.rafaelalvim.lanchonete.entity.StatusPedido;
import com.rafaelalvim.lanchonete.repository.PedidoRepository;
import com.rafaelalvim.lanchonete.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public PedidoDTO criarPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setValorTotal(BigDecimal.ZERO);
        Pedido novoPedido = pedidoRepository.save(pedido);
        return new PedidoDTO(novoPedido.getId(), novoPedido.getValorTotal(), novoPedido.getStatus());
    }

    @Transactional
    public PedidoDTO adicionarProduto(Long idPedido, AdicionarProdutoDTO adicionarProdutoDTO) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(adicionarProdutoDTO.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(adicionarProdutoDTO.getQuantidade());
        pedido.getItens().add(itemPedido);

        atualizarValorTotalPedido(pedido);

        return new PedidoDTO(pedido.getId(), pedido.getValorTotal(), pedido.getStatus());
    }

    @Transactional
    public PedidoDTO retirarProduto(Long idPedido, ProdutoQuantidadeDTO produtoQuantidadeDTO) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(produtoQuantidadeDTO.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemPedido itemPedido = pedido.getItens().stream()
                .filter(item -> item.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no pedido"));

        if (produtoQuantidadeDTO.getQuantidade() <= 0 || produtoQuantidadeDTO.getQuantidade() > itemPedido.getQuantidade()) {
            throw new RuntimeException("Quantidade inválida para retirada do produto do pedido");
        }

        int novaQuantidade = itemPedido.getQuantidade() - produtoQuantidadeDTO.getQuantidade();
        if (novaQuantidade == 0) {
            pedido.getItens().remove(itemPedido);
        } else {
            itemPedido.setQuantidade(novaQuantidade);
        }

        atualizarValorTotalPedido(pedido);

        return new PedidoDTO(pedido.getId(), pedido.getValorTotal(), pedido.getStatus());
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularPrecoTotal(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return pedido.getValorTotal();
    }

//    @Transactional
//    public PedidoDTO fecharPedido(Long idPedido, FecharPedidoDTO fecharPedidoDTO) {
//        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
//
//        BigDecimal valorPagamento = fecharPedidoDTO.getValorPagamento();
//        BigDecimal valorTotalPedido = pedido.getValorTotal();
//        if (valorTotalPedido.compareTo(valorPagamento) > 0) {
//            throw new RuntimeException("Valor de pagamento inferior ao valor total do pedido");
//        }
//
//        BigDecimal troco = valorPagamento.subtract(valorTotalPedido);
//
//        pedido.setStatus(StatusPedido.FECHADO);
//        Pedido pedidoFechado = pedidoRepository.save(pedido);
//
//        PedidoDTO pedidoDTO = new PedidoDTO(pedidoFechado.getId(), pedidoFechado.getValorTotal(), pedidoFechado.getStatus());
////        pedidoDTO.setTroco(troco);
//        return pedidoDTO;
//    }

    @Transactional
    public FechamentoDTO fecharPedido(Long idPedido, FecharPedidoDTO fecharPedidoDTO) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        BigDecimal valorPagamento = fecharPedidoDTO.getValorPagamento();
        BigDecimal valorTotalPedido = pedido.getValorTotal();
        if (valorTotalPedido.compareTo(valorPagamento) > 0) {
            throw new RuntimeException("Valor de pagamento inferior ao valor total do pedido");
        }

        BigDecimal troco = valorPagamento.subtract(valorTotalPedido);

        pedido.setStatus(StatusPedido.FECHADO);
        Pedido pedidoFechado = pedidoRepository.save(pedido);

        FechamentoDTO fechamentoDTO = new FechamentoDTO();
        fechamentoDTO.setId(pedidoFechado.getId());
        fechamentoDTO.setValorTotal(pedidoFechado.getValorTotal());
        fechamentoDTO.setStatus(pedidoFechado.getStatus());
        fechamentoDTO.setTroco(troco);

        return fechamentoDTO;
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularPrecoTotalPorLista(Long idPedido, List<ProdutoQuantidadeDTO> produtosQuantidade) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        BigDecimal precoTotal = BigDecimal.ZERO;
        for (ProdutoQuantidadeDTO produtoQuantidade : produtosQuantidade) {
            Produto produto = produtoRepository.findById(produtoQuantidade.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            precoTotal = precoTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(produtoQuantidade.getQuantidade())));
        }

        return precoTotal;
    }

    @Transactional(readOnly = true)
    public PedidoDTO buscarPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoDTO(pedido.getId(), pedido.getValorTotal(), pedido.getStatus());
    }

    private void atualizarValorTotalPedido(Pedido pedido) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : pedido.getItens()) {
            valorTotal = valorTotal.add(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }
        pedido.setValorTotal(valorTotal);
    }
}
