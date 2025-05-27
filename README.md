# 🌱 API Planta - Documentação

Este projeto é uma API RESTful desenvolvida em **Spring Boot** para gerenciamento de plantas, utilizando **MongoDB** como banco de dados, gerenciado via **Docker**. A API oferece operações para **criação**, **atualização**, **busca**, **listagem** e **remoção** de plantas, com autenticação básica via **Basic Auth**.

---

## ✅ Autenticação

A API está protegida com **Basic Authentication**.

### ➡️ Credenciais disponíveis:

| Papel   | Username | Password  |
|---------|----------|-----------|
| Admin   | admin    | admin123  |
| Usuário | user     | user123   |

> ⚠️ **Importante:**  
> Para executar a operação de **DELETE** (`/v1/planta/{plantaId}`), é necessário possuir o papel **ADMIN**.  
> Os demais endpoints podem ser acessados por qualquer usuário autenticado.

---

## 📬 Endpoints disponíveis

### ➡️ Criar Planta

- **Método:** `POST`
- **Rota:** `/v1/planta/create`
- **Descrição:** Cria uma nova planta com os dados enviados.
- **Body (JSON):**
```json
{
  "name": "Rosa",
  "description": "Planta ornamental"
}
```
- **Resposta:** `200 OK` com dados da planta criada.
- **Autenticação:** Basic Auth (user ou admin).

---

### ➡️ Atualizar Planta

- **Método:** `PUT`
- **Rota:** `/v1/planta/{plantaId}`
- **Descrição:** Atualiza a descrição de uma planta existente.
- **Body (JSON):**
```json
{
  "description": "Nova descrição da planta"
}
```
- **Resposta:** `204 No Content` (sem conteúdo, operação bem-sucedida).
- **Autenticação:** Basic Auth (user ou admin).

---

### ➡️ Buscar Planta por ID

- **Método:** `GET`
- **Rota:** `/v1/planta/{plantaId}`
- **Descrição:** Retorna as informações de uma planta específica.
- **Resposta:**
  - `200 OK` com dados da planta.
  - `404 Not Found` caso a planta não exista.
- **Autenticação:** Basic Auth (user ou admin).

---

### ➡️ Listar todas as Plantas

- **Método:** `GET`
- **Rota:** `/v1/planta/all`
- **Descrição:**: Retorna a lista de todas as plantas cadastradas.
- **Resposta:** `200 OK` com array de plantas.
- **Autenticação:** Basic Auth (user ou admin).

---

### ➡️ Deletar Planta

- **Método:** `DELETE`
- **Rota:** `/v1/planta/{plantaId}`
- **Descrição:** Remove uma planta específica.
- **Resposta:**
  - `200 OK` se removida com sucesso.
  - `404 Not Found` caso a planta não exista.
- **Autenticação:** **Apenas** usuários com papel **ADMIN**.

---

## ⚙️ Como testar com Postman?

1. Abra o **Postman**.
2. Selecione o método e a URL correspondente.
3. Vá na aba **"Authorization"**.
4. Selecione o tipo **"Basic Auth"**.
5. Preencha o **username** e **password** conforme desejado:
   - Para operações comuns: `user` / `user123`
   - Para deletar: `admin` / `admin123`
6. Configure o **Body** em formato **JSON** quando necessário.
7. Execute a requisição.

---

## 🛠️ Tratamento de Erros

- **500 Internal Server Error**: Falha interna, exceção inesperada.
- **404 Not Found**: Planta não encontrada.
- **401 Unauthorized**: Ausência de credenciais ou credenciais incorretas.
- **403 Forbidden**: Tentativa de acesso a recurso restrito sem permissão adequada (ex.: deletar planta com usuário comum).

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Web
- Spring Data MongoDB (para integração com MongoDB)
- MongoDB (banco de dados NoSQL)
- Docker (para gerenciamento do banco de dados MongoDB)
- Postman (para testes)

---

## 📂 Estrutura do Projeto

```
com.example.api_planta
├── controllers
│   ├── GetFindAllPlantas.java
│   ├── GetFindPlantaById.java
│   ├── DeletePlanta.java
│   ├── PutUpdatePlanta.java
│   └── PostCreatePlanta.java
├── services
│   ├── CreatePlantaService.java
│   ├── UpdatePlantaService.java
│   ├── DeletePlantaService.java
│   ├── FindPlantaByIdService.java
│   └── FindAllPlantasService.java
├── dtos
│   ├── request
│   └── response
├── repository
│   └── PlantaRepository.java
└── ApiPlantaApplication.java
```

---

## 🐳 Configuração do Banco de Dados com Docker

O banco de dados **MongoDB** é configurado e executado via **Docker** utilizando o arquivo `docker-compose.yml` abaixo. Ele define um serviço MongoDB com as configurações necessárias para o projeto.

### Arquivo `docker-compose.yml`

```yaml
version: '3.8'

services:
  mongo:
    image: mongo:6.0
    container_name: mongo-planta
    ports:
      - "27018:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: example
      MONGO_INITDB_DATABASE: venetto
    volumes:
      - ./mongo-init:/docker-entrypoint-initdb.d
      - mongo-data:/data/db

volumes:
  mongo-data:
```

### Instruções para executar o MongoDB com Docker

1. Certifique-se de ter o **Docker** e o **Docker Compose** instalados.
2. Crie o arquivo `docker-compose.yml` na raiz do projeto com o conteúdo acima.
3. Execute o comando no terminal, na mesma pasta do arquivo:
   ```bash
   docker-compose up -d
   ```
4. O MongoDB estará disponível em `localhost:27018` com o banco de dados `venetto`.
5. As credenciais de acesso ao banco são:
   - **Usuário:** `root`
   - **Senha:** `example`
6. Configure a conexão no arquivo `application.properties` ou `application.yml` do Spring Boot:
   ```properties
   spring.data.mongodb.uri=mongodb://root:example@localhost:27018/venetto?authSource=admin
   ```

> **Nota:** O volume `mongo-data` garante a persistência dos dados do banco mesmo após a parada do container. O diretório `./mongo-init` pode ser usado para scripts de inicialização do banco, se necessário.

---

## 📌 Observações

- O `@PreAuthorize("hasRole('ADMIN')")` protege o endpoint de **DELETE**.
- O restante do fluxo é tratado com `try-catch` e respostas HTTP adequadas.
- O `RequestUpdatePlanta` encapsula `plantaId` e `description` para facilitar o uso no serviço.
- O MongoDB foi escolhido como banco de dados NoSQL para flexibilidade no armazenamento de dados das plantas.
- Certifique-se de que o container MongoDB está ativo antes de executar a API.