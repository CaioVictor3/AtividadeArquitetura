package com.lanchonete;

import com.lanchonete.factory.IngredienteFactory;
import com.lanchonete.model.Lanche;
import com.lanchonete.model.Pizza;
import com.lanchonete.service.GerenciadorPedidos;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("    SISTEMA DE PEDIDOS - LANCHONETE");
        System.out.println("-----------------------------------------");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha o tipo de pedido:");
            System.out.println("1 - Lanche");
            System.out.println("2 - Pizza");
            System.out.println("3 - Clonar Pedido Anterior");
            System.out.println("4 - Visualizar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("\nOpção: ");

            int opcao = lerOpcaoNumerica();
            
            // Validação de opção válida
            if (opcao < 0 || opcao > 4) {
                System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 4.");
                continue;
            }

            Lanche lanche = new Lanche.LancheBuilder()
                .escolherTamanho("Grande")
                .comTipoPao("Brioche")
                .comRecheio("Frango")
                .comQueijoExtra(true)
                .adicionarMolho("Barbecue")
                .build();


            switch (opcao) {
                case 1 -> montarLanche();
                case 2 -> montarPizza();
                case 3 -> clonarPedido();
                case 4 -> visualizarPedidos();
                case 0 -> {
                    continuar = false;
                    System.out.println("\nObrigado! Volte sempre!");
                }
            }
        }

        scanner.close();
    }


    private static int lerOpcaoNumerica() {
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    System.out.print("Valor inválido! Digite um número: ");
                    continue;
                }
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número: ");
            }
        }
    }

    private static boolean lerSimOuNao() {
        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("s")) {
                return true;
            } else if (entrada.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.print("Resposta inválida! Digite apenas 's' para sim ou 'n' para não: ");
            }
        }
    }


    private static void montarLanche() {
        System.out.println("\n-------- MONTAR LANCHE --------");

        Lanche.LancheBuilder builder = new Lanche.LancheBuilder();

        // Tamanho
        System.out.println("\nEscolha o tamanho:");
        System.out.println("1 - Pequeno (R$ 15,00)");
        System.out.println("2 - Médio (R$ 20,00)");
        System.out.println("3 - Grande (R$ 25,00)");
        System.out.print("Opção: ");
        int tamanhoOpcao = lerOpcaoNumerica();
        
        while (tamanhoOpcao < 1 || tamanhoOpcao > 3) {
            System.out.println("Opção inválida! Escolha entre 1 e 3.");
            System.out.print("Opção: ");
            tamanhoOpcao = lerOpcaoNumerica();
        }

        switch (tamanhoOpcao) {
            case 1 -> builder.escolherTamanho("Pequeno");
            case 2 -> builder.escolherTamanho("Medio");
            case 3 -> builder.escolherTamanho("Grande");
        }

        // Pão (obrigatório)
        String tipoPao = null;
        while (tipoPao == null) {
            System.out.println("\nEscolha o tipo de pão (OBRIGATÓRIO):");
            System.out.println("1 - Australiano");
            System.out.println("2 - Integral");
            System.out.println("3 - Brioche");
            System.out.println("4 - Italiano");
            System.out.print("Opção: ");
            String paoOpcao = scanner.nextLine().trim();
            if (!paoOpcao.isEmpty()) {
                try {
                    int opcaoPaoNum = Integer.parseInt(paoOpcao);
                    if (opcaoPaoNum >= 1 && opcaoPaoNum <= 4) {
                        tipoPao = IngredienteFactory.criarPao(paoOpcao);
                        builder.comTipoPao(tipoPao);
                    } else {
                        System.out.println("Erro: Escolha uma opção entre 1 e 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite apenas números de 1 a 4.");
                }
            } else {
                System.out.println("Erro: Tipo de pão é obrigatório! Escolha uma opção.");
            }
        }

        // Recheio (obrigatório)
        String recheio = null;
        while (recheio == null) {
            System.out.println("\nEscolha o recheio (OBRIGATÓRIO):");
            System.out.println("1 - Frango");
            System.out.println("2 - Carne");
            System.out.println("3 - Calabresa");
            System.out.println("4 - Vegetariano");
            System.out.print("Opção: ");
            String recheioOpcao = scanner.nextLine().trim();
            if (!recheioOpcao.isEmpty()) {
                try {
                    int opcaoRecheioNum = Integer.parseInt(recheioOpcao);
                    if (opcaoRecheioNum >= 1 && opcaoRecheioNum <= 4) {
                        recheio = IngredienteFactory.criarRecheio(recheioOpcao);
                        builder.comRecheio(recheio);
                    } else {
                        System.out.println("Erro: Escolha uma opção entre 1 e 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite apenas números de 1 a 4.");
                }
            } else {
                System.out.println("Erro: Recheio é obrigatório! Escolha uma opção.");
            }
        }

        // Queijo extra
        System.out.print("\nDeseja queijo extra? (s/n): ");
        builder.comQueijoExtra(lerSimOuNao());

        // Ingredientes adicionais
        String[] ingredientesDisponiveis = {"Alface", "Tomate", "Cebola", "Picles", "Bacon", "Ovo"};
        System.out.println("\nIngredientes adicionais (digite o número ou 'fim' para encerrar):");
        for (int i = 0; i < ingredientesDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + ingredientesDisponiveis[i]);
        }

        while (true) {
            System.out.print("Ingrediente: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) break;
            
            try {
                int numIng = Integer.parseInt(entrada);
                if (numIng >= 1 && numIng <= ingredientesDisponiveis.length) {
                    builder.adicionarIngrediente(ingredientesDisponiveis[numIng - 1]);
                    System.out.println("✓ " + ingredientesDisponiveis[numIng - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + ingredientesDisponiveis.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }

        // Molhos
        String[] molhosDisponiveis = {"Barbecue", "Mostarda", "Maionese", "Picante"};
        System.out.println("\nMolhos (digite o número ou 'fim' para encerrar):");
        for (int i = 0; i < molhosDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + molhosDisponiveis[i]);
        }
        
        while (true) {
            System.out.print("Molho: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) break;
            
            try {
                int numMolho = Integer.parseInt(entrada);
                if (numMolho >= 1 && numMolho <= molhosDisponiveis.length) {
                    builder.adicionarMolho(molhosDisponiveis[numMolho - 1]);
                    System.out.println("✓ " + molhosDisponiveis[numMolho - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + molhosDisponiveis.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }

        // Acompanhamentos
        String[] acompanhamentosDisponiveis = {"Batata Frita", "Anéis de Cebola", "Nuggets"};
        System.out.println("\nAcompanhamentos (digite o número ou 'fim' para encerrar):");
        for (int i = 0; i < acompanhamentosDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + acompanhamentosDisponiveis[i]);
        }

        while (true) {
            System.out.print("Acompanhamento: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) break;
            
            try {
                int numAc = Integer.parseInt(entrada);
                if (numAc >= 1 && numAc <= acompanhamentosDisponiveis.length) {
                    builder.adicionarAcompanhamento(acompanhamentosDisponiveis[numAc - 1]);
                    System.out.println("✓ " + acompanhamentosDisponiveis[numAc - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + acompanhamentosDisponiveis.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }

        try {
            Lanche lanche = builder.build();
            System.out.println("\n" + lanche);
            System.out.println("✓ Lanche adicionado ao pedido!");
        } catch (IllegalStateException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }


    private static void montarPizza() {
        System.out.println("\n------ MONTAR PIZZA ------");

        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();


        System.out.println("\nEscolha o tamanho:");
        System.out.println("1 - Pequena (R$ 25,00)");
        System.out.println("2 - Média (R$ 35,00)");
        System.out.println("3 - Grande (R$ 45,00)");
        System.out.println("4 - Família (R$ 55,00)");
        System.out.print("Opção: ");
        int tamanhoOpcao = lerOpcaoNumerica();
        
        while (tamanhoOpcao < 1 || tamanhoOpcao > 4) {
            System.out.println("Opção inválida! Escolha entre 1 e 4.");
            System.out.print("Opção: ");
            tamanhoOpcao = lerOpcaoNumerica();
        }

        switch (tamanhoOpcao) {
            case 1 -> builder.escolherTamanho("Pequena");
            case 2 -> builder.escolherTamanho("Media");
            case 3 -> builder.escolherTamanho("Grande");
            case 4 -> builder.escolherTamanho("Familia");
        }


        // Tipo de massa (obrigatório)
        String tipoMassa = null;
        while (tipoMassa == null) {
            System.out.println("\nEscolha o tipo de massa (OBRIGATÓRIO):");
            System.out.println("1 - Fina");
            System.out.println("2 - Tradicional");
            System.out.println("3 - Pan");
            System.out.println("4 - Integral");
            System.out.print("Opção: ");
            String massaOpcao = scanner.nextLine().trim();
            if (!massaOpcao.isEmpty()) {
                try {
                    int opcaoMassaNum = Integer.parseInt(massaOpcao);
                    if (opcaoMassaNum >= 1 && opcaoMassaNum <= 4) {
                        tipoMassa = IngredienteFactory.criarMassa(massaOpcao);
                        builder.comTipoMassa(tipoMassa);
                    } else {
                        System.out.println("Erro: Escolha uma opção entre 1 e 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite apenas números de 1 a 4.");
                }
            } else {
                System.out.println("Erro: Tipo de massa é obrigatório! Escolha uma opção.");
            }
        }


        // Recheios (obrigatório - pelo menos um)
        String[] recheiosDisponiveis = {"Frango", "Carne", "Calabresa", "Vegetariano", "Pepperoni", "Presunto", "Queijo"};
        boolean temRecheio = false;
        System.out.println("\nEscolha os recheios (OBRIGATÓRIO - pelo menos um, digite 'fim' para encerrar):");
        for (int i = 0; i < recheiosDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + recheiosDisponiveis[i]);
        }

        while (true) {
            System.out.print("Recheio: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) {
                if (!temRecheio) {
                    System.out.println("Erro: É obrigatório escolher pelo menos um recheio!");
                    continue;
                }
                break;
            }
            
            try {
                int numRecheio = Integer.parseInt(entrada);
                if (numRecheio >= 1 && numRecheio <= recheiosDisponiveis.length) {
                    builder.adicionarRecheio(recheiosDisponiveis[numRecheio - 1]);
                    temRecheio = true;
                    System.out.println("✓ " + recheiosDisponiveis[numRecheio - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + recheiosDisponiveis.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }


        System.out.print("\nDeseja queijo extra? (s/n): ");
        builder.comQueijoExtra(lerSimOuNao());

        String[] molhosDisponiveisPizza = {"Barbecue", "Mostarda", "Maionese", "Picante"};
        System.out.println("\nMolhos (digite o número ou 'fim' para encerrar):");
        for (int i = 0; i < molhosDisponiveisPizza.length; i++) {
            System.out.println((i + 1) + " - " + molhosDisponiveisPizza[i]);
        }
        
        while (true) {
            System.out.print("Molho: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) break;
            
            try {
                int numMolho = Integer.parseInt(entrada);
                if (numMolho >= 1 && numMolho <= molhosDisponiveisPizza.length) {
                    builder.adicionarMolho(molhosDisponiveisPizza[numMolho - 1]);
                    System.out.println("✓ " + molhosDisponiveisPizza[numMolho - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + molhosDisponiveisPizza.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }


        String[] extrasDisponiveis = {"Bacon", "Azeitona", "Champignon", "Pimentão"};
        System.out.println("\nExtras (digite o número ou 'fim' para encerrar):");
        for (int i = 0; i < extrasDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + extrasDisponiveis[i]);
        }
        
        while (true) {
            System.out.print("Extra: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fim")) break;
            
            try {
                int numExtra = Integer.parseInt(entrada);
                if (numExtra >= 1 && numExtra <= extrasDisponiveis.length) {
                    builder.adicionarExtra(extrasDisponiveis[numExtra - 1]);
                    System.out.println("✓ " + extrasDisponiveis[numExtra - 1] + " adicionado!");
                } else {
                    System.out.println("Erro: Número inválido! Escolha um número entre 1 e " + extrasDisponiveis.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido ou 'fim' para encerrar");
            }
        }

        try {
            Pizza pizza = builder.build();
            System.out.println("\n" + pizza);
            System.out.println("✓ Pizza adicionada ao pedido!");
        } catch (IllegalStateException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }


    private static void clonarPedido() {
        System.out.println("\n------ CLONAR PEDIDO ANTERIOR ------");
        
        GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();
        List<Lanche> lanchesPedidos = gerenciador.getLanchesPedidos();
        List<Pizza> pizzasPedidos = gerenciador.getPizzasPedidos();
        
        int totalPedidos = gerenciador.getTotalPedidos();
        
        if (totalPedidos == 0) {
            System.out.println("\nNenhum pedido anterior encontrado.");
            System.out.println("Faça um pedido primeiro para poder cloná-lo!");
            System.out.print("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\nEscolha qual pedido deseja clonar:");
        System.out.println("\n--- LANCHES DISPONÍVEIS ---");
        
        if (lanchesPedidos.isEmpty()) {
            System.out.println("  (Nenhum lanche pedido)");
        } else {
            for (int i = 0; i < lanchesPedidos.size(); i++) {
                Lanche lanche = lanchesPedidos.get(i);
                System.out.println((i + 1) + " - Lanche " + lanche.getTamanho() + 
                                 " (Pão: " + lanche.getTipoPao() + 
                                 ", Recheio: " + lanche.getRecheio() + 
                                 ") - R$ " + String.format("%.2f", lanche.getPrecoTotal()));
            }
        }
        
        System.out.println("\n--- PIZZAS DISPONÍVEIS ---");
        
        if (pizzasPedidos.isEmpty()) {
            System.out.println("  (Nenhuma pizza pedida)");
        } else {
            for (int i = 0; i < pizzasPedidos.size(); i++) {
                Pizza pizza = pizzasPedidos.get(i);
                String recheios = String.join(", ", pizza.getRecheios().keySet());
                System.out.println((lanchesPedidos.size() + i + 1) + " - Pizza " + 
                                 pizza.getTamanho() + 
                                 " (Massa: " + pizza.getTipoMassa() + 
                                 ", Recheios: " + recheios + 
                                 ") - R$ " + String.format("%.2f", pizza.getPrecoTotal()));
            }
        }
        
        System.out.println("\n0 - Voltar ao menu principal");
        System.out.print("\nEscolha o número do pedido para clonar: ");
        
        int opcao = lerOpcaoNumerica();
        
        if (opcao == 0) {
            return;
        }
        
        // Verifica se é um lanche
        if (opcao >= 1 && opcao <= lanchesPedidos.size()) {
            Lanche lancheOriginal = lanchesPedidos.get(opcao - 1);
            System.out.println("\n✓ Clonando lanche...");
            
            Lanche.LancheBuilder builder = lancheOriginal.clonar();
            
            try {
                Lanche novoLanche = builder.build();
                System.out.println("\n" + novoLanche);
                System.out.println("✓ Lanche clonado e adicionado ao pedido!");
            } catch (IllegalStateException e) {
                System.out.println("\nErro ao clonar: " + e.getMessage());
            }
            
        } else if (opcao > lanchesPedidos.size() && opcao <= totalPedidos) {
            // É uma pizza
            int pizzaIndex = opcao - lanchesPedidos.size() - 1;
            Pizza pizzaOriginal = pizzasPedidos.get(pizzaIndex);
            System.out.println("\n✓ Clonando pizza...");
            
            Pizza.PizzaBuilder builder = pizzaOriginal.clonar();
            
            try {
                Pizza novaPizza = builder.build();
                System.out.println("\n" + novaPizza);
                System.out.println("✓ Pizza clonada e adicionada ao pedido!");
            } catch (IllegalStateException e) {
                System.out.println("\nErro ao clonar: " + e.getMessage());
            }
            
        } else {
            System.out.println("\nOpção inválida! Tente novamente.");
        }
        
        System.out.print("\nPressione Enter para voltar...");
        scanner.nextLine();
    }


    private static void visualizarPedidos() {
        System.out.println("\n------ VISUALIZAR PEDIDOS ------");
        
        GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();
        List<Lanche> lanchesPedidos = gerenciador.getLanchesPedidos();
        List<Pizza> pizzasPedidos = gerenciador.getPizzasPedidos();
        
        int totalPedidos = gerenciador.getTotalPedidos();
        
        if (totalPedidos == 0) {
            System.out.println("\nNenhum pedido realizado ainda.");
            System.out.print("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        
        double totalVendas = gerenciador.getTotalVendas();
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("RESUMO DE PEDIDOS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("  • Lanches: " + lanchesPedidos.size());
        System.out.println("  • Pizzas: " + pizzasPedidos.size());
        
        if (!lanchesPedidos.isEmpty()) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║           LISTA DE LANCHES            ║");
            System.out.println("╚═══════════════════════════════════════╝");
            int contador = 1;
            for (Lanche lanche : lanchesPedidos) {
                System.out.println("\n┌─ Lanche #" + contador++ + " ─────────────────────────────┐");
                System.out.print(lanche);
                System.out.println("└───────────────────────────────────────────┘");
            }
        }
        
        if (!pizzasPedidos.isEmpty()) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║           LISTA DE PIZZAS             ║");
            System.out.println("╚═══════════════════════════════════════╝");
            int contador = 1;
            for (Pizza pizza : pizzasPedidos) {
                System.out.println("\n┌─ Pizza #" + contador++ + " ──────────────────────────────┐");
                System.out.print(pizza);
                System.out.println("└───────────────────────────────────────────┘");
            }
        }
        
        if (totalPedidos > 1) {
            System.out.println("\n═══════════════════════════════════════");
            System.out.println("         TOTAL GERAL DOS PEDIDOS        ");
            System.out.println("VALOR TOTAL: R$ " + String.format("%.2f", totalVendas));
            System.out.println("═══════════════════════════════════════");
        }
        
        System.out.println("Fim da listagem de pedidos");
        System.out.print("\nPressione Enter para voltar...");
        scanner.nextLine();
    }
}
