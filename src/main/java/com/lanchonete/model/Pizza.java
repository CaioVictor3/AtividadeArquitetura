package com.lanchonete.model;

import com.lanchonete.service.GerenciadorPedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Pizza {
    private String tamanho;
    private String tipoMassa;
    private Map<String, Integer> recheios; // Nome -> Quantidade
    private boolean queijoExtra;
    private Map<String, Integer> molhos; // Nome -> Quantidade
    private Map<String, Integer> extras; // Nome -> Quantidade
    private double precoBase;
    private double precoTotal;

    private Pizza() {
        this.recheios = new HashMap<>();
        this.molhos = new HashMap<>();
        this.extras = new HashMap<>();
    }


    public String getTamanho() {
        return tamanho;
    }

    public String getTipoMassa() {
        return tipoMassa;
    }

    public Map<String, Integer> getRecheios() {
        return new HashMap<>(recheios);
    }

    public boolean isQueijoExtra() {
        return queijoExtra;
    }

    public Map<String, Integer> getMolhos() {
        return new HashMap<>(molhos);
    }

    public Map<String, Integer> getExtras() {
        return new HashMap<>(extras);
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    /**
     * Cria uma cópia (clone) desta pizza para reutilização da configuração
     * Útil quando o cliente quer pedir a mesma pizza novamente
     * @return Um novo builder pré-configurado com as mesmas opções
     */
    public PizzaBuilder clonar() {
        PizzaBuilder builder = new PizzaBuilder()
            .escolherTamanho(this.tamanho)
            .comTipoMassa(this.tipoMassa)
            .comQueijoExtra(this.queijoExtra);
        
        // Adiciona recheios
        for (Map.Entry<String, Integer> entry : this.recheios.entrySet()) {
            int quantidade = entry.getValue();
            for (int i = 0; i < quantidade; i++) {
                builder.adicionarRecheio(entry.getKey());
            }
        }
        
        // Adiciona molhos
        for (Map.Entry<String, Integer> entry : this.molhos.entrySet()) {
            int quantidade = entry.getValue();
            for (int i = 0; i < quantidade; i++) {
                builder.adicionarMolho(entry.getKey());
            }
        }
        
        // Adiciona extras (exceto queijo extra que já foi adicionado)
        for (Map.Entry<String, Integer> entry : this.extras.entrySet()) {
            if (!entry.getKey().equals("Queijo Extra")) {
                int quantidade = entry.getValue();
                for (int i = 0; i < quantidade; i++) {
                    builder.adicionarExtra(entry.getKey());
                }
            }
        }
        
        return builder;
    }


    /**
     * Builder interno para construção fluente de Pizza
     * Implementa o padrão Builder (GoF)
     */
    public static class PizzaBuilder {
        private Pizza pizza;

        public PizzaBuilder() {
            this.pizza = new Pizza();
        }

        /**
         * Define o tamanho da pizza (afeta preço base)
         * @param tamanho Pequena, Media, Grande ou Familia
         * @return this builder para encadeamento fluente
         */
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
                pizza.recheios.put(recheio, 
                    pizza.recheios.getOrDefault(recheio, 0) + 1);
            }
            return this;
        }

        public PizzaBuilder comQueijoExtra(boolean queijoExtra) {
            pizza.queijoExtra = queijoExtra;
            if (queijoExtra) {
                pizza.extras.put("Queijo Extra", 
                    pizza.extras.getOrDefault("Queijo Extra", 0) + 1);
            }
            return this;
        }

        public PizzaBuilder adicionarMolho(String molho) {
            if (molho != null && !molho.trim().isEmpty()) {
                pizza.molhos.put(molho, 
                    pizza.molhos.getOrDefault(molho, 0) + 1);
            }
            return this;
        }

        public PizzaBuilder adicionarExtra(String extra) {
            if (extra != null && !extra.trim().isEmpty()) {
                pizza.extras.put(extra, 
                    pizza.extras.getOrDefault(extra, 0) + 1);
            }
            return this;
        }


        /**
         * Constrói o objeto Pizza final após validações
         * @return Pizza configurada e pronta para uso
         * @throws IllegalStateException se configurações obrigatórias estiverem ausentes ou inválidas
         */
        public Pizza build() {
            // Validações de campos obrigatórios
            if (pizza.tamanho == null || pizza.tamanho.isEmpty()) {
                throw new IllegalStateException("Tamanho é obrigatório");
            }
            if (pizza.tipoMassa == null || pizza.tipoMassa.isEmpty()) {
                throw new IllegalStateException("Tipo de massa é obrigatório");
            }
            if (pizza.recheios.isEmpty()) {
                throw new IllegalStateException("Pelo menos um recheio é obrigatório");
            }

            // Validação: Vegetariano não pode ter bacon
            if (pizza.recheios.keySet().stream().anyMatch(r -> r.equalsIgnoreCase("Vegetariano"))) {
                for (String extra : pizza.extras.keySet()) {
                    if (extra.equalsIgnoreCase("Bacon")) {
                        throw new IllegalStateException("Pizza vegetariana não pode conter Bacon");
                    }
                }
            }

            // Validação: Máximo de 5 tipos diferentes de recheios
            if (pizza.recheios.size() > 5) {
                throw new IllegalStateException("Máximo de 5 tipos diferentes de recheios permitidos");
            }

            pizza.precoTotal = pizza.precoBase;
            
            // Calcula preço dos recheios (soma das quantidades)
            int totalRecheios = pizza.recheios.values().stream()
                .mapToInt(Integer::intValue).sum();
            pizza.precoTotal += totalRecheios * 3.00; // cada recheio adicional R$ 3,00
            
            // Calcula preço dos molhos (soma das quantidades)
            int totalMolhos = pizza.molhos.values().stream()
                .mapToInt(Integer::intValue).sum();
            pizza.precoTotal += totalMolhos * 1.50; // cada molho custa 1,50
            
            // Calcula preço dos extras (soma das quantidades)
            int totalExtras = pizza.extras.values().stream()
                .mapToInt(Integer::intValue).sum();
            pizza.precoTotal += totalExtras * 4.00; // cada extra custa 4,00

            // Adiciona ao gerenciador de pedidos (Singleton)
            GerenciadorPedidos.getInstancia().adicionarPizza(pizza);

            return pizza;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------- PEDIDO de PIZZA ----------\n");
        sb.append("Tamanho: ").append(tamanho != null ? tamanho : "Não especificado").append("\n");
        if (tipoMassa != null) {
            sb.append("Tipo de Massa: ").append(tipoMassa).append("\n");
        }
        
        if (!recheios.isEmpty()) {
            sb.append("Recheios: ");
            List<String> recheiosFormatados = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : recheios.entrySet()) {
                String recheio = entry.getKey();
                int qtd = entry.getValue();
                if (qtd > 1) {
                    recheiosFormatados.add(recheio + " (" + qtd + "x)");
                } else {
                    recheiosFormatados.add(recheio);
                }
            }
            sb.append(String.join(", ", recheiosFormatados)).append("\n");
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
        
        if (!extras.isEmpty()) {
            sb.append("Extras: ");
            List<String> extrasFormatados = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : extras.entrySet()) {
                String extra = entry.getKey();
                int qtd = entry.getValue();
                if (qtd > 1) {
                    extrasFormatados.add(extra + " (" + qtd + "x)");
                } else {
                    extrasFormatados.add(extra);
                }
            }
            sb.append(String.join(", ", extrasFormatados)).append("\n");
        }
        
        sb.append("\nPreço Base: R$ ").append(String.format("%.2f", precoBase)).append("\n");
        sb.append("Preço Total: R$ ").append(String.format("%.2f", precoTotal)).append("\n");
        sb.append("-------------------------------------\n");
        
        return sb.toString();
    }
}

