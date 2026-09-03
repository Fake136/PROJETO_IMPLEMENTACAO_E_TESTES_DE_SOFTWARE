# Plano de Teste – Sistema de Reserva de Salas

## 1. Identificação

| Item | Descrição |
|------|-----------|
| **Documento** | Plano de Teste |
| **Produto** | Sistema de Reserva de Salas |
| **Versão** | 1.0 |
| **Data** | 2026 |
| **Autores** | Dupla de alunos |

## 2. Objetivo

Garantir que o sistema de reserva de salas atenda aos requisitos funcionais (RF-01 a RF-08) e não-funcionais (RNF-01 a RNF-03), prevenindo riscos críticos: dupla ocupação, capacidade insegura, alteração sem autorização e falha de notificação.

## 3. Escopo

### 3.1 Dentro do escopo
- Reserva de salas disponíveis para turmas compatíveis
- Impedimento de sobreposição de horários
- Validação de capacidade da sala vs. tamanho da turma
- Bloqueio de salas em manutenção
- Respeito ao horário permitido (07h30–22h30)
- Controle de perfil (professor vs. coordenação)
- Cancelamento e alteração com histórico e notificação
- Busca de salas
- Trilha de auditoria
- Restrição de acesso por unidade

### 3.2 Fora do escopo
- Integração com sistemas externos de e-mail (apenas verificação de disparo)
- Testes de carga extremos (além do RNF-01)
- Interface mobile nativa
- Migração de dados legados

## 4. Estratégia de teste

| Tipo | Abordagem | Objetivo |
|------|-----------|----------|
| **Funcional (caixa-preta)** | Classes de equivalência + valores-limite | Validar regras de negócio |
| **Não-funcional** | Medição de tempo de resposta e verificação de logs | RNF-01 a RNF-03 |
| **Integração** | Fluxos completos (reserva → notificação → histórico) | Efeitos colaterais |
| **Segurança de acesso** | Testes de perfil e autorização | RF-06 e RNF-03 |

## 5. Critérios de entrada

- Requisitos aprovados e estáveis
- Ambiente de teste configurado (banco limpo + dados de seed)
- Usuários de teste criados (professor, coordenação, outro professor)
- Salas de teste cadastradas (com e sem manutenção, diferentes capacidades)

## 6. Critérios de saída

- 100% dos casos de teste de prioridade Alta executados
- Zero defeitos críticos abertos (dupla ocupação, capacidade, autorização)
- Cobertura de todos os RFs e RNFs
- Evidências (prints, logs, traces) anexadas

## 7. Recursos

| Recurso | Descrição |
|---------|-----------|
| Ambiente | Ambiente de homologação / local |
| Dados | Seed de salas, turmas, usuários |
| Ferramentas | Manual + futura automação (Playwright/Selenium) |
| Pessoas | Dupla de alunos + professor avaliador |

## 8. Riscos e mitigação

| Risco | Impacto | Mitigação |
|-------|---------|-----------|
| Dupla ocupação | Alto | Casos de sobreposição e concorrência |
| Capacidade insegura | Alto | Valores-limite (turma = capacidade e capacidade+1) |
| Alteração sem autorização | Alto | Testes de perfil cruzado |
| Falha de notificação | Médio | Verificação de disparo e conteúdo |
| Ambiente instável | Médio | Dados de seed versionados |

## 9. Cronograma (sugestão)

| Atividade | Prazo |
|-----------|-------|
| Elaboração do plano e casos | Até 20/08 |
| Execução dos casos prioritários | Semana seguinte |
| Registro de evidências e relatório | Final da atividade |

## 10. Entregáveis

- Plano de Teste (este documento)
- Casos de Teste detalhados
- Registro de execução (a preencher)
- Evidências e relatório final
