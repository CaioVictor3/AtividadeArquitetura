package com.lanchonete.factory;

public class IngredienteFactory {
    
    public static String criarPao(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1":
            case "australiano":
                return "Pão Australiano";
            case "2":
            case "integral":
                return "Pão Integral";
            case "3":
            case "brioche":
                return "Pão Brioche";
            case "4":
            case "italiano":
                return "Pão Italiano";
            default:
                return "Pão Tradicional";
        }
    }


    public static String criarMassa(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1":
            case "fina":
                return "Massa Fina";
            case "2":
            case "tradicional":
                return "Massa Tradicional";
            case "3":
            case "pan":
                return "Massa Pan";
            case "4":
            case "integral":
                return "Massa Integral";
            default:
                return "Massa Tradicional";
        }
    }


    public static String criarRecheio(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1":
            case "frango":
                return "Frango";
            case "2":
            case "carne":
                return "Carne";
            case "3":
            case "calabresa":
                return "Calabresa";
            case "4":
            case "vegetariano":
                return "Vegetariano";
            default:
                return "Tradicional";
        }
    }


    public static String criarMolho(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1":
            case "barbecue":
                return "Barbecue";
            case "2":
            case "mostarda":
                return "Mostarda";
            case "3":
            case "maionese":
                return "Maionese";
            case "4":
            case "picante":
                return "Picante";
            default:
                return "Tradicional";
        }
    }
}

