package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.Produto;
import com.ryanoliveira.ecommerce_api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto save(Produto produto) {
        if (produtoRepository.existsByNome(produto.getProdutoNome())){
            throw new RuntimeException("Produto já cadastrado: " + produto.getProdutoNome());
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(long id, Produto produtoAtualizado){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setProdutoNome(produtoAtualizado.getProdutoNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setEstoque(produtoAtualizado.getEstoque());
        produto.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produto);

    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> buscarPorNome(String nome) {
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
