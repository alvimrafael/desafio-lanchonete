# API de Lanchonete

## Descrição Geral
A API de Lanchonete é um sistema para gerenciamento de pedidos e produtos em uma lanchonete. Ela oferece endpoints para criar pedidos, adicionar e retirar produtos dos pedidos, calcular preços totais, fechar pedidos e muito mais.

## Requisitos de Sistema
- Java 17 ou superior
- Maven (ou Gradle, se preferir)
- Banco de dados (por exemplo, MySQL, PostgreSQL)

## Instalação e Configuração
1. Instale o Java 17 em sua máquina.
2. Clone o repositório do projeto.
3. Configure o arquivo application.properties com as informações do banco de dados.
4. Execute o comando `mvn clean install` para compilar o projeto.
5. Execute o comando `java -jar nome-do-arquivo.jar` para iniciar a aplicação.

## Endpoints da API
### Criar Pedido
- Método: POST
- URL: /api/pedidos
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {}
- Exemplo de Resposta:
  ```json
  {
    "id": 38,
    "valorTotal": 0,
    "status": "ABERTO"
  }

### Criar Produto
- Método: POST
- URL: /api/produtos
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {{
  "nome": "Pastel",
  "preco": 4.00
  }}
- Exemplo de Resposta:
  ```json
  {
    "id": 4,
    "nome": "Pastel",
    "preco": 4.00
  }

### Atualizar Produto
- Método: PUT
- URL: /api/produtos/{id}
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {{
  "nome": "Pastel",
  "preco": 3.00
  }}
- Exemplo de Resposta:
  ```json
  {
    "id": 4,
    "nome": "Pastel",
    "preco": 3.00
  }

### Deletar Produto
- Método: DELETE
- URL: /api/produtos/{id}
- Resposta Esperada: Status:204 No Content

### Listar Todos Produtos
- Método: GET
- URL: /api/produtos
- Exemplo de Resposta:
  ```json
  [
    {
        "id": 1,
        "nome": "Hamburguer",
        "preco": 5.00
    },
    {
        "id": 3,
        "nome": "Cachorro quente",
        "preco": 3.00
    },
    {
        "id": 4,
        "nome": "Pastel",
        "preco": 4.00
    }
  ]

### Listar Produto Por Id
- Método: GET
- URL: /api/produtos/{id}
- Exemplo de Resposta:
  ```json
   {
    "id": 1,
    "nome": "Hamburguer",
    "preco": 5.00
  }

### Adicionar Produto ao Pedido
- Método: POST
- URL: /api/pedidos/{id}/adicionar-produto
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {{
  "idProduto": 1,
  "quantidade": 3
  }}
- Exemplo de Resposta:
  ```json
  {
    "id": 12,
    "valorTotal": 36.00,
    "status": "ABERTO"
  }

### Retirar Produto do Pedido
- Método: POST
- URL: /api/pedidos/{id}/retirar-produto
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {{
  "idProduto": 1,
  "quantidade": 1
  }}
- Exemplo de Resposta:
  ```json
  {
    "id": 12,
    "valorTotal": 36.00,
    "status": "ABERTO"
  }

### Calcular Preço Total do Pedido
- Método: GET
- URL: /api/pedidos/{id}/calcular-preco-total
- Exemplo de Resposta:
  ```json
   36.00

### Fechar Pedido
- Método: POST
- URL: /api/pedidos/{id}/fechar
- Body: JSON contendo informações do pedido
- Exemplo de Requisição:
  ```json
  {{
  "valorPagamento": 38
  }}
- Exemplo de Resposta:
  ```json
  {
    "id": 12,
    "valorTotal": 36.00,
    "status": "FECHADO",
    "troco": 2.00
  }

### Buscar Pedido
- Método: GET
- URL: /api/pedidos/{id}
- Exemplo de Resposta:
  ```json
   {
    "id": 22,
    "valorTotal": 30.00,
    "status": "ABERTO"
  }

## Modelo de Dados
- Pedido
- Produto
- Itens Pedido
- DTOs (Data Transfer Objects) para requisições e respostas

## Autenticação e Autorização
  Não foi implementado por falta de tempo. Mas é previsto autenticação via token JWT

## Erros e Exceções
  Erros e exceções não foram tratados por falta de tempo, mas tava previsto também

## Documentação
  Não foi feito por falta de tempo

## Testes Unitários
  Não foi feito por falta de tempo, mas faria com JUnit

## Observações Finais
  Lembre-se de substituir as informações genéricas (como URLs, IDs, exemplos de JSON) pelos dados reais.

## Modelo de Dados
  Para dúvidas, entre em contato pelo e-mail rafael.alvimdossantos@gmail.com
