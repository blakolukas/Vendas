# Sistema de Vendas - Trabalhos T1 e T2

## Descrição

Este repositório contém a implementação dos trabalhos T1 e T2 da disciplina de Projeto e Arquitetura de Software, focando em Arquitetura Limpa e Microserviços.

## T1 - Arquitetura Limpa

### Implementações Realizadas

#### Pessoa A - Domínio & Regras de Negócio
- ✅ **Estratégias de Impostos**: Implementação de `ImpostoEstadualStrategy` e `ImpostoFederalFixoStrategy`
- ✅ **Regras de Desconto**: Estratégias para descontos em pedidos com >3 itens (5%) e >10 itens (10%) usando padrão Strategy/Composite
- ✅ **Validação de Orçamento**: Método `isValido()` em `OrcamentoModel` para verificar validade de 21 dias
- ✅ **Cobertura Geográfica**: Enums `Pais` e `Estado` com verificação de cobertura
- ✅ **Exceções de Domínio**: Criação de exceções específicas (`DominioException`, `OrcamentoInvalidoException`, `EstoqueInsuficienteException`, `CoberturaGeograficaException`)
- ✅ **ServicoDeVendas Refatorado**: Integração com novas regras e lançamento de exceções

### Estrutura do Domínio

```
src/main/java/com/projarq/vendas/dominio/
├── entidades/
│   ├── enums/
│   │   ├── Pais.java
│   │   └── Estado.java
│   └── OrcamentoModel.java (com método isValido())
├── excecoes/
│   ├── DominioException.java
│   ├── OrcamentoInvalidoException.java
│   ├── EstoqueInsuficienteException.java
│   └── CoberturaGeograficaException.java
└── servicos/
    ├── impostos/
    │   ├── ImpostoStrategy.java
    │   ├── ImpostoEstadualStrategy.java
    │   └── ImpostoFederalFixoStrategy.java
    ├── descontos/
    │   ├── DescontoStrategy.java
    │   ├── DescontoMaisTresItensStrategy.java
    │   ├── DescontoMaisDezItensStrategy.java
    │   └── DescontoComposite.java
    └── ServicoDeVendasRefatorado.java
```

## T2 - Arquitetura de Microserviços

### Arquitetura Implementada

O sistema foi refatorado de um monolito para uma arquitetura de microserviços com os seguintes componentes:

#### 1. Eureka Server (Service Discovery)
- **Porta**: 8761
- **Função**: Registro e descoberta de serviços
- **Localização**: `microservicos/eureka-server/`

#### 2. Microserviço de Impostos
- **Função**: Cálculo de impostos estaduais e federais
- **Comunicação**: Síncrona (REST)
- **Escalabilidade**: Suporte a múltiplas instâncias
- **Localização**: `microservicos/impostos-service/`
- **Endpoints**:
  - `POST /impostos/calcular` - Calcula impostos
  - `GET /impostos/health` - Health check

#### 3. Microserviço de Registro de Vendas
- **Porta**: 8082
- **Função**: Registra vendas efetivadas e gera relatórios
- **Comunicação**: Assíncrona via RabbitMQ
- **Banco**: H2 em memória
- **Localização**: `microservicos/registro-vendas-service/`
- **Endpoints**:
  - `GET /registro-vendas/relatorio/{ano}/{mes}` - Relatório mensal
  - `GET /registro-vendas/health` - Health check

#### 4. RabbitMQ (Message Broker)
- **Portas**: 5672 (AMQP), 15672 (Management)
- **Credenciais**: admin/admin123
- **Função**: Comunicação assíncrona entre monolito e microserviço de registro

#### 5. Monolito Principal (Refatorado)
- **Porta**: 8081
- **Função**: Sistema principal de vendas
- **Integração**: Comunica com microserviço de impostos (síncrono) e envia mensagens para fila (assíncrono)

### Como Executar

#### Pré-requisitos
- Docker e Docker Compose
- Java 17
- Maven

#### Executar com Docker Compose

```bash
# Construir e executar todos os serviços
docker-compose up --build

# Executar com múltiplas instâncias do microserviço de impostos
docker-compose up --scale impostos-service=3
```

#### Executar Localmente (Desenvolvimento)

1. **Iniciar Eureka Server**:
```bash
cd microservicos/eureka-server
mvn spring-boot:run
```

2. **Iniciar RabbitMQ**:
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin123 \
  rabbitmq:3-management
```

3. **Iniciar Microserviços**:
```bash
# Microserviço de Impostos
cd microservicos/impostos-service
mvn spring-boot:run

# Microserviço de Registro de Vendas
cd microservicos/registro-vendas-service
mvn spring-boot:run
```

4. **Iniciar Monolito Principal**:
```bash
mvn spring-boot:run
```

### Endpoints Principais

- **Eureka Dashboard**: http://localhost:8761
- **RabbitMQ Management**: http://localhost:15672
- **Sistema Principal**: http://localhost:8081
- **Microserviço Impostos**: Descoberto via Eureka
- **Microserviço Registro**: http://localhost:8082

### Funcionalidades Implementadas

#### Comunicação Síncrona
- Monolito → Microserviço de Impostos (REST)
- Múltiplas instâncias do microserviço de impostos com load balancing

#### Comunicação Assíncrona
- Monolito → Fila RabbitMQ → Microserviço de Registro de Vendas
- Registro automático de vendas efetivadas

#### Relatórios
- Consulta de vendas por mês/ano
- Total vendido e total de impostos arrecadados

### Padrões Arquiteturais Utilizados

- **Microserviços**: Decomposição do monolito
- **Service Discovery**: Eureka para registro de serviços
- **API Gateway**: Preparado para implementação
- **Message Queue**: RabbitMQ para comunicação assíncrona
- **Strategy Pattern**: Cálculo de impostos e descontos
- **Composite Pattern**: Combinação de estratégias de desconto

### Estrutura de Arquivos

```
├── docker-compose.yml
├── microservicos/
│   ├── eureka-server/
│   ├── impostos-service/
│   ├── registro-vendas-service/
│   └── gateway/ (preparado para implementação)
├── src/ (monolito principal)
├── TODO_T1.md
└── TODO_T2.md
```

### Próximos Passos

Para completar a implementação:

1. **Gateway**: Implementar Spring Cloud Gateway
2. **Testes**: Criar testes de integração
3. **Monitoramento**: Adicionar métricas e logs
4. **Segurança**: Implementar autenticação/autorização
5. **Persistência**: Migrar para bancos de dados persistentes

### Tecnologias Utilizadas

- **Spring Boot 3.1.0**
- **Spring Cloud 2022.0.3**
- **Netflix Eureka**
- **RabbitMQ**
- **H2 Database**
- **Docker & Docker Compose**
- **Maven**

---

**Desenvolvido para a disciplina de Projeto e Arquitetura de Software**

