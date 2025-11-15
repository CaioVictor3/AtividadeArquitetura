package com.lanchonete;

import com.lanchonete.factory.IngredienteFactory;
import com.lanchonete.model.Lanche;
import com.lanchonete.model.Pizza;

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
            System.out.println("3 - Visualizar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("\nOpção: ");

            int opcao = lerOpcaoNumerica();

            switch (opcao) {
                case 1 -> montarLanche();
                case 2 -> montarPizza();
                case 3 -> visualizarPedidos();
                case 0 -> {
                    continuar = false;
                    System.out.println("\nObrigado! Volte sempre!");
                }
                default -> System.out.println("\nOpção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }


    private static int lerOpcaoNumerica() {
        while (true) {
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número: ");
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

        switch (tamanhoOpcao) {
            case 1 -> builder.escolherTamanho("Pequeno");
            case 2 -> builder.escolherTamanho("Medio");
            case 3 -> builder.escolherTamanho("Grande");
            default -> builder.escolherTamanho("Medio");
        }

        // Pão
        System.out.println("\nEscolha o tipo de pão:");
        System.out.println("1 - Australiano");
        System.out.println("2 - Integral");
        System.out.println("3 - Brioche");
        System.out.println("4 - Italiano");
        System.out.print("Opção: ");
        String paoOpcao = scanner.nextLine();
        builder.comTipoPao(IngredienteFactory.criarPao(paoOpcao));

        // Recheio
        System.out.println("\nEscolha o recheio:");
        System.out.println("1 - Frango");
        System.out.println("2 - Carne");
        System.out.println("3 - Calabresa");
        System.out.println("4 - Vegetariano");
        System.out.print("Opção: ");
        String recheioOpcao = scanner.nextLine();
        builder.comRecheio(IngredienteFactory.criarRecheio(recheioOpcao));

        // Queijo extra
        System.out.print("\nDeseja queijo extra? (s/n): ");
        builder.comQueijoExtra(scanner.nextLine().equalsIgnoreCase("s"));

        // Ingredientes adicionais
        System.out.println("\nIngredientes adicionais (digite 'fim' para encerrar):");
        System.out.println("Opções: Alface, Tomate, Cebola, Picles, Bacon, Ovo");

        while (true) {
            System.out.print("Ingrediente: ");
            String ing = scanner.nextLine();
            if (ing.equalsIgnoreCase("fim")) break;
            builder.adicionarIngrediente(ing);
        }

        // Molhos
        System.out.print("\nDeseja adicionar molhos? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Opções: 1-Barbecue, 2-Mostarda, 3-Maionese, 4-Picante");
            System.out.print("Molho: ");
            builder.adicionarMolho(IngredienteFactory.criarMolho(scanner.nextLine()));
        }

        // Acompanhamentos
        System.out.println("\nAcompanhamentos (digite 'fim' para encerrar):");
        System.out.println("Opções: Batata Frita, Anéis de Cebola, Nuggets");

        while (true) {
            System.out.print("Acompanhamento: ");
            String ac = scanner.nextLine();
            if (ac.equalsIgnoreCase("fim")) break;
            builder.adicionarAcompanhamento(ac);
        }

        try {
            Lanche lanche = builder.build();
            System.out.println("\n" + lanche);
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

        switch (tamanhoOpcao) {
            case 1 -> builder.escolherTamanho("Pequena");
            case 2 -> builder.escolherTamanho("Media");
            case 3 -> builder.escolherTamanho("Grande");
            case 4 -> builder.escolherTamanho("Familia");
            default -> builder.escolherTamanho("Media");
        }


        System.out.println("\nEscolha o tipo de massa:");
        System.out.println("1 - Fina");
        System.out.println("2 - Tradicional");
        System.out.println("3 - Pan");
        System.out.println("4 - Integral");
        System.out.print("Opção: ");
        builder.comTipoMassa(IngredienteFactory.criarMassa(scanner.nextLine()));


        System.out.println("\nEscolha os recheios (digite 'fim' para encerrar):");
        System.out.println("Opções: 1-Frango, 2-Carne, 3-Calabresa, 4-Vegetariano");
        System.out.println("Ou digite o nome direto: Pepperoni, Presunto, etc.");

        while (true) {
            System.out.print("Recheio: ");
            String r = scanner.nextLine();
            if (r.equalsIgnoreCase("fim")) break;

            if (r.matches("\\d"))
                builder.adicionarRecheio(IngredienteFactory.criarRecheio(r));
            else
                builder.adicionarRecheio(r);
        }


        System.out.print("\nDeseja queijo extra? (s/n): ");
        builder.comQueijoExtra(scanner.nextLine().equalsIgnoreCase("s"));

 
        System.out.print("\nDeseja adicionar molhos? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Opções: 1-Barbecue, 2-Mostarda, 3-Maionese, 4-Picante");
            System.out.print("Molho: ");
            builder.adicionarMolho(IngredienteFactory.criarMolho(scanner.nextLine()));
        }


        System.out.println("\nExtras (digite 'fim' para encerrar):");
        System.out.println("Opções: Bacon, Azeitona, Champignon, Pimentão");
        while (true) {
            System.out.print("Extra: ");
            String ex = scanner.nextLine();
            if (ex.equalsIgnoreCase("fim")) break;
            builder.adicionarExtra(ex);
        }

        try {
            Pizza pizza = builder.build();
            System.out.println("\n" + pizza);
        } catch (IllegalStateException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }


    private static void visualizarPedidos() {
        System.out.println("\n------ VISUALIZAR PEDIDOS ------");

        while (true) {
            System.out.print("Os pedidos totais serão exibidos aqui. Digite 'fim' para voltar: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("fim"))
                break;
        }
    }
}
