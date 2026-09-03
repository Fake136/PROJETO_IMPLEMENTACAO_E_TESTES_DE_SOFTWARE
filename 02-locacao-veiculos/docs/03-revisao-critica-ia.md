# Etapa 3 – Revisão Crítica da resposta da IA

## O que a IA costuma acertar
- Cálculo das diárias, seguro e km excedente
- Aplicação correta do desconto só sobre as diárias
- Identificação das entradas inválidas

## Pontos que precisam de atenção / refinamento
1. **Valores-limite de desconto** (6, 7, 14, 15 dias) – frequentemente omitidos.
2. **Idade exatamente 21** – muitas respostas só testam < 21.
3. **Km exatamente na franquia** – evita falso positivo de excedente.
4. **Desconto não incide sobre seguro nem excedente** – verificar se o teste isola isso.
5. **Mensagens de exceção** – não são obrigatórias, mas o tipo deve ser IllegalArgumentException.

## Ajustes realizados
- Inclusão de testes parametrizados para os limites de desconto.
- Teste explícito de idade = 21 e km = franquia.
- Cobertura de todos os ramos de validação.
