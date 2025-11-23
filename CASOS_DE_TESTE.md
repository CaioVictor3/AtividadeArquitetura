# Casos de Teste - Sistema de Pedidos da Lanchonete

## Visão Geral

Este documento descreve os casos de teste automáticos implementados no sistema para demonstrar e validar as funcionalidades dos padrões de projeto GoF aplicados.

## Como Executar

1. Compile e execute o programa `Main.java`
2. No menu principal, selecione a opção **5 - Executar Casos de Teste**
3. Os testes serão executados automaticamente e exibirão resultados detalhados

## Casos de Teste Implementados

### TESTE 1: Padrão Builder - Construção de Lanche Completo
**Objetivo:** Demonstrar a construção fluente de um lanche com múltiplas opções

**Cenário:**
- Cria um lanche grande com pão australiano
- Adiciona recheio de carne
- Inclui queijo extra, ingredientes, molhos e acompanhamentos

**Padrão Testado:** Builder Pattern (GoF)

**Resultado Esperado:** ✓ Lanche construído com sucesso com todos os componentes

---

### TESTE 2: Padrão Factory - Criação de Ingredientes
**Objetivo:** Testar a criação de diferentes tipos de ingredientes via Factory

**Cenário:**
- Cria pão tipo Brioche
- Cria recheio de Frango
- Cria massa Tradicional

**Padrão Testado:** Factory Pattern (GoF)

**Resultado Esperado:** ✓ Ingredientes criados corretamente através da factory

---

### TESTE 3: Padrão Builder - Construção de Pizza Completa
**Objetivo:** Demonstrar a construção fluente de uma pizza com múltiplos recheios

**Cenário:**
- Cria uma pizza tamanho família com massa pan
- Adiciona 3 tipos de recheios
- Inclui queijo extra, molhos e ingredientes extras

**Padrão Testado:** Builder Pattern (GoF)

**Resultado Esperado:** ✓ Pizza construída com sucesso com todos os componentes

---

### TESTE 4: Validação - Campos Obrigatórios do Lanche
**Objetivo:** Verificar se a validação impede a criação de lanche sem campos obrigatórios

**Cenário:**
- Tenta criar um lanche sem especificar o tipo de pão
- Deve lançar `IllegalStateException`

**Validação Testada:** Campos obrigatórios (pão, recheio)

**Resultado Esperado:** ✓ Exceção lançada corretamente com mensagem apropriada

---

### TESTE 5: Validação - Restrição Vegetariana
**Objetivo:** Verificar se lanche vegetariano não permite bacon ou ovo

**Cenário:**
- Cria um lanche com recheio vegetariano
- Tenta adicionar bacon como ingrediente
- Deve lançar `IllegalStateException`

**Validação Testada:** Regras de negócio (vegetariano)

**Resultado Esperado:** ✓ Exceção lançada impedindo ingrediente não-vegetariano

---

### TESTE 6: Validação - Máximo de Recheios na Pizza
**Objetivo:** Verificar se pizza não permite mais de 5 tipos diferentes de recheios

**Cenário:**
- Tenta criar uma pizza com 6 tipos diferentes de recheios
- Deve lançar `IllegalStateException`

**Validação Testada:** Limite de recheios na pizza

**Resultado Esperado:** ✓ Exceção lançada ao exceder o limite

---

### TESTE 7: Padrão Prototype - Clonagem de Lanche
**Objetivo:** Demonstrar a clonagem de um lanche existente

**Cenário:**
- Cria um lanche original com configurações específicas
- Clona o lanche usando o método `clonar()`
- Verifica se o clone é um objeto diferente com mesmas propriedades

**Padrão Testado:** Prototype Pattern (GoF)

**Resultado Esperado:** 
- ✓ Clone criado com sucesso
- ✓ Original e clone são objetos distintos
- ✓ Clone possui as mesmas configurações do original

---

### TESTE 8: Padrão Prototype - Clonagem de Pizza
**Objetivo:** Demonstrar a clonagem de uma pizza existente

**Cenário:**
- Cria uma pizza original vegetariana
- Clona a pizza usando o método `clonar()`
- Verifica se o clone é um objeto diferente com mesmas propriedades

**Padrão Testado:** Prototype Pattern (GoF)

**Resultado Esperado:**
- ✓ Clone criado com sucesso
- ✓ Original e clone são objetos distintos
- ✓ Clone possui as mesmas configurações do original

---

### TESTE 9: Padrão Singleton - Gerenciador de Pedidos
**Objetivo:** Verificar se apenas uma instância do gerenciador existe

**Cenário:**
- Obtém duas referências ao `GerenciadorPedidos`
- Compara se são o mesmo objeto
- Verifica acesso aos dados compartilhados

**Padrão Testado:** Singleton Pattern (GoF)

**Resultado Esperado:**
- ✓ Ambas as referências apontam para o mesmo objeto
- ✓ Dados compartilhados acessíveis por ambas as referências

---

### TESTE 10: Cálculo de Preços - Verificação de Adicionais
**Objetivo:** Verificar se os preços dos adicionais estão sendo calculados corretamente

**Cenário:**
- Cria um lanche básico pequeno (R$ 15,00)
- Cria outro lanche igual com adicionais:
  - Queijo extra: +R$ 2,50
  - Bacon: +R$ 2,50
  - Molho barbecue: +R$ 1,00
  - Batata frita: +R$ 3,00
- Calcula a diferença de preço

**Validação Testada:** Lógica de cálculo de preços

**Resultado Esperado:**
- ✓ Diferença de R$ 9,00 entre os lanches
- ✓ Preço total do lanche com adicionais: R$ 24,00

---

## Resumo dos Padrões Testados

### ✓ Builder Pattern
- Construção fluente de objetos complexos (Lanche e Pizza)
- Interface clara e legível para montagem de pedidos
- Validações centralizadas no método `build()`

### ✓ Factory Pattern
- Criação padronizada de ingredientes (pão, recheio, massa)
- Abstração da lógica de criação
- Facilita manutenção e extensibilidade

### ✓ Prototype Pattern
- Clonagem eficiente de pedidos existentes
- Reutilização de configurações complexas
- Objetos independentes após clonagem

### ✓ Singleton Pattern
- Gerenciador único de pedidos no sistema
- Controle centralizado de todos os pedidos
- Cálculos agregados (total de vendas, quantidade de pedidos)

## Validações Implementadas

1. **Campos Obrigatórios:**
   - Lanche: tamanho, tipo de pão, recheio
   - Pizza: tamanho, tipo de massa, pelo menos um recheio

2. **Regras de Negócio:**
   - Pedidos vegetarianos não podem conter bacon ou ovo
   - Pizzas limitadas a 5 tipos diferentes de recheios

3. **Cálculo de Preços:**
   - Preço base varia por tamanho
   - Ingredientes: R$ 2,50 cada
   - Molhos: R$ 1,00 (lanche) ou R$ 1,50 (pizza) cada
   - Acompanhamentos: R$ 3,00 cada
   - Extras (pizza): R$ 4,00 cada

## Estatísticas dos Testes

Ao executar todos os casos de teste, o sistema:
- Cria aproximadamente 8 pedidos válidos
- Testa 3 cenários de falha (validações)
- Gera um valor total de vendas demonstrativo
- Valida 4 padrões de projeto GoF
- Executa 10 testes diferentes

## Observações

- Os testes podem ser executados múltiplas vezes
- O sistema oferece a opção de limpar pedidos anteriores antes de executar
- Cada teste exibe resultado claro: ✓ PASSOU ou ✗ FALHOU
- Exceções esperadas são capturadas e validadas
- Warnings de variáveis não usadas nos testes de validação são intencionais

## Conclusão

Os casos de teste demonstram que:
1. Todos os padrões GoF estão implementados corretamente
2. As validações de negócio funcionam como esperado
3. Os cálculos de preços estão precisos
4. O sistema é robusto e previne estados inválidos
5. A experiência do usuário é intuitiva e clara
