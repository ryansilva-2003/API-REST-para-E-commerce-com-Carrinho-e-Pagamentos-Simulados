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

import java.util.UUID;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;
    private CarrinhoItemRepository carrinhoItemRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
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
    public CarrinhoItem adicionarItem(UUID idUsuario, Long idProduto, int quantidade) {
        Carrinho carrinho = criarCarrinho(idUsuario);

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CarrinhoItem = carrinhoItemRepository
                .findByCarrinhoAndProduto(carrinho, produto)
                .orElse(new carrinhoItem());

        if (item.getId() == null) { // só entra aqui se for um novo CarrinhoItem
            item.setCarrinho(carrinho);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setQuantidade(0);
        }
        item.setQuantidade(item.getQuantidade() + quantidade);

        // 6. Salvar no banco
        return carrinhoItemRepository.save(item);
    }

}