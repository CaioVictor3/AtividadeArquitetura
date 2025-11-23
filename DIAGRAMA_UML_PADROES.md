# Diagrama UML de Classes - Padrões de Projeto

> **Diagrama focado nos padrões Builder, Singleton e Factory Method**  
> (Exclui a classe Main para enfatizar a arquitetura de domínio)

---

## 📊 Estrutura de Classes

### **Lanche (Produto)**

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
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ ◆ COMPOSIÇÃO
                            │ (inner class)
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                    <<static inner class>>                       │
│                       LancheBuilder                             │
├─────────────────────────────────────────────────────────────────┤
│ - lanche: Lanche                        [ASSOCIAÇÃO]            │
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
```

---

### **Pizza (Produto)**

```
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
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ ◆ COMPOSIÇÃO
                            │ (inner class)
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                    <<static inner class>>                       │
│                        PizzaBuilder                             │
├─────────────────────────────────────────────────────────────────┤
│ - pizza: Pizza                          [ASSOCIAÇÃO]            │
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
```

---

### **IngredienteFactory (Factory Method)**

```
┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                    IngredienteFactory                           │
│                    <<Factory Method>>                           │
├─────────────────────────────────────────────────────────────────┤
│ + criarPao(String): String              [STATIC]                │
│ + criarMassa(String): String            [STATIC]                │
│ + criarRecheio(String): String          [STATIC]                │
│ + criarMolho(String): String            [STATIC]                │
└─────────────────────────────────────────────────────────────────┘
```

---

### **GerenciadorPedidos (Singleton)**

```
┌─────────────────────────────────────────────────────────────────┐
│                          <<class>>                              │
│                    GerenciadorPedidos                           │
│                       <<Singleton>>                             │
├─────────────────────────────────────────────────────────────────┤
│ - instancia: GerenciadorPedidos         [STATIC - sublinhado]  │
│ - lanchesPedidos: List<Lanche>                                  │
│ - pizzasPedidos: List<Pizza>                                    │
├─────────────────────────────────────────────────────────────────┤
│ - GerenciadorPedidos()                  [CONSTRUTOR PRIVADO]    │
│ + getInstancia(): GerenciadorPedidos    [STATIC - sublinhado]  │
│ + adicionarLanche(Lanche): void                                 │
│ + adicionarPizza(Pizza): void                                   │
│ + getLanchesPedidos(): List<Lanche>                             │
│ + getPizzasPedidos(): List<Pizza>                               │
│ + getTotalPedidos(): int                                        │
│ + getTotalVendas(): double                                      │
│ + limparPedidos(): void                                         │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🔗 Relacionamentos entre Classes

### **Diagrama de Relacionamentos**

```
                    ┌─────────────────────┐
                    │IngredienteFactory   │
                    │   <<Factory>>       │
                    └──────────┬──────────┘
                               │
                               │ DEPENDÊNCIA
                               │ (<<use>>)
                ┌──────────────┼──────────────┐
                │              │              │
                ▼              ▼              ▼
        ┌──────────────┐              ┌──────────────┐
        │LancheBuilder │              │ PizzaBuilder │
        │  <<builder>> │              │  <<builder>> │
        └──────┬───────┘              └──────┬───────┘
               │                             │
               │ ASSOCIAÇÃO                  │ ASSOCIAÇÃO
               │ (cria/retorna)              │ (cria/retorna)
               │ 1                           │ 1
               ▼                             ▼
        ┌──────────────┐              ┌──────────────┐
        │    Lanche    │              │     Pizza    │
        │  <<produto>> │              │  <<produto>> │
        └──────┬───────┘              └──────┬───────┘
               │                             │
               │ AGREGAÇÃO                   │ AGREGAÇÃO
               │ (é armazenado)              │ (é armazenado)
               │ 0..*                        │ 0..*
               │         ┌───────────────────┘
               │         │
               └─────────┼────────────┐
                         ▼            │
              ┌─────────────────────┐ │
              │GerenciadorPedidos   │◇│
              │   <<Singleton>>     │ │
              └─────────────────────┘ │
                         ▲            │
                         │            │
                         └────────────┘
                    DEPENDÊNCIA (<<call>>)
              (Builders chamam addLanche/addPizza)
```

---

## 📋 Descrição dos Relacionamentos

### **1. Composição: Lanche ◆─── LancheBuilder**

- **Tipo:** Composição (diamante preenchido ◆)
- **Multiplicidade:** 1:1
- **Descrição:** `LancheBuilder` é uma classe interna estática de `Lanche`. Não pode existir sem o contexto da classe externa.
- **Notação UML:** Desenhe `LancheBuilder` dentro da caixa de `Lanche` ou use linha com ◆ e estereótipo `<<inner class>>`

### **2. Composição: Pizza ◆─── PizzaBuilder**

- **Tipo:** Composição (diamante preenchido ◆)
- **Multiplicidade:** 1:1
- **Descrição:** `PizzaBuilder` é uma classe interna estática de `Pizza`. Segue o mesmo padrão de `LancheBuilder`.
- **Notação UML:** Desenhe `PizzaBuilder` dentro da caixa de `Pizza` ou use linha com ◆ e estereótipo `<<inner class>>`

### **3. Associação: LancheBuilder ───> Lanche**

- **Tipo:** Associação unidirecional (linha sólida com seta →)
- **Multiplicidade:** 1 (um builder constrói um lanche)
- **Descrição:** `LancheBuilder` mantém uma referência privada para o objeto `Lanche` que está construindo (atributo `private Lanche lanche`). O método `build()` retorna essa instância.
- **Navegação:** Unidirecional (apenas Builder conhece Lanche, não o contrário)

### **4. Associação: PizzaBuilder ───> Pizza**

- **Tipo:** Associação unidirecional (linha sólida com seta →)
- **Multiplicidade:** 1 (um builder constrói uma pizza)
- **Descrição:** `PizzaBuilder` mantém uma referência privada para o objeto `Pizza` que está construindo (atributo `private Pizza pizza`). O método `build()` retorna essa instância.
- **Navegação:** Unidirecional

### **5. Dependência: LancheBuilder - - -> IngredienteFactory**

- **Tipo:** Dependência (linha tracejada com seta - - ->)
- **Estereótipo:** `<<use>>`
- **Descrição:** `LancheBuilder` **usa** métodos estáticos de `IngredienteFactory` para criar ingredientes padronizados (ex: `IngredienteFactory.criarPao("1")`). Não mantém referência permanente.
- **Natureza:** Temporária, apenas durante a construção

### **6. Dependência: PizzaBuilder - - -> IngredienteFactory**

- **Tipo:** Dependência (linha tracejada com seta - - ->)
- **Estereótipo:** `<<use>>`
- **Descrição:** `PizzaBuilder` **usa** métodos estáticos de `IngredienteFactory` para criar ingredientes padronizados (ex: `IngredienteFactory.criarMassa("2")`). Não mantém referência permanente.
- **Natureza:** Temporária, apenas durante a construção

### **7. Agregação: GerenciadorPedidos ◇───> Lanche**

- **Tipo:** Agregação (diamante vazio ◇)
- **Multiplicidade:** 0..* (zero ou muitos lanches)
- **Descrição:** `GerenciadorPedidos` **agrega** uma coleção de `Lanche` (atributo `List<Lanche> lanchesPedidos`). Lanches podem existir independentemente do gerenciador.
- **Notação:** Linha sólida com ◇ no lado do GerenciadorPedidos

### **8. Agregação: GerenciadorPedidos ◇───> Pizza**

- **Tipo:** Agregação (diamante vazio ◇)
- **Multiplicidade:** 0..* (zero ou muitas pizzas)
- **Descrição:** `GerenciadorPedidos` **agrega** uma coleção de `Pizza` (atributo `List<Pizza> pizzasPedidos`). Pizzas podem existir independentemente do gerenciador.
- **Notação:** Linha sólida com ◇ no lado do GerenciadorPedidos

### **9. Dependência: LancheBuilder - - -> GerenciadorPedidos**

- **Tipo:** Dependência (linha tracejada com seta - - ->)
- **Estereótipo:** `<<call>>`
- **Descrição:** O método `LancheBuilder.build()` **chama** `GerenciadorPedidos.getInstancia().adicionarLanche()` para registrar automaticamente o lanche criado.
- **Natureza:** Chamada de método, sem manter referência

### **10. Dependência: PizzaBuilder - - -> GerenciadorPedidos**

- **Tipo:** Dependência (linha tracejada com seta - - ->)
- **Estereótipo:** `<<call>>`
- **Descrição:** O método `PizzaBuilder.build()` **chama** `GerenciadorPedidos.getInstancia().adicionarPizza()` para registrar automaticamente a pizza criada.
- **Natureza:** Chamada de método, sem manter referência

---

## 📐 Representação Visual Simplificada

```
Legenda:
  ◆────   Composição (inner class)
  ───>    Associação (mantém referência)
  - - >   Dependência (usa temporariamente)
  ◇───>   Agregação (contém coleção)


        [IngredienteFactory]
         (Factory Method)
                │
                │ <<use>>
        ┌───────┴───────┐
        │               │
        ▼               ▼
  [LancheBuilder]  [PizzaBuilder]
   <<builder>>      <<builder>>
        │               │
        │               │ builds (1)
        ▼               ▼
    [Lanche]        [Pizza]
   <<produto>>     <<produto>>
        │               │
        │ 0..*      0..*│
        └───────┬───────┘
                │
                ▼
      [GerenciadorPedidos]
          <<Singleton>>
                ▲
                │ <<call>>
        ┌───────┴───────┐
        │               │
  [LancheBuilder]  [PizzaBuilder]
   (no build())     (no build())
```

---

## 🎯 Resumo dos Padrões

### **1. Builder Pattern**
- **Classes:** `Lanche`, `LancheBuilder`, `Pizza`, `PizzaBuilder`
- **Propósito:** Construir objetos complexos passo a passo com validações
- **Relacionamentos-chave:**
  - Composição (Builder é inner class do Produto)
  - Associação (Builder mantém referência ao Produto)

### **2. Singleton Pattern**
- **Classe:** `GerenciadorPedidos`
- **Propósito:** Garantir uma única instância global para gerenciar todos os pedidos
- **Relacionamentos-chave:**
  - Agregação com Lanche e Pizza (armazena coleções)
  - Recebe chamadas dos Builders via `build()`

### **3. Factory Method Pattern**
- **Classe:** `IngredienteFactory`
- **Propósito:** Centralizar criação de ingredientes padronizados
- **Relacionamentos-chave:**
  - Dependência com Builders (são usados temporariamente)

---

## ✅ Checklist para Desenhar o Diagrama UML

- [X] Desenhar 4 caixas principais: Lanche, Pizza, IngredienteFactory, GerenciadorPedidos
- [X] LancheBuilder dentro ou conectado a Lanche (◆ composição)
- [X] PizzaBuilder dentro ou conectado a Pizza (◆ composição)
- [X] Linha sólida de LancheBuilder → Lanche (associação, multiplicidade 1)
- [X] Linha sólida de PizzaBuilder → Pizza (associação, multiplicidade 1)
- [X] Linha tracejada de LancheBuilder - - -> IngredienteFactory (dependência `<<use>>`)
- [X] Linha tracejada de PizzaBuilder - - -> IngredienteFactory (dependência `<<use>>`)
- [X] Linha com ◇ de GerenciadorPedidos ◇──> Lanche (agregação, 0..*)
- [X] Linha com ◇ de GerenciadorPedidos ◇──> Pizza (agregação, 0..*)
- [X] Linha tracejada de LancheBuilder - - -> GerenciadorPedidos (dependência `<<call>>`)
- [X] Linha tracejada de PizzaBuilder - - -> GerenciadorPedidos (dependência `<<call>>`)
- [X] Estereótipo `<<Singleton>>` em GerenciadorPedidos
- [X] Estereótipo `<<Factory Method>>` em IngredienteFactory
- [X] Estereótipo `<<inner class>>` nos Builders
- [X] Sublinhar membros estáticos (instancia, getInstancia(), métodos da Factory)
- [X] Marcar construtores privados com símbolo `-`

---

**Este diagrama destaca claramente os três padrões de projeto implementados, sem a poluição visual da classe Main, focando na arquitetura de domínio do sistema.**
