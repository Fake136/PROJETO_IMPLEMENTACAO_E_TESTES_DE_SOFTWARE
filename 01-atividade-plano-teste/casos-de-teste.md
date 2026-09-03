# Casos de Teste – Sistema de Reserva de Salas

## Legenda de prioridade
- **Alta**: riscos críticos (dupla ocupação, capacidade, autorização, notificação)
- **Média**: regras de horário e manutenção
- **Baixa**: cenários complementares

---

## RF-01 – Reservar sala disponível para turma compatível

### CT-RF01-01 – Reserva válida (fluxo feliz)
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF01-01 |
| **Requisito** | RF-01 |
| **Prioridade** | Alta |
| **Pré-condição** | Sala A disponível no horário; turma com tamanho ≤ capacidade; usuário professor autenticado |
| **Entrada** | Sala A, data D, 08:00–10:00, turma T1 (20 alunos), capacidade 30 |
| **Passos** | 1. Acessar tela de reserva<br>2. Selecionar sala, data, horário e turma<br>3. Confirmar |
| **Resultado esperado** | Reserva criada com sucesso; status “Confirmada”; horário fica ocupado |
| **Justificativa** | Fluxo principal do sistema |

### CT-RF01-02 – Sala já ocupada no horário
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF01-02 |
| **Requisito** | RF-01 / RF-02 |
| **Prioridade** | Alta |
| **Pré-condição** | Já existe reserva na Sala A em 08:00–10:00 |
| **Entrada** | Nova tentativa de reserva na mesma sala e horário |
| **Resultado esperado** | Sistema rejeita; mensagem de conflito; nenhuma nova reserva criada |
| **Justificativa** | Impedir sobreposição |

---

## RF-02 – Impedir sobreposição na mesma sala

### CT-RF02-01 – Sobreposição parcial de horário
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF02-01 |
| **Requisito** | RF-02 |
| **Prioridade** | Alta |
| **Pré-condição** | Reserva existente 08:00–10:00 |
| **Entrada** | Nova reserva 09:00–11:00 na mesma sala |
| **Resultado esperado** | Rejeição por conflito de horário |
| **Justificativa** | Sobreposição parcial também é conflito |

### CT-RF02-02 – Horários adjacentes (sem sobreposição)
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF02-02 |
| **Requisito** | RF-02 |
| **Prioridade** | Média |
| **Pré-condição** | Reserva 08:00–10:00 |
| **Entrada** | Nova reserva 10:00–12:00 |
| **Resultado esperado** | Reserva aceita (não há sobreposição) |
| **Justificativa** | Valor-limite: fim de uma = início da outra |

---

## RF-03 – Impedir turma maior que a capacidade

### CT-RF03-01 – Turma igual à capacidade (limite)
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF03-01 |
| **Requisito** | RF-03 |
| **Prioridade** | Alta |
| **Entrada** | Capacidade 30; turma 30 alunos |
| **Resultado esperado** | Reserva aceita |
| **Justificativa** | Valor-limite válido |

### CT-RF03-02 – Turma maior que a capacidade
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF03-02 |
| **Requisito** | RF-03 |
| **Prioridade** | Alta |
| **Entrada** | Capacidade 30; turma 31 alunos |
| **Resultado esperado** | Rejeição; mensagem clara de capacidade insuficiente |
| **Justificativa** | Valor-limite inválido – risco crítico |

---

## RF-04 – Bloquear sala em manutenção

### CT-RF04-01 – Tentativa de reserva em sala em manutenção
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF04-01 |
| **Requisito** | RF-04 |
| **Prioridade** | Alta |
| **Pré-condição** | Sala B marcada como “Em manutenção” |
| **Entrada** | Tentativa de reserva na Sala B |
| **Resultado esperado** | Sistema bloqueia; não permite seleção ou rejeita na confirmação |
| **Justificativa** | Segurança operacional |

---

## RF-05 – Reservas apenas entre 07h30 e 22h30

### CT-RF05-01 – Horário no limite inferior (07h30)
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF05-01 |
| **Requisito** | RF-05 |
| **Prioridade** | Média |
| **Entrada** | Início 07:30 |
| **Resultado esperado** | Aceito |

### CT-RF05-02 – Horário antes de 07h30
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF05-02 |
| **Requisito** | RF-05 |
| **Prioridade** | Média |
| **Entrada** | Início 07:00 |
| **Resultado esperado** | Rejeitado |

### CT-RF05-03 – Horário no limite superior (22h30)
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF05-03 |
| **Requisito** | RF-05 |
| **Prioridade** | Média |
| **Entrada** | Término 22:30 |
| **Resultado esperado** | Aceito |

### CT-RF05-04 – Horário após 22h30
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF05-04 |
| **Requisito** | RF-05 |
| **Prioridade** | Média |
| **Entrada** | Término 23:00 |
| **Resultado esperado** | Rejeitado |

---

## RF-06 – Só coordenação altera reserva de outro professor

### CT-RF06-01 – Professor tenta alterar reserva de outro
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF06-01 |
| **Requisito** | RF-06 |
| **Prioridade** | Alta |
| **Pré-condição** | Reserva do Professor A; usuário logado = Professor B |
| **Entrada** | Tentativa de alteração |
| **Resultado esperado** | Acesso negado; nenhuma alteração realizada |
| **Justificativa** | Controle de autorização – risco crítico |

### CT-RF06-02 – Coordenação altera reserva de professor
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF06-02 |
| **Requisito** | RF-06 |
| **Prioridade** | Alta |
| **Pré-condição** | Usuário com perfil Coordenação |
| **Entrada** | Alteração de horário da reserva do Professor A |
| **Resultado esperado** | Alteração permitida; histórico e notificação gerados |
| **Justificativa** | Perfil autorizado |

### CT-RF06-03 – Professor altera a própria reserva
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF06-03 |
| **Requisito** | RF-06 |
| **Prioridade** | Média |
| **Pré-condição** | Reserva do próprio professor logado |
| **Resultado esperado** | Alteração permitida |

---

## RF-07 – Cancelamento libera horário e registra histórico

### CT-RF07-01 – Cancelamento bem-sucedido
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF07-01 |
| **Requisito** | RF-07 |
| **Prioridade** | Alta |
| **Pré-condição** | Reserva ativa do professor logado |
| **Entrada** | Ação de cancelar |
| **Resultado esperado** | Status = Cancelada; horário liberado para novas reservas; registro no histórico |
| **Justificativa** | Liberação de recurso + auditoria |

---

## RF-08 – Alteração ou cancelamento gera notificação

### CT-RF08-01 – Notificação após cancelamento
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF08-01 |
| **Requisito** | RF-08 |
| **Prioridade** | Alta |
| **Pré-condição** | Reserva ativa |
| **Entrada** | Cancelamento |
| **Resultado esperado** | Notificação enviada ao responsável (e/ou coordenação); conteúdo identifica a reserva cancelada |
| **Justificativa** | Risco crítico de falha de notificação |

### CT-RF08-02 – Notificação após alteração
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RF08-02 |
| **Requisito** | RF-08 |
| **Prioridade** | Alta |
| **Entrada** | Alteração de horário por coordenação |
| **Resultado esperado** | Notificação enviada ao professor responsável |

---

## RNF-01 – Busca responde em até 2 segundos

### CT-RNF01-01 – Tempo de resposta da busca
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RNF01-01 |
| **Requisito** | RNF-01 |
| **Prioridade** | Média |
| **Entrada** | Busca de salas disponíveis em data/horário |
| **Resultado esperado** | Resposta ≤ 2 segundos (medir com ferramenta) |
| **Justificativa** | Desempenho mínimo aceitável |

## RNF-02 – Trilha de auditoria

### CT-RNF02-01 – Operação gera registro de auditoria
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RNF02-01 |
| **Requisito** | RNF-02 |
| **Prioridade** | Média |
| **Entrada** | Qualquer operação de reserva/alteração/cancelamento |
| **Resultado esperado** | Registro contendo usuário, data/hora, ação e dados relevantes |

## RNF-03 – Acesso limitado às unidades autorizadas

### CT-RNF03-01 – Usuário de outra unidade
| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-RNF03-01 |
| **Requisito** | RNF-03 |
| **Prioridade** | Média |
| **Pré-condição** | Usuário vinculado à Unidade X; sala da Unidade Y |
| **Resultado esperado** | Sala não aparece ou acesso negado |

---

## Matriz de rastreabilidade (resumo)

| Requisito | Casos de teste |
|-----------|----------------|
| RF-01 | CT-RF01-01, CT-RF01-02 |
| RF-02 | CT-RF02-01, CT-RF02-02 |
| RF-03 | CT-RF03-01, CT-RF03-02 |
| RF-04 | CT-RF04-01 |
| RF-05 | CT-RF05-01 a CT-RF05-04 |
| RF-06 | CT-RF06-01 a CT-RF06-03 |
| RF-07 | CT-RF07-01 |
| RF-08 | CT-RF08-01, CT-RF08-02 |
| RNF-01 | CT-RNF01-01 |
| RNF-02 | CT-RNF02-01 |
| RNF-03 | CT-RNF03-01 |
