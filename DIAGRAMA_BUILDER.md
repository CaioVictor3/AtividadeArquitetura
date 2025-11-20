# Diagrama do Padrão Builder - Sistema de Lanchonete

## Estrutura de Classes

```
┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                           Lanche                                │
├─────────────────────────────────────────────────────────────────┤
│ - tamanho: String                                               │
│ - tipoPao: String                                               │
│ - recheio: String                                               │
│ - ingredientes: Map<String, Integer>                            │
│ - queijoExtra: boolean                                          │
│ - molhos: Map<String, Integer>                                  │
│ - acompanhamentos: Map<String, Integer>                         │
│ - precoBase: double                                             │
│ - precoTotal: double                                            │
├─────────────────────────────────────────────────────────────────┤
│ - Lanche()                              [CONSTRUTOR PRIVADO]    │
│ + getTamanho(): String                                          │
│ + getTipoPao(): String                                          │
│ + getRecheio(): String                                          │
│ + getIngredientes(): Map<String, Integer>                       │
│ + isQueijoExtra(): boolean                                      │
│ + getMolhos(): Map<String, Integer>                             │
│ + getAcompanhamentos(): Map<String, Integer>                    │
│ + getPrecoTotal(): double                                       │
│ + clonar(): LancheBuilder                                       │
│ + toString(): String                                            │
├─────────────────────────────────────────────────────────────────┤
│                    <<static inner class>>                       │
│                       LancheBuilder                             │
├─────────────────────────────────────────────────────────────────┤
│ - lanche: Lanche                                                │
├─────────────────────────────────────────────────────────────────┤
│ + LancheBuilder()                                               │
│ + escolherTamanho(String): LancheBuilder                        │
│ + comTipoPao(String): LancheBuilder                             │
│ + comRecheio(String): LancheBuilder                             │
│ + adicionarIngrediente(String): LancheBuilder                   │
│ + comQueijoExtra(boolean): LancheBuilder                        │
│ + adicionarMolho(String): LancheBuilder                         │
│ + adicionarAcompanhamento(String): LancheBuilder                │
│ + build(): Lanche                       [VALIDAÇÕES]            │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                            Pizza                                │
├─────────────────────────────────────────────────────────────────┤
│ - tamanho: String                                               │
│ - tipoMassa: String                                             │
│ - recheios: Map<String, Integer>                                │
│ - queijoExtra: boolean                                          │
│ - molhos: Map<String, Integer>                                  │
│ - extras: Map<String, Integer>                                  │
│ - precoBase: double                                             │
│ - precoTotal: double                                            │
├─────────────────────────────────────────────────────────────────┤
│ - Pizza()                               [CONSTRUTOR PRIVADO]    │
│ + getTamanho(): String                                          │
│ + getTipoMassa(): String                                        │
│ + getRecheios(): Map<String, Integer>                           │
│ + isQueijoExtra(): boolean                                      │
│ + getMolhos(): Map<String, Integer>                             │
│ + getExtras(): Map<String, Integer>                             │
│ + getPrecoTotal(): double                                       │
│ + clonar(): PizzaBuilder                                        │
│ + toString(): String                                            │
├─────────────────────────────────────────────────────────────────┤
│                    <<static inner class>>                       │
│                        PizzaBuilder                             │
├─────────────────────────────────────────────────────────────────┤
│ - pizza: Pizza                                                  │
├─────────────────────────────────────────────────────────────────┤
│ + PizzaBuilder()                                                │
│ + escolherTamanho(String): PizzaBuilder                         │
│ + comTipoMassa(String): PizzaBuilder                            │
│ + adicionarRecheio(String): PizzaBuilder                        │
│ + comQueijoExtra(boolean): PizzaBuilder                         │
│ + adicionarMolho(String): PizzaBuilder                          │
│ + adicionarExtra(String): PizzaBuilder                          │
│ + build(): Pizza                        [VALIDAÇÕES]            │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                    IngredienteFactory                           │
│                    [FACTORY METHOD]                             │
├─────────────────────────────────────────────────────────────────┤
│ + criarPao(String): String              [STATIC]                │
│ + criarMassa(String): String            [STATIC]                │
│ + criarRecheio(String): String          [STATIC]                │
│ + criarMolho(String): String            [STATIC]                │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                    GerenciadorPedidos                           │
│                       [SINGLETON]                               │
├─────────────────────────────────────────────────────────────────┤
│ - static instancia: GerenciadorPedidos                          │
│ - lanchesPedidos: List<Lanche>                                  │
│ - pizzasPedidos: List<Pizza>                                    │
├─────────────────────────────────────────────────────────────────┤
│ - GerenciadorPedidos()                  [CONSTRUTOR PRIVADO]    │
│ + static getInstancia(): GerenciadorPedidos  [SYNCHRONIZED]     │
│ + adicionarLanche(Lanche): void                                 │
│ + adicionarPizza(Pizza): void                                   │
│ + getLanchesPedidos(): List<Lanche>                             │
│ + getPizzasPedidos(): List<Pizza>                               │
│ + getTotalPedidos(): int                                        │
│ + getTotalVendas(): double                                      │
│ + limparPedidos(): void                                         │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                            Main                                 │
├─────────────────────────────────────────────────────────────────┤
│ - static scanner: Scanner                                       │
├─────────────────────────────────────────────────────────────────┤
│ + static main(String[]): void                                   │
│ - static montarLanche(): void                                   │
│ - static montarPizza(): void                                    │
│ - static visualizarPedidos(): void                              │
│ - static lerOpcaoNumerica(): int                                │
│ - static lerSimOuNao(): boolean                                 │
└─────────────────────────────────────────────────────────────────┘
```

## Relações entre Classes

```
Main
  │
  ├──> usa ─────> Lanche.LancheBuilder ────builds───> Lanche
  │                      │
  │                      └──> usa ─> IngredienteFactory
  │
  ├──> usa ─────> Pizza.PizzaBuilder ─────builds───> Pizza
  │                      │
  │                      └──> usa ─> IngredienteFactory
  │
  └──> usa ─────> GerenciadorPedidos (Singleton)
                         │
                         ├──> armazena ─> List<Lanche>
                         └──> armazena ─> List<Pizza>

LancheBuilder.build() ──> adiciona em ──> GerenciadorPedidos
PizzaBuilder.build()  ──> adiciona em ──> GerenciadorPedidos
```

## Fluxo de Construção (Builder Pattern)

```
┌─────────────────────────────────────────────────────────────────┐
│  1. CRIAÇÃO DO BUILDER                                          │
└─────────────────────────────────────────────────────────────────┘
                            │
                            ▼
        Lanche.LancheBuilder builder = new Lanche.LancheBuilder();
                            │
                            │ (builder contém instância privada de Lanche)
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│  2. CONFIGURAÇÃO FLUENTE (Métodos retornam 'this')              │
└─────────────────────────────────────────────────────────────────┘
                            │
                            ▼
        builder.escolherTamanho("Grande")          // retorna builder
               .comTipoPao("Brioche")              // retorna builder
               .comRecheio("Frango")               // retorna builder
               .comQueijoExtra(true)               // retorna builder
               .adicionarIngrediente("Alface")     // retorna builder
               .adicionarMolho("Barbecue")         // retorna builder
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│  3. CONSTRUÇÃO E VALIDAÇÃO (Método build())                     │
└─────────────────────────────────────────────────────────────────┘
                            │
                            ▼
        Lanche lanche = builder.build();
                            │
                            ├─> Valida campos obrigatórios
                            ├─> Valida regras de negócio
                            ├─> Calcula preço total
                            ├─> Adiciona ao GerenciadorPedidos (Singleton)
                            └─> Retorna objeto Lanche pronto
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│  4. OBJETO FINAL IMUTÁVEL                                       │
└─────────────────────────────────────────────────────────────────┘
```

## Validações no Build

```
builder.build()
    │
    ├─> if (tamanho == null)
    │       throw IllegalStateException("Tamanho é obrigatório")
    │
    ├─> if (tipoPao == null)
    │       throw IllegalStateException("Tipo de pão é obrigatório")
    │
    ├─> if (recheio == null)
    │       throw IllegalStateException("Recheio é obrigatório")
    │
    ├─> if (recheio == "Vegetariano" && ingredientes.contains("Bacon"))
    │       throw IllegalStateException("Vegetariano não pode ter bacon")
    │
    ├─> Calcula preço total
    │     precoTotal = precoBase
    │                 + (ingredientes.count * 2.50)
    │                 + (molhos.count * 1.00)
    │                 + (acompanhamentos.count * 3.00)
    │
    ├─> GerenciadorPedidos.getInstancia().adicionarLanche(lanche)
    │
    └─> return lanche
```

## Integração dos Padrões

```
┌─────────────────────────────────────────────────────────────────┐
│                    USUÁRIO (Main)                               │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ escolhe opção de ingrediente
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│            FACTORY METHOD (IngredienteFactory)                  │
│  criarPao("1") -> "Pão Australiano"                             │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ retorna ingrediente padronizado
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                BUILDER (LancheBuilder)                          │
│  builder.comTipoPao("Pão Australiano")                          │
│  builder.comRecheio("Frango")                                   │
│  ... outras configurações ...                                   │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ build()
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│             PRODUTO (Lanche) - VALIDADO                         │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ automaticamente adicionado
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│          SINGLETON (GerenciadorPedidos)                         │
│  - Armazena todos os pedidos                                    │
│  - Calcula totais                                               │
└─────────────────────────────────────────────────────────────────┘
```

## Exemplo de Uso Completo

```java
// 1. Main obtém entrada do usuário
String opcaoPao = scanner.nextLine(); // "1"

// 2. Factory Method cria ingrediente padronizado
String pao = IngredienteFactory.criarPao(opcaoPao); // "Pão Australiano"

// 3. Builder constrói objeto passo a passo
Lanche.LancheBuilder builder = new Lanche.LancheBuilder();
builder.escolherTamanho("Grande")           // configura tamanho
       .comTipoPao(pao)                     // configura pão (via factory)
       .comRecheio("Frango")                // configura recheio
       .comQueijoExtra(true)                // adiciona queijo extra
       .adicionarIngrediente("Alface")      // adiciona ingrediente
       .adicionarMolho("Barbecue");         // adiciona molho

// 4. Build valida, calcula preço e cria objeto
Lanche lanche = builder.build();
// Validações executadas ✓
// Preço calculado ✓
// Adicionado ao Singleton ✓

// 5. Exibe resultado
System.out.println(lanche); // toString() formatado

// 6. Singleton gerencia todos os pedidos
GerenciadorPedidos gerenciador = GerenciadorPedidos.getInstancia();
System.out.println("Total de pedidos: " + gerenciador.getTotalPedidos());
System.out.println("Total vendas: R$ " + gerenciador.getTotalVendas());
```

## Vantagens Demonstradas

```
┌────────────────────────────────────────────────────────────────┐
│  PROBLEMA: Telescoping Constructor Anti-Pattern                │
├────────────────────────────────────────────────────────────────┤
│  Sem Builder seria necessário:                                 │
│  - Lanche(String tamanho, String pao)                          │
│  - Lanche(String tamanho, String pao, String recheio)          │
│  - Lanche(String tamanho, String pao, String recheio, boolean) │
│  - ... dezenas de combinações                                  │
└────────────────────────────────────────────────────────────────┘
                            │
                            │ SOLUÇÃO: Builder Pattern
                            ▼
┌────────────────────────────────────────────────────────────────┐
│  ✅ Um único Builder flexível                                  │
│  ✅ Configuração clara e legível                               │
│  ✅ Validações centralizadas                                   │
│  ✅ Métodos auto-documentados                                  │
│  ✅ Imutabilidade garantida                                    │
└────────────────────────────────────────────────────────────────┘
```

## Comparação de Código

### ❌ SEM Builder (Problemático)

```java
// Difícil de entender
Lanche lanche = new Lanche("Grande", "Brioche", "Frango", true, 
                           Arrays.asList("Alface", "Tomate"),
                           Arrays.asList("Barbecue"),
                           Arrays.asList("Batata"));

// Ordem dos parâmetros confusa
// Impossível adicionar ingrediente múltiplas vezes
// Validações espalhadas
```

### ✅ COM Builder (Implementado)

```java
// Claro e legível
Lanche lanche = new Lanche.LancheBuilder()
    .escolherTamanho("Grande")           // auto-explicativo
    .comTipoPao("Brioche")               // auto-explicativo
    .comRecheio("Frango")                // auto-explicativo
    .comQueijoExtra(true)                // auto-explicativo
    .adicionarIngrediente("Alface")      // pode adicionar múltiplos
    .adicionarIngrediente("Tomate")      // pode adicionar múltiplos
    .adicionarMolho("Barbecue")
    .adicionarAcompanhamento("Batata Frita")
    .build();                            // validações aqui

// Interface fluente
// Validações centralizadas
// Flexível para adições
```

## Princípios SOLID Aplicados

```
S - Single Responsibility
    ├─ Lanche: representa o produto
    ├─ LancheBuilder: constrói o produto
    ├─ IngredienteFactory: cria ingredientes
    └─ GerenciadorPedidos: gerencia pedidos

O - Open/Closed
    └─ Extensível via Factory (novos ingredientes)
       sem modificar código existente

L - Liskov Substitution
    └─ N/A (não usa herança)

I - Interface Segregation
    └─ Interfaces específicas para cada responsabilidade

D - Dependency Inversion
    └─ Builder usa abstrações (não implementações concretas)
```

## Thread Safety (Singleton)

```java
public static synchronized GerenciadorPedidos getInstancia() {
    if (instancia == null) {
        instancia = new GerenciadorPedidos();
    }
    return instancia;
}

// synchronized garante que apenas uma thread
// pode executar este método por vez
// evitando criação de múltiplas instâncias
```

---

**Conclusão:** Este diagrama demonstra a implementação completa e correta do Padrão Builder, integrado com Factory Method e Singleton, seguindo as melhores práticas de design orientado a objetos.
