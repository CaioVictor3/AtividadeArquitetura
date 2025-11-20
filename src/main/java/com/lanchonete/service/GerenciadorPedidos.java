package com.lanchonete.service;

import com.lanchonete.model.Lanche;
import com.lanchonete.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPedidos {
    private static GerenciadorPedidos instancia;
    private List<Lanche> lanchesPedidos;
    private List<Pizza> pizzasPedidos;

    private GerenciadorPedidos() {
        this.lanchesPedidos = new ArrayList<>();
        this.pizzasPedidos = new ArrayList<>();
    }

    public static synchronized GerenciadorPedidos getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorPedidos();
        }
        return instancia;
    }

    public void adicionarLanche(Lanche lanche) {
        if (lanche != null) {
            lanchesPedidos.add(lanche);
        }
    }

    public void adicionarPizza(Pizza pizza) {
        if (pizza != null) {
            pizzasPedidos.add(pizza);
        }
    }

    public List<Lanche> getLanchesPedidos() {
        return new ArrayList<>(lanchesPedidos);
    }

    public List<Pizza> getPizzasPedidos() {
        return new ArrayList<>(pizzasPedidos);
    }

    public int getTotalPedidos() {
        return lanchesPedidos.size() + pizzasPedidos.size();
    }

    public double getTotalVendas() {
        double total = 0.0;
        for (Lanche lanche : lanchesPedidos) {
            total += lanche.getPrecoTotal();
        }
        for (Pizza pizza : pizzasPedidos) {
            total += pizza.getPrecoTotal();
        }
        return total;
    }

    public void limparPedidos() {
        lanchesPedidos.clear();
        pizzasPedidos.clear();
    }
}

