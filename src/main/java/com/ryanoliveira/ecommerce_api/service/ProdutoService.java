package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.Produto;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import com.ryanoliveira.ecommerce_api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto save(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNomeProduto())){
            throw new RuntimeException("Produto já cadastrado: " + produto.getNomeProduto());
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(long id, Produto produtoAtualizado){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setEstoque(produtoAtualizado.getEstoque());
        produto.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produto);

    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + idProduto));
    }

    public Optional<Produto> findByNome(String nome) {
        return Optional.ofNullable(produtoRepository.findByNome(nome));
    }

    @Transactional
    public void deletar(Long id){
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

}
