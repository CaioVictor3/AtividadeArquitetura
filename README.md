# Sistema de Pedidos de Lanches - Padrões de Projeto GoF

## Descrição do Projeto

Sistema de montagem de pedidos para uma lanchonete, onde cada pedido pode ser customizado pelo cliente. O sistema permite construir lanches ou pizzas personalizados especificando opções como tamanho, tipo de massa/pão, proteínas/recheios, queijo extra, molhos, acompanhamentos, etc.

## Padrões de Projeto Implementados

Este projeto implementa **três padrões de projeto GoF (Gang of Four)**:

### 1. Builder (Padrão Principal) ✅

**Classes:** `Lanche.LancheBuilder`, `Pizza.PizzaBuilder`

**Problema Resolvido:**
- Evita a criação de múltiplos construtores com diferentes combinações de parâmetros
- Permite construção flexível e passo a passo de objetos complexos
- Facilita a leitura e manutenção do código de construção

**Implementação:**
- As classes `Lanche` e `Pizza` possuem construtores privados
- Builders internos estáticos fornecem métodos fluentes para configuração
- Exemplo de uso:
```java
Lanche lanche = new Lanche.LancheBuilder()
    .escolherTamanho("Grande")
    .comTipoPao("Brioche")
    .comRecheio("Frango")
    .comQueijoExtra(true)
    .adicionarMolho("Barbecue")
    .build();
```

**Validações Implementadas:**
- Tamanho e recheio obrigatórios
- Validação de dependências (ex: pedido vegetariano não pode conter bacon)
- Validação de limites (ex: máximo de 5 recheios em pizza)

### 2. Factory Method (Padrão Adicional) ✅

**Classe:** `IngredienteFactory`

**Problema Resolvido:**
- Centraliza a criação de ingredientes padronizados
- Facilita a manutenção e extensão de tipos de ingredientes
- Desacopla a criação de objetos do uso

**Implementação:**
- Métodos estáticos factory para criar tipos padronizados:
  - `criarPao(String tipo)`: Cria tipos de pão (Australiano, Integral, Brioche, Italiano)
  - `criarMassa(String tipo)`: Cria tipos de massa (Fina, Tradicional, Pan, Integral)
  - `criarRecheio(String tipo)`: Cria recheios padrão (Frango, Carne, Calabresa, Vegetariano)
  - `criarMolho(String tipo)`: Cria molhos padronizados (Barbecue, Mostarda, Maionese, Picante)

**Exemplo de uso:**
```java
String pao = IngredienteFactory.criarPao("1"); 
String molho = IngredienteFactory.criarMolho("barbecue"); 
```

### 4. Clonagem de Pedidos (Funcionalidade Adicional) ✅

**Métodos:** `Lanche.clonar()`, `Pizza.clonar()`

**Problema Resolvido:**
- Permite reutilizar configurações de pedidos anteriores
- Facilita quando o cliente quer pedir o mesmo item novamente
- Evita reconfiguração manual completa

**Implementação:**
- Métodos `clonar()` que retornam um novo builder pré-configurado
- Mantém todas as configurações do pedido original
- Permite modificações antes de construir o novo pedido

**Exemplo de uso:**
```java
Lanche lancheOriginal = builder.build();
// Cliente quer o mesmo lanche novamente
Lanche.LancheBuilder builderClonado = lancheOriginal.clonar();
// Pode modificar se desejar antes de construir
Lanche novoLanche = builderClonado.build();
```

### 3. Singleton (Padrão Adicional) ✅

**Classe:** `GerenciadorPedidos`

**Problema Resolvido:**
- Garante uma única instância global para gerenciar todos os pedidos
- Centraliza o controle de pedidos em toda a aplicação
- Evita criação desnecessária de múltiplos gerenciadores

**Implementação:**
- Construtor privado
- Método `getInstancia()` sincronizado (thread-safe)
- Armazena listas de lanches e pizzas pedidos
- Fornece métodos para adicionar pedidos, obter listas e calcular totais

**Exemplo de uso:**
```java
GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();
gerenciador.adicionarLanche(lanche);
List<Lanche> pedidos = gerenciador.getLanchesPedidos();
double totalVendas = gerenciador.getTotalVendas();
```

## Sistema de Adicionais com Quantidades

O sistema permite adicionar múltiplos itens do mesmo tipo, com contagem automática de quantidades:

- **Seleção por Número:** O usuário escolhe adicionais através de números (ex: 1, 2, 3)
- **Validação:** Números inválidos exibem mensagem de erro clara
- **Contagem Automática:** Quando o mesmo número é escolhido múltiplas vezes, a quantidade é incrementada
- **Exibição com Multiplicadores:** Itens repetidos aparecem como "nome (2x)", "nome (3x)", etc.

**Exemplo:**
- Usuário escolhe molho "1" (Barbecue) duas vezes
- O sistema exibe: "Molhos: Barbecue (2x)"

## Estrutura do Projeto

```
src/main/java/com/lanchonete/
├── Main.java                          # Interface console interativa
├── model/
│   ├── Lanche.java                    # Produto final - Lanche com Builder
│   └── Pizza.java                     # Produto final - Pizza com Builder
├── factory/
│   └── IngredienteFactory.java        # Factory Method para ingredientes
└── service/
    └── GerenciadorPedidos.java        # Singleton para gerenciar pedidos
```

## Funcionalidades

### Menu Principal
1. **Montar Lanche** - Constrói um lanche personalizado usando Builder
2. **Montar Pizza** - Constrói uma pizza personalizada usando Builder
3. **Clonar Pedido Anterior** - Clona um lanche ou pizza já pedido (usa método `clonar()`)
4. **Visualizar Pedidos** - Lista todos os pedidos usando Singleton
0. **Sair**

### Características dos Pedidos

**Lanche:**
- Tamanho: Pequeno (R$ 15,00), Médio (R$ 20,00), Grande (R$ 25,00)
- Tipo de pão: Australiano, Integral, Brioche, Italiano (via Factory Method)
- Recheio: Frango, Carne, Calabresa, Vegetariano (via Factory Method)
- Ingredientes adicionais (por número): 1-Alface, 2-Tomate, 3-Cebola, 4-Picles, 5-Bacon, 6-Ovo
- Queijo extra opcional
- Molhos (por número): 1-Barbecue, 2-Mostarda, 3-Maionese, 4-Picante
- Acompanhamentos (por número): 1-Batata Frita, 2-Anéis de Cebola, 3-Nuggets

**Pizza:**
- Tamanho: Pequena (R$ 25,00), Média (R$ 35,00), Grande (R$ 45,00), Família (R$ 55,00)
- Tipo de massa: Fina, Tradicional, Pan, Integral (via Factory Method)
- Recheios múltiplos (por número): 1-Frango, 2-Carne, 3-Calabresa, 4-Vegetariano, 5-Pepperoni, 6-Presunto, 7-Queijo (máximo 5 tipos diferentes)
- Queijo extra opcional
- Molhos (por número): 1-Barbecue, 2-Mostarda, 3-Maionese, 4-Picante
- Extras (por número): 1-Bacon, 2-Azeitona, 3-Champignon, 4-Pimentão

**Validações:**
- Pedido vegetariano não pode conter bacon ou ovo
- Pizza vegetariana não pode conter bacon
- Máximo de 5 recheios em pizza

## Como Executar

### Requisitos
- Java JDK 8 ou superior

### Compilação

```bash
cd src/main/java
javac com/lanchonete/**/*.java
```

### Execução

```bash
java com.lanchonete.Main
```

### Uso no IDE

1. Importe o projeto em sua IDE Java favorita (Eclipse, IntelliJ IDEA, VS Code)
2. Execute a classe `Main.java`

## Exemplos de Uso dos Padrões

### Builder

```java
// Construção fluente de um lanche
Lanche lanche = new Lanche.LancheBuilder()
    .escolherTamanho("Grande")
    .comTipoPao("Brioche")
    .comRecheio("Frango")
    .comQueijoExtra(true)
    .adicionarIngrediente("Alface")
    .adicionarIngrediente("Tomate")
    .adicionarMolho("Barbecue")
    .adicionarAcompanhamento("Batata Frita")
    .build();
```

### Factory Method

```java
// Criação de ingredientes padronizados
String pao = IngredienteFactory.criarPao("1"); // Pão Australiano
String recheio = IngredienteFactory.criarRecheio("frango"); // Frango
String molho = IngredienteFactory.criarMolho("barbecue"); // Barbecue
```

### Singleton

```java
// Obtenção da instância única do gerenciador
GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();

// Adição de pedidos
gerenciador.adicionarLanche(lanche);
gerenciador.adicionarPizza(pizza);

// Consultas
List<Lanche> lanches = gerenciador.getLanchesPedidos();
double total = gerenciador.getTotalVendas();
```


## Diagramas de Classe

### Padrão Builder
```
Lanche
├── - tamanho: String
├── - tipoPao: String
├── - recheio: String
├── ...
└── + static class LancheBuilder
    ├── - lanche: Lanche
    ├── + escolherTamanho(String): LancheBuilder
    ├── + comTipoPao(String): LancheBuilder
    ├── + comRecheio(String): LancheBuilder
    └── + build(): Lanche
```

### Padrão Factory Method
```
IngredienteFactory
├── + static criarPao(String): String
├── + static criarMassa(String): String
├── + static criarRecheio(String): String
└── + static criarMolho(String): String
```

### Padrão Singleton
```
GerenciadorPedidos
├── - static instancia: GerenciadorPedidos
├── - lanchesPedidos: List<Lanche>
├── - pizzasPedidos: List<Pizza>
├── - GerenciadorPedidos() [private]
├── + static getInstancia(): GerenciadorPedidos
├── + adicionarLanche(Lanche): void
└── + adicionarPizza(Pizza): void
```


## Justificativa dos Padrões

### Builder
**Por que foi usado:** O Builder resolve o problema de construir objetos complexos com muitos atributos opcionais. Sem ele, precisaríamos de múltiplos construtores ou métodos com muitos parâmetros, tornando o código difícil de manter e ler.

### Factory Method
**Por que foi usado:** Centraliza a criação de ingredientes padronizados, facilitando manutenção e garantindo consistência. Se novos tipos de ingredientes forem adicionados, só precisamos modificar a factory.

### Singleton
**Por que foi usado:** Garante uma única fonte de verdade para todos os pedidos na aplicação. Evita problemas de sincronização e garante que todas as partes do sistema trabalhem com o mesmo conjunto de pedidos.


## Testes

O sistema inclui interface interativa no console para testar todas as funcionalidades:

1. **Teste Builder:** Monte diferentes combinações de lanches e pizzas
2. **Teste Factory Method:** Use os menus que utilizam a factory para criar ingredientes
3. **Teste Singleton:** Crie vários pedidos e visualize-os - todos estarão na mesma lista
4. **Teste Validações:** Tente criar um pedido vegetariano com bacon para ver a validação
5. **Teste Clonagem (NOVA FUNCIONALIDADE):** 
   - Faça um pedido de lanche ou pizza
   - Escolha a opção "3 - Clonar Pedido Anterior"
   - Selecione o pedido que deseja clonar
   - Opcionalmente, modifique o pedido clonado (tamanho, ingredientes extras)
   - O sistema usa o método `clonar()` para criar uma cópia do builder pré-configurado

## Princípios de Design Aplicados

- **Alta Coesão:** Cada classe tem responsabilidades bem definidas
- **Baixo Acoplamento:** Uso de interfaces e padrões reduz dependências
- **DRY (Don't Repeat Yourself):** Factory Method evita repetição de lógica de criação
- **Open/Closed Principle:** Sistema extensível através de factories e builders
- **Single Responsibility:** Cada padrão resolve um problema específico


