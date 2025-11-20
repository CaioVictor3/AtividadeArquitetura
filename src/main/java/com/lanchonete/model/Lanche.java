package com.lanchonete.model;

import com.lanchonete.service.GerenciadorPedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lanche {
    private String tamanho;
    private String tipoPao;
    private String recheio;
    private Map<String, Integer> ingredientes; // Nome -> Quantidade
    private boolean queijoExtra;
    private Map<String, Integer> molhos; // Nome -> Quantidade
    private Map<String, Integer> acompanhamentos; // Nome -> Quantidade
    private double precoBase;
    private double precoTotal;

    private Lanche() {
        this.ingredientes = new HashMap<>();
        this.molhos = new HashMap<>();
        this.acompanhamentos = new HashMap<>();
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

    public Map<String, Integer> getIngredientes() {
        return new HashMap<>(ingredientes);
    }

    public boolean isQueijoExtra() {
        return queijoExtra;
    }

    public Map<String, Integer> getMolhos() {
        return new HashMap<>(molhos);
    }

    public Map<String, Integer> getAcompanhamentos() {
        return new HashMap<>(acompanhamentos);
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    /**
     * Cria uma cópia (clone) deste lanche para reutilização da configuração
     * Útil quando o cliente quer pedir o mesmo lanche novamente
     * @return Um novo builder pré-configurado com as mesmas opções
     */
    public LancheBuilder clonar() {
        LancheBuilder builder = new LancheBuilder()
            .escolherTamanho(this.tamanho)
            .comTipoPao(this.tipoPao)
            .comRecheio(this.recheio)
            .comQueijoExtra(this.queijoExtra);
        
        // Adiciona ingredientes
        for (Map.Entry<String, Integer> entry : this.ingredientes.entrySet()) {
            String ingrediente = entry.getKey();
            if (!ingrediente.equals("Queijo Extra")) { // Queijo extra já foi adicionado
                int quantidade = entry.getValue();
                for (int i = 0; i < quantidade; i++) {
                    builder.adicionarIngrediente(ingrediente);
                }
            }
        }
        
        // Adiciona molhos
        for (Map.Entry<String, Integer> entry : this.molhos.entrySet()) {
            int quantidade = entry.getValue();
            for (int i = 0; i < quantidade; i++) {
                builder.adicionarMolho(entry.getKey());
            }
        }
        
        // Adiciona acompanhamentos
        for (Map.Entry<String, Integer> entry : this.acompanhamentos.entrySet()) {
            int quantidade = entry.getValue();
            for (int i = 0; i < quantidade; i++) {
                builder.adicionarAcompanhamento(entry.getKey());
            }
        }
        
        return builder;
    }


    /**
     * Builder interno para construção fluente de Lanche
     * Implementa o padrão Builder (GoF)
     */
    public static class LancheBuilder {
        private Lanche lanche;

        public LancheBuilder() {
            this.lanche = new Lanche();
        }

        /**
         * Define o tamanho do lanche (afeta preço base)
         * @param tamanho Pequeno, Medio ou Grande
         * @return this builder para encadeamento fluente
         */
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
                lanche.ingredientes.put(ingrediente, 
                    lanche.ingredientes.getOrDefault(ingrediente, 0) + 1);
            }
            return this;
        }

        public LancheBuilder comQueijoExtra(boolean queijoExtra) {
            lanche.queijoExtra = queijoExtra;
            if (queijoExtra) {
                lanche.ingredientes.put("Queijo Extra", 
                    lanche.ingredientes.getOrDefault("Queijo Extra", 0) + 1);
            }
            return this;
        }

        public LancheBuilder adicionarMolho(String molho) {
            if (molho != null && !molho.trim().isEmpty()) {
                lanche.molhos.put(molho, 
                    lanche.molhos.getOrDefault(molho, 0) + 1);
            }
            return this;
        }

        public LancheBuilder adicionarAcompanhamento(String acompanhamento) {
            if (acompanhamento != null && !acompanhamento.trim().isEmpty()) {
                lanche.acompanhamentos.put(acompanhamento, 
                    lanche.acompanhamentos.getOrDefault(acompanhamento, 0) + 1);
            }
            return this;
        }


        /**
         * Constrói o objeto Lanche final após validações
         * @return Lanche configurado e pronto para uso
         * @throws IllegalStateException se configurações obrigatórias estiverem ausentes
         */
        public Lanche build() {
            // Validações de campos obrigatórios
            if (lanche.tamanho == null || lanche.tamanho.isEmpty()) {
                throw new IllegalStateException("Tamanho é obrigatório");
            }
            if (lanche.tipoPao == null || lanche.tipoPao.isEmpty()) {
                throw new IllegalStateException("Tipo de pão é obrigatório");
            }
            if (lanche.recheio == null || lanche.recheio.isEmpty()) {
                throw new IllegalStateException("Recheio é obrigatório");
            }

            // Validação: Vegetariano não pode ter bacon ou ovo
            if (lanche.recheio.equalsIgnoreCase("Vegetariano")) {
                for (String ing : lanche.ingredientes.keySet()) {
                    if (ing.equalsIgnoreCase("Bacon") || ing.equalsIgnoreCase("Ovo")) {
                        throw new IllegalStateException("Pedido vegetariano não pode conter " + ing);
                    }
                }
            }

            // Calcula preço total
            lanche.precoTotal = lanche.precoBase;
            
            // Calcula preço dos ingredientes (soma das quantidades)
            int totalIngredientes = lanche.ingredientes.values().stream()
                .mapToInt(Integer::intValue).sum();
            lanche.precoTotal += totalIngredientes * 2.50; // cada adicional custa R$ 2,50
            
            // Calcula preço dos molhos (soma das quantidades)
            int totalMolhos = lanche.molhos.values().stream()
                .mapToInt(Integer::intValue).sum();
            lanche.precoTotal += totalMolhos * 1.00; // cada molho custa R$ 1,00
            
            // Calcula preço dos acompanhamentos (soma das quantidades)
            int totalAcompanhamentos = lanche.acompanhamentos.values().stream()
                .mapToInt(Integer::intValue).sum();
            lanche.precoTotal += totalAcompanhamentos * 3.00; // cada acompanhamento custa R$ 3,00

            // Adiciona ao gerenciador de pedidos (Singleton)
            GerenciadorPedidos.getInstancia().adicionarLanche(lanche);

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
            sb.append("Ingredientes: ");
            List<String> ingFormatados = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
                String ing = entry.getKey();
                int qtd = entry.getValue();
                if (qtd > 1) {
                    ingFormatados.add(ing + " (" + qtd + "x)");
                } else {
                    ingFormatados.add(ing);
                }
            }
            sb.append(String.join(", ", ingFormatados)).append("\n");
        }
        
        if (!molhos.isEmpty()) {
            sb.append("Molhos: ");
            List<String> molhosFormatados = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : molhos.entrySet()) {
                String molho = entry.getKey();
                int qtd = entry.getValue();
                if (qtd > 1) {
                    molhosFormatados.add(molho + " (" + qtd + "x)");
                } else {
                    molhosFormatados.add(molho);
                }
            }
            sb.append(String.join(", ", molhosFormatados)).append("\n");
        }
        
        if (!acompanhamentos.isEmpty()) {
            sb.append("Acompanhamentos: ");
            List<String> acFormatados = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : acompanhamentos.entrySet()) {
                String ac = entry.getKey();
                int qtd = entry.getValue();
                if (qtd > 1) {
                    acFormatados.add(ac + " (" + qtd + "x)");
                } else {
                    acFormatados.add(ac);
                }
            }
            sb.append(String.join(", ", acFormatados)).append("\n");
        }
        
        sb.append("\nPreço Base: R$ ").append(String.format("%.2f", precoBase)).append("\n");
        sb.append("Preço Total: R$ ").append(String.format("%.2f", precoTotal)).append("\n");
        sb.append("=====================================\n");
        
        return sb.toString();
    }
}

