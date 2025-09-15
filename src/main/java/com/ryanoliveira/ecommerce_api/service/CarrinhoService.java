package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.Carrinho;
import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import com.ryanoliveira.ecommerce_api.model.Produto;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import com.ryanoliveira.ecommerce_api.repository.CarrinhoItemRepository;
import com.ryanoliveira.ecommerce_api.repository.CarrinhoRepository;
import com.ryanoliveira.ecommerce_api.repository.ProdutoRepository;
import com.ryanoliveira.ecommerce_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           UsuarioRepository usuarioRepository,
                           ProdutoRepository produtoRepository,
                           CarrinhoItemRepository carrinhoItemRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.carrinhoItemRepository = carrinhoItemRepository;

    }

    public Carrinho criarCarrinho(UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado: " + idUsuario));

        Carrinho carrinhoExistente = carrinhoRepository.findByUsuario_idUsuario(idUsuario).orElse(null);
        if (carrinhoExistente != null) {
            return carrinhoExistente;
        }
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public CarrinhoItem atualizarOuAdicionarItem(UUID idUsuario, Long idProduto, int quantidade) {
        Carrinho carrinho = criarCarrinho(idUsuario);
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CarrinhoItem item = carrinhoItemRepository.findByCarrinhoAndProduto(carrinho, produto)
                .orElseGet(() -> {
                    CarrinhoItem novoItem = new CarrinhoItem();
                    novoItem.setCarrinho(carrinho);
                    novoItem.setProduto(produto);
                    novoItem.setQuantidade(0);
                    novoItem.setPrecoUnitario(produto.getPreco());
                    return novoItem;
                });
        item.setQuantidade(item.getQuantidade() + quantidade);

        return carrinhoItemRepository.save(item);
    }

    @Transactional
    public void removerItem(UUID idUsuario, Long idProduto) {
        Carrinho carrinho = carrinhoRepository.findByUsuario_idUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        carrinhoItemRepository.deleteByCarrinhoAndProduto_Id(carrinho, idProduto);
    }

    public List<CarrinhoItem> listarItens (UUID idUsuario){
        Carrinho carrinho = carrinhoRepository.findByUsuario_idUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        return carrinhoItemRepository.findByCarrinho(carrinho);
    }

    public BigDecimal calcularTotal (UUID idUsuario){
        List<CarrinhoItem> itens = listarItens(idUsuario);
        BigDecimal total = BigDecimal.ZERO;

        for (CarrinhoItem item : itens){
            BigDecimal valorItem = item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(valorItem);
        }
        return total;
    }


}