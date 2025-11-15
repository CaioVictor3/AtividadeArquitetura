package com.lanchonete.model;

import java.util.ArrayList;
import java.util.List;


public class Pizza {
    private String tamanho;
    private String tipoMassa;
    private List<String> recheios;
    private boolean queijoExtra;
    private List<String> molhos;
    private List<String> extras;
    private double precoBase;
    private double precoTotal;

   
    private Pizza() {
        this.recheios = new ArrayList<>();
        this.molhos = new ArrayList<>();
        this.extras = new ArrayList<>();
    }


    public String getTamanho() {
        return tamanho;
    }

    public String getTipoMassa() {
        return tipoMassa;
    }

    public List<String> getRecheios() {
        return new ArrayList<>(recheios);
    }

    public boolean isQueijoExtra() {
        return queijoExtra;
    }

    public List<String> getMolhos() {
        return new ArrayList<>(molhos);
    }

    public List<String> getExtras() {
        return new ArrayList<>(extras);
    }

    public double getPrecoTotal() {
        return precoTotal;
    }


    public static class PizzaBuilder {
        private Pizza pizza;

        public PizzaBuilder() {
            this.pizza = new Pizza();
        }

        public PizzaBuilder escolherTamanho(String tamanho) {
            pizza.tamanho = tamanho;
            switch (tamanho.toLowerCase()) {
                case "pequena":
                    pizza.precoBase = 25.00;
                    break;
                case "media":
                    pizza.precoBase = 35.00;
                    break;
                case "grande":
                    pizza.precoBase = 45.00;
                    break;
                case "familia":
                    pizza.precoBase = 55.00;
                    break;
                default:
                    pizza.precoBase = 35.00;
            }
            return this;
        }

        public PizzaBuilder comTipoMassa(String tipoMassa) {
            pizza.tipoMassa = tipoMassa;
            return this;
        }

        public PizzaBuilder adicionarRecheio(String recheio) {
            if (recheio != null && !recheio.trim().isEmpty()) {
                pizza.recheios.add(recheio);
            }
            return this;
        }

        public PizzaBuilder comQueijoExtra(boolean queijoExtra) {
            pizza.queijoExtra = queijoExtra;
            if (queijoExtra) {
                pizza.extras.add("Queijo Extra");
            }
            return this;
        }

        public PizzaBuilder adicionarMolho(String molho) {
            if (molho != null && !molho.trim().isEmpty()) {
                pizza.molhos.add(molho);
            }
            return this;
        }

        public PizzaBuilder adicionarExtra(String extra) {
            if (extra != null && !extra.trim().isEmpty()) {
                pizza.extras.add(extra);
            }
            return this;
        }


        public Pizza build() {

            if (pizza.tamanho == null || pizza.tamanho.isEmpty()) {
                throw new IllegalStateException("Tamanho é obrigatório");
            }
            if (pizza.recheios.isEmpty()) {
                throw new IllegalStateException("Pelo menos um recheio é obrigatório");
            }


            pizza.precoTotal = pizza.precoBase;
            pizza.precoTotal += pizza.recheios.size() * 3.00; // cada recheio adicional R$ 3,00
            pizza.precoTotal += pizza.molhos.size() * 1.50; // cada molho custa 1,50
            pizza.precoTotal += pizza.extras.size() * 4.00; // cada extra custa 4,00

            return pizza;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------- PEDIDO de PIZZA ----------n");
        sb.append("Tamanho: ").append(tamanho != null ? tamanho : "Não especificado").append("\n");
        if (tipoMassa != null) {
            sb.append("Tipo de Massa: ").append(tipoMassa).append("\n");
        }
        sb.append("Recheios: ").append(String.join(", ", recheios)).append("\n");
        
        if (!molhos.isEmpty()) {
            sb.append("Molhos: ").append(String.join(", ", molhos)).append("\n");
        }
        
        if (!extras.isEmpty()) {
            sb.append("Extras: ").append(String.join(", ", extras)).append("\n");
        }
        
        sb.append("\nPreço Base: R$ ").append(String.format("%.2f", precoBase)).append("\n");
        sb.append("Preço Total: R$ ").append(String.format("%.2f", precoTotal)).append("\n");
        sb.append("-------------------------------------\n");
        
        return sb.toString();
    }
}

