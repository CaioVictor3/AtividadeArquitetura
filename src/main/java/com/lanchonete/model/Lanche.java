package com.lanchonete.model;

import java.util.ArrayList;
import java.util.List;


public class Lanche {
    private String tamanho;
    private String tipoPao;
    private String recheio;
    private List<String> ingredientes;
    private boolean queijoExtra;
    private List<String> molhos;
    private List<String> acompanhamentos;
    private double precoBase;
    private double precoTotal;

   
    private Lanche() {
        this.ingredientes = new ArrayList<>();
        this.molhos = new ArrayList<>();
        this.acompanhamentos = new ArrayList<>();
    }

    // Getters
    public String getTamanho() {
        return tamanho;
    }

    public String getTipoPao() {
        return tipoPao;
    }

    public String getRecheio() {
        return recheio;
    }

    public List<String> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }

    public boolean isQueijoExtra() {
        return queijoExtra;
    }

    public List<String> getMolhos() {
        return new ArrayList<>(molhos);
    }

    public List<String> getAcompanhamentos() {
        return new ArrayList<>(acompanhamentos);
    }

    public double getPrecoTotal() {
        return precoTotal;
    }


    public static class LancheBuilder {
        private Lanche lanche;

        public LancheBuilder() {
            this.lanche = new Lanche();
        }

        public LancheBuilder escolherTamanho(String tamanho) {
            lanche.tamanho = tamanho;
            switch (tamanho.toLowerCase()) {
                case "pequeno":
                    lanche.precoBase = 15.00;
                    break;
                case "medio":
                    lanche.precoBase = 20.00;
                    break;
                case "grande":
                    lanche.precoBase = 25.00;
                    break;
                default:
                    lanche.precoBase = 20.00;
            }
            return this;
        }

        public LancheBuilder comTipoPao(String tipoPao) {
            lanche.tipoPao = tipoPao;
            return this;
        }

        public LancheBuilder comRecheio(String recheio) {
            lanche.recheio = recheio;
            return this;
        }

        public LancheBuilder adicionarIngrediente(String ingrediente) {
            if (ingrediente != null && !ingrediente.trim().isEmpty()) {
                lanche.ingredientes.add(ingrediente);
            }
            return this;
        }

        public LancheBuilder comQueijoExtra(boolean queijoExtra) {
            lanche.queijoExtra = queijoExtra;
            if (queijoExtra) {
                lanche.ingredientes.add("Queijo Extra");
            }
            return this;
        }

        public LancheBuilder adicionarMolho(String molho) {
            if (molho != null && !molho.trim().isEmpty()) {
                lanche.molhos.add(molho);
            }
            return this;
        }

        public LancheBuilder adicionarAcompanhamento(String acompanhamento) {
            if (acompanhamento != null && !acompanhamento.trim().isEmpty()) {
                lanche.acompanhamentos.add(acompanhamento);
            }
            return this;
        }


        public Lanche build() {

            if (lanche.tamanho == null || lanche.tamanho.isEmpty()) {
                throw new IllegalStateException("Tamanho é obrigatório");
            }
            if (lanche.recheio == null || lanche.recheio.isEmpty()) {
                throw new IllegalStateException("Recheio é obrigatório");
            }

            // Calcula preço total
            lanche.precoTotal = lanche.precoBase;
            lanche.precoTotal += lanche.ingredientes.size() * 2.50; // cada adicional custa R$ 2,50
            lanche.precoTotal += lanche.molhos.size() * 1.00; // cada molho custa R$ 1,00
            lanche.precoTotal += lanche.acompanhamentos.size() * 3.00; // cada acompanhamento custa R$ 3,00

            return lanche;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== PEDIDO - LANCHE ==========\n");
        sb.append("Tamanho: ").append(tamanho != null ? tamanho : "Não especificado").append("\n");
        if (tipoPao != null) {
            sb.append("Tipo de Pão: ").append(tipoPao).append("\n");
        }
        sb.append("Recheio: ").append(recheio).append("\n");
        
        if (!ingredientes.isEmpty()) {
            sb.append("Ingredientes: ").append(String.join(", ", ingredientes)).append("\n");
        }
        
        if (!molhos.isEmpty()) {
            sb.append("Molhos: ").append(String.join(", ", molhos)).append("\n");
        }
        
        if (!acompanhamentos.isEmpty()) {
            sb.append("Acompanhamentos: ").append(String.join(", ", acompanhamentos)).append("\n");
        }
        
        sb.append("\nPreço Base: R$ ").append(String.format("%.2f", precoBase)).append("\n");
        sb.append("Preço Total: R$ ").append(String.format("%.2f", precoTotal)).append("\n");
        sb.append("=====================================\n");
        
        return sb.toString();
    }
}

