# 🧪 Guia Rápido - Casos de Teste

## Como Executar

```
1. Execute: java com.lanchonete.Main
2. Escolha opção: 5 - Executar Casos de Teste
3. Aguarde a execução automática
```

## 📊 Resumo dos 10 Testes

### 🏗️ Padrão Builder (3 testes)

| # | Teste | O que valida |
|---|-------|--------------|
| 1 | Lanche Completo | Construção fluente com múltiplas opções |
| 3 | Pizza Completa | Construção com vários recheios e extras |
| 10 | Cálculo de Preços | Soma correta de base + adicionais |

**Resultado esperado:** Objetos construídos com sucesso e preços corretos

---

### 🏭 Padrão Factory (1 teste)

| # | Teste | O que valida |
|---|-------|--------------|
| 2 | Criação de Ingredientes | Factory cria pão, recheio e massa corretamente |

**Resultado esperado:** Ingredientes criados com nomes padronizados

---

### 🔄 Padrão Prototype (2 testes)

| # | Teste | O que valida |
|---|-------|--------------|
| 7 | Clone de Lanche | Cópia independente com mesmas propriedades |
| 8 | Clone de Pizza | Cópia independente com mesmas propriedades |

**Resultado esperado:** Objetos diferentes com configurações idênticas

---

### 🎯 Padrão Singleton (1 teste)

| # | Teste | O que valida |
|---|-------|--------------|
| 9 | Instância Única | Duas chamadas retornam o mesmo objeto |

**Resultado esperado:** `instancia1 == instancia2` é verdadeiro

---

### ✅ Validações de Negócio (3 testes)

| # | Teste | O que valida | Exceção Esperada |
|---|-------|--------------|------------------|
| 4 | Campos Obrigatórios | Lanche sem pão é rejeitado | `IllegalStateException` |
| 5 | Restrição Vegetariana | Vegetariano não aceita bacon | `IllegalStateException` |
| 6 | Limite de Recheios | Pizza não aceita +5 recheios | `IllegalStateException` |

**Resultado esperado:** Exceções lançadas com mensagens apropriadas

---

## 📈 Estatísticas dos Testes

Ao executar, o sistema:

- ✅ Cria **~8 pedidos válidos**
- ⚠️ Testa **3 cenários de falha** (validações)
- 🎯 Valida **4 padrões GoF**
- 💰 Calcula **valor total** demonstrativo
- 📊 Exibe **estatísticas completas**

---

## 🎨 Exemplo de Saída

```
╔══════════════════════════════════════════════════════════════╗
║         EXECUTANDO CASOS DE TESTE AUTOMÁTICOS               ║
╚══════════════════════════════════════════════════════════════╝

======================================================================
TESTE 1: PADRÃO BUILDER - Construção de Lanche Completo
======================================================================
✓ TESTE PASSOU: Lanche construído com sucesso

========== PEDIDO - LANCHE ==========
Tamanho: Grande
Tipo de Pão: Pão Australiano
Recheio: Carne
Ingredientes: Queijo Extra, Alface, Tomate, Bacon
Molhos: Barbecue, Maionese
Acompanhamentos: Batata Frita
Preço Total: R$ 40,00
=====================================
```

---

## 🔍 Detalhes Técnicos

### Padrões Testados

| Padrão | Método/Classe | Linha no Teste |
|--------|---------------|----------------|
| Builder | `LancheBuilder.build()` | Testes 1, 3, 7, 10 |
| Factory | `IngredienteFactory.criar*()` | Teste 2 |
| Prototype | `lanche.clonar()` | Testes 7, 8 |
| Singleton | `GerenciadorPedidos.getInstancia()` | Teste 9 |

### Validações Testadas

| Validação | Regra | Mensagem de Erro |
|-----------|-------|------------------|
| Campos obrigatórios | Pão, recheio, massa | "Tipo de pão é obrigatório" |
| Vegetariano | Sem bacon/ovo | "Pedido vegetariano não pode conter Bacon" |
| Limite recheios | Máx. 5 tipos | "Máximo de 5 tipos diferentes de recheios" |

---

## 💡 Dicas

1. **Primeira execução:** Sistema oferece limpar pedidos anteriores
2. **Múltiplas execuções:** Pedidos se acumulam (use visualizar pedidos)
3. **Debugging:** Cada teste exibe resultado claro (✓ PASSOU / ✗ FALHOU)
4. **Documentação completa:** Veja `CASOS_DE_TESTE.md`

---

## 🎯 Checklist de Validação

Após executar os testes, verifique:

- [ ] Todos os 10 testes exibem "✓ TESTE PASSOU"
- [ ] Nenhum teste exibe "✗ TESTE FALHOU"
- [ ] Pedidos foram criados e registrados no gerenciador
- [ ] Valor total está correto e coerente
- [ ] Clones são objetos independentes
- [ ] Singleton retorna mesma instância
- [ ] Validações capturam exceções corretamente

---

**✨ Todos os testes passando = Sistema 100% funcional!**
