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

---

## 📐 Como Representar em Diagrama UML de Classes

### **Relações e Notações UML**

#### **1. Composição (Lanche ◆─── LancheBuilder e Pizza ◆─── PizzaBuilder)**

**Notação:** Diamante preenchido (◆) na classe container

```
┌──────────┐  ◆───────────┐  ┌────────────────┐
│  Lanche  │◆──────────────┤  │ LancheBuilder  │
└──────────┘               │  └────────────────┘
                          1
                     <<inner class>>
```

**Explicação:**
- **LancheBuilder** e **PizzaBuilder** são classes **internas estáticas**
- Têm uma relação de **composição** com suas classes externas
- A classe interna não pode existir sem contexto da classe externa
- Use `<<inner class>>` ou `<<nested>>` como estereótipo

**No diagrama:**
- Desenhe a classe Builder dentro da classe Lanche/Pizza (caixa dentro de caixa), OU
- Use linha com diamante preenchido + estereótipo `<<inner class>>`

---

#### **2. Dependência (Main - - -> Lanche, Pizza, GerenciadorPedidos, IngredienteFactory)**

**Notação:** Linha tracejada com seta aberta (- - ->)

```
┌──────┐
│ Main │- - - - - - - - - ->┌─────────────────────┐
└──────┘                    │ GerenciadorPedidos  │
  │                         └─────────────────────┘
  │
  │- - - - - - - - - - - ->┌──────────────────┐
  │                        │ LancheBuilder    │
  │                        └──────────────────┘
  │
  └- - - - - - - - - - - ->┌──────────────────┐
                           │ PizzaBuilder     │
                           └──────────────────┘
```

**Explicação:**
- **Main** usa as outras classes mas não as armazena como atributos
- Usa métodos estáticos ou cria instâncias temporárias
- Relacionamento de **uso** (dependency)

**No diagrama:**
- Linha tracejada de Main para cada classe que utiliza
- Seta aponta para a classe sendo usada
- Pode adicionar `<<use>>` como estereótipo

---

#### **3. Associação (LancheBuilder ───> Lanche e PizzaBuilder ───> Pizza)**

**Notação:** Linha sólida com seta (───>)

```
┌────────────────┐          ┌──────────┐
│ LancheBuilder  │───────────>│  Lanche  │
└────────────────┘  -lanche  └──────────┘
                      1
```

**Explicação:**
- **LancheBuilder** mantém uma referência para **Lanche** (atributo `private Lanche lanche`)
- Multiplicidade: **1** (um builder constrói um lanche)
- Nome do papel: `-lanche`
- Navegação unidirecional (seta)

**No diagrama:**
- Linha sólida de LancheBuilder para Lanche
- Seta indica direção da navegação
- Adicione multiplicidade `1` perto do Lanche
- Adicione nome do atributo `-lanche` acima da linha

---

#### **4. Dependência com Uso de Método Estático (Builders - - -> IngredienteFactory)**

**Notação:** Linha tracejada com seta (- - ->) + estereótipo `<<use>>`

```
┌────────────────┐
│ LancheBuilder  │- - - - - - - - - ->┌─────────────────────┐
└────────────────┘      <<use>>       │ IngredienteFactory  │
                                      └─────────────────────┘
┌────────────────┐                            │
│ PizzaBuilder   │- - - - - - - - - - - - - ->│
└────────────────┘      <<use>>               └─────────────────────┘
```

**Explicação:**
- Builders **usam** métodos estáticos da Factory
- Não mantêm referência à Factory
- Chamam métodos como `IngredienteFactory.criarPao()`

**No diagrama:**
- Linha tracejada dos Builders para IngredienteFactory
- Adicione estereótipo `<<use>>` ou `<<call>>`

---

#### **5. Associação com Agregação (GerenciadorPedidos ◇─── Lanche e Pizza)**

**Notação:** Diamante vazio (◇) na classe container

```
┌─────────────────────┐  ◇─────────────┐  ┌──────────┐
│ GerenciadorPedidos  │◇──────────────>│  │  Lanche  │
│                     │                │  └──────────┘
│ -lanchesPedidos     │            0..*
│ -pizzasPedidos      │
│                     │◇──────────────┐  ┌──────────┐
└─────────────────────┘                │  │  Pizza   │
                                   0..*│  └──────────┘
```

**Explicação:**
- **GerenciadorPedidos** mantém **coleções** de Lanche e Pizza
- Lanches e Pizzas podem existir independentemente do gerenciador (conceitualmente)
- Multiplicidade: **0..\*** (zero ou muitos)
- Usa `List<Lanche>` e `List<Pizza>`

**No diagrama:**
- Linha sólida com diamante vazio no GerenciadorPedidos
- Seta aponta para Lanche/Pizza
- Multiplicidade `0..*` perto de Lanche/Pizza
- Nome do atributo `-lanchesPedidos` e `-pizzasPedidos`

---

#### **6. Padrão Singleton - Notação Especial**

**Notação:** Linha sublinhada nos membros estáticos + nota ou estereótipo

```
┌─────────────────────────────────────┐
│   <<Singleton>>                     │
│   GerenciadorPedidos                │
├─────────────────────────────────────┤
│ - instancia: GerenciadorPedidos     │ ← sublinhado (estático)
│   ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾     │
│ - lanchesPedidos: List<Lanche>      │
│ - pizzasPedidos: List<Pizza>        │
├─────────────────────────────────────┤
│ - GerenciadorPedidos()              │
│ + getInstancia(): GerenciadorPedidos│ ← sublinhado (estático)
│   ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾  │
│ + adicionarLanche(Lanche): void     │
│ + adicionarPizza(Pizza): void       │
└─────────────────────────────────────┘
```

**Explicação:**
- Adicione estereótipo `<<Singleton>>` acima do nome da classe
- Sublinhado indica membros **estáticos**
- Construtor privado (símbolo `-`)
- Método estático `getInstancia()` sublinhado

---

### **📋 Resumo das Relações UML**

| Relação | De | Para | Notação | Multiplicidade |
|---------|----|----|---------|----------------|
| **Composição** | Lanche | LancheBuilder | ◆──── | 1 |
| **Composição** | Pizza | PizzaBuilder | ◆──── | 1 |
| **Dependência** | Main | Lanche | - - -> | - |
| **Dependência** | Main | Pizza | - - -> | - |
| **Dependência** | Main | GerenciadorPedidos | - - -> | - |
| **Dependência** | Main | LancheBuilder | - - -> | - |
| **Dependência** | Main | PizzaBuilder | - - -> | - |
| **Associação** | LancheBuilder | Lanche | ───> | 1 |
| **Associação** | PizzaBuilder | Pizza | ───> | 1 |
| **Dependência** | LancheBuilder | IngredienteFactory | - - -> | - |
| **Dependência** | PizzaBuilder | IngredienteFactory | - - -> | - |
| **Dependência** | Main | IngredienteFactory | - - -> | - |
| **Agregação** | GerenciadorPedidos | Lanche | ◇───> | 0..* |
| **Agregação** | GerenciadorPedidos | Pizza | ◇───> | 0..* |

---

### **🎨 Dicas para Desenhar o Diagrama UML**

#### **Organização Visual:**

```
┌─────────────────────────────────────────────────────────────┐
│                         Layout Sugerido                     │
└─────────────────────────────────────────────────────────────┘

         ┌──────────────┐
         │     Main     │
         └──────┬───────┘
                │ (dependências tracejadas)
        ┌───────┼───────┬──────────┐
        ↓       ↓       ↓          ↓
    ┌───────┐ ┌──────┐ ┌────────┐ ┌─────────────────┐
    │Lanche │ │Pizza │ │IngFact │ │GerenciadorPed.  │
    │   ◆   │ │  ◆   │ └────────┘ └─────────────────┘
    │   │   │ │  │   │
    │   ↓   │ │  ↓   │
    │Builder│ │Build.│
    └───────┘ └──────┘
```

#### **1. Posicionamento:**
- **Main** no topo (ponto de entrada)
- **Lanche** e **Pizza** lado a lado (produtos)
- **Builders** dentro ou conectados às suas classes produto
- **IngredienteFactory** ao lado (utilidade)
- **GerenciadorPedidos** embaixo ou ao lado (gerenciamento)

#### **2. Estereótipos Importantes:**
- `<<Singleton>>` em GerenciadorPedidos
- `<<inner class>>` ou `<<nested>>` em Builders
- `<<use>>` ou `<<call>>` em dependências de Factory

#### **3. Visibilidade:**
- `+` público
- `-` privado
- `#` protegido
- `~` pacote

#### **4. Métodos Importantes a Destacar:**
- **LancheBuilder:** `build(): Lanche`
- **PizzaBuilder:** `build(): Pizza`
- **GerenciadorPedidos:** `getInstancia(): GerenciadorPedidos` (sublinhado)
- **IngredienteFactory:** todos os métodos `criarXxx(String): String` (sublinhados)

#### **5. Atributos Importantes:**
- **GerenciadorPedidos:** `-instancia: GerenciadorPedidos` (sublinhado - estático)
- **LancheBuilder:** `-lanche: Lanche`
- **PizzaBuilder:** `-pizza: Pizza`

---

### **🔍 Detalhamento de Cada Relação**

#### **A. Main usa LancheBuilder (Dependência)**
```
Main - - - - - - - - -> LancheBuilder
        <<use>>
```
- Main cria instâncias temporárias: `new Lanche.LancheBuilder()`
- Não mantém referência permanente

#### **B. LancheBuilder constrói Lanche (Associação)**
```
LancheBuilder ────────> Lanche
          -lanche  1
```
- Builder tem atributo `private Lanche lanche`
- Retorna Lanche no método `build()`

#### **C. LancheBuilder usa IngredienteFactory (Dependência)**
```
LancheBuilder - - - - -> IngredienteFactory
          <<use>>
```
- Chama métodos estáticos: `IngredienteFactory.criarPao("1")`

#### **D. Lanche contém LancheBuilder (Composição)**
```
Lanche ◆────────── LancheBuilder
        <<inner>>
```
- LancheBuilder é classe interna estática de Lanche
- Pode ser representado como caixa dentro de caixa

#### **E. GerenciadorPedidos agrega Lanches (Agregação)**
```
GerenciadorPedidos ◇─────────> Lanche
     -lanchesPedidos      0..*
```
- Mantém `List<Lanche> lanchesPedidos`
- Lanches podem existir sem o gerenciador

#### **F. LancheBuilder.build() adiciona ao Singleton**
```
LancheBuilder - - - - -> GerenciadorPedidos
        <<call>>
```
- Dentro do método `build()`, chama `GerenciadorPedidos.getInstancia().adicionarLanche()`

---

### **⚡ Exemplo Completo de uma Relação Detalhada**

#### **Relação: LancheBuilder → Lanche**

```
┌────────────────────────────────────────────┐
│          Lanche                            │
├────────────────────────────────────────────┤
│ - tamanho: String                          │
│ - tipoPao: String                          │
│ - recheio: String                          │
│ - ingredientes: Map<String, Integer>       │
│ - precoBase: double                        │
│ - precoTotal: double                       │
├────────────────────────────────────────────┤
│ - Lanche()                                 │
│ + getTamanho(): String                     │
│ + getPrecoTotal(): double                  │
│ + clonar(): LancheBuilder                  │
│ + toString(): String                       │
└────────────────┬───────────────────────────┘
                 │ ◆ composição (inner class)
                 │
        ┌────────▼──────────────────────────┐
        │   <<inner class>>                 │
        │   LancheBuilder                   │
        ├───────────────────────────────────┤
        │ - lanche: Lanche ←───────────────┐│ associação
        ├───────────────────────────────────┤│
        │ + LancheBuilder()                 ││
        │ + escolherTamanho(String):this    ││
        │ + comTipoPao(String):this         ││
        │ + comRecheio(String):this         ││
        │ + build(): Lanche ────────────────┘│ retorna
        └───────────────────────────────────┘
```

---

### **🎯 Checklist para seu Diagrama UML**

- [ ] **Lanche** e **Pizza** com atributos privados
- [ ] **LancheBuilder** e **PizzaBuilder** como classes internas (◆) ou separadas com estereótipo
- [ ] **GerenciadorPedidos** com estereótipo `<<Singleton>>`
- [ ] Atributo estático `instancia` sublinhado
- [ ] Método estático `getInstancia()` sublinhado
- [ ] **IngredienteFactory** com todos os métodos estáticos sublinhados
- [ ] Dependências tracejadas (- - ->) de Main para todas as classes que usa
- [ ] Associação sólida (───>) de Builders para seus produtos
- [ ] Agregação (◇) de GerenciadorPedidos para Lanche e Pizza
- [ ] Multiplicidades: `1`, `0..*` onde apropriado
- [ ] Visibilidades: `+` público, `-` privado
- [ ] Estereótipos: `<<use>>`, `<<inner class>>`, `<<Singleton>>`

---

### **📚 Ferramentas Recomendadas**

Para desenhar o diagrama UML:
- **Draw.io / diagrams.net** (gratuito, web)
- **Lucidchart** (web, template UML)
- **StarUML** (desktop, profissional)
- **PlantUML** (texto para diagrama)
- **Visual Paradigm** (completo, suporta todos os padrões GoF)

---

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
