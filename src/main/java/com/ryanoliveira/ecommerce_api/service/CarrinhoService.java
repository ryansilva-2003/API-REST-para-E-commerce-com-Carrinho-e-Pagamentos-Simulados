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
    public CarrinhoItem adicionarItem(UUID idUsuario, Long idProduto, int quantidade) {
        Carrinho carrinho = criarCarrinho(idUsuario);

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CarrinhoItem item = carrinhoItemRepository
                .findByCarrinhoAndProduto(carrinho, produto)
                .orElse(new CarrinhoItem());

        if (item.getIdCarrinhoItem() == null) {
            item.setCarrinho(carrinho);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setQuantidade(0);
        }
        item.setQuantidade(item.getQuantidade() + quantidade);

        return carrinhoItemRepository.save(item);
    }

    @Transactional
    public void removerItem(UUID idUsuario, Long idProduto){
        Carrinho carrinho = carrinhoRepository.findByUsuario_idUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

            carrinhoItemRepository.deleteByCarrinhoAndProduto_Id(carrinho, idProduto);
    }



}