package com.rafaelalvim.lanchonete.service;

import com.rafaelalvim.lanchonete.dto.CriarProdutoDTO;
import com.rafaelalvim.lanchonete.dto.ProdutoDTO;
import com.rafaelalvim.lanchonete.entity.ItemPedido;
import com.rafaelalvim.lanchonete.entity.Produto;
import com.rafaelalvim.lanchonete.repository.ItemPedidoRepository;
import com.rafaelalvim.lanchonete.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        Produto novoProduto = produtoRepository.save(produto);
        return toDto(novoProduto);
    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());

        Produto produtoAtualizado = produtoRepository.save(produto);
        return toDto(produtoAtualizado);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        List<ItemPedido> itensReferenciandoProduto = itemPedidoRepository.findByProdutoId(id);

        if (!itensReferenciandoProduto.isEmpty()) {
            itemPedidoRepository.deleteAll(itensReferenciandoProduto);
        }

        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return toDto(produto);
    }

    private ProdutoDTO toDto(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    private Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        return produto;
    }
}
