package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import com.ryanoliveira.ecommerce_api.repository.CarrinhoItemRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarrinhoItemService {

    private final CarrinhoItemRepository carrinhoItemRepository;

    public CarrinhoItemService (CarrinhoItemRepository carrinhoItemRepository){
        this.carrinhoItemRepository = carrinhoItemRepository;
    }

    @Transactional
    public CarrinhoItem save(CarrinhoItem carrinhoItem){
        return carrinhoItemRepository.save(carrinhoItem);
    }

    public List<CarrinhoItem> listarTodos(){
        return carrinhoItemRepository.findAll();
    }

    public List<CarrinhoItem> listarPorUsuario(UUID idUsuario){
        return carrinhoItemRepository.findByUsuarioId(idUsuario);
    }

    public Optional<CarrinhoItem> listarPorId(Long id){
        return carrinhoItemRepository.findById(id);
    }

    @Transactional
    public void deletar (Long id){
        if(!carrinhoItemRepository.existsById(id)){
            throw new RuntimeException(("O item do carrinho não foi encontrado com ID: " + id));
        }
        carrinhoItemRepository.deleteById(id);
    }

    @Transactional
    public void limparCarrinhoUsuario(UUID idUsuario){
        List<CarrinhoItem> itens = carrinhoItemRepository.findByUsuarioId(idUsuario);
        if(itens.isEmpty()){
            throw new RuntimeException("O carrinho está vazio");
        }
        carrinhoItemRepository.deleteAll(itens);
        }
}
