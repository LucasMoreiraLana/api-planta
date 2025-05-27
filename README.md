
# 🌱 API Planta - Documentação

Este projeto é uma API RESTful desenvolvida em **Spring Boot** para gerenciamento de plantas. A API oferece operações para **criação**, **atualização**, **busca**, **listagem** e **remoção** de plantas, com autenticação básica via **Basic Auth**.

---

## ✅ Autenticação

A API está protegida com **Basic Authentication**.

### ➡️ Credenciais disponíveis:

| Papel   | Username | Password |
|---------|----------|---------|
| Admin   | admin    | admin123 |
| Usuário | user     | user123  |

> ⚠️ **Importante:**  
Para executar a operação de **DELETE** (`/v1/planta/{plantaId}`), é necessário possuir papel **ADMIN**.  
Os demais endpoints podem ser acessados por qualquer usuário autenticado.

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
- **Descrição:** Retorna a lista de todas as plantas cadastradas.
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

- **500 Internal Server Error**: falha interna, exceção inesperada.
- **404 Not Found**: planta não encontrada.
- **401 Unauthorized**: ausência de credenciais ou credenciais incorretas.
- **403 Forbidden**: tentativa de acesso a recurso restrito sem permissão adequada (por exemplo, deletar planta com usuário comum).

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Web
- Postman (para testes)
- Mockito (para testes unitários)

---

## 📂 Estrutura do Projeto

```
com.example.api_planta
├── controllers
│   └── PlantaController.java
├── services
│   ├── CreatePlantaService.java
│   ├── UpdatePlantaService.java
│   ├── DeletePlantaService.java
│   ├── FindPlantaByIdService.java
│   └── FindAllPlantasService.java
├── dtos
│   ├── request
│   └── response
└── ApiPlantaApplication.java
```

---

## 📌 Observações

- O `@PreAuthorize("hasRole('ADMIN')")` protege o endpoint de **DELETE**.
- O restante do fluxo é tratado com `try-catch` e respostas HTTP adequadas.
- O `RequestUpdatePlanta` encapsula `plantaId` e `description` para facilitar o uso no serviço.

---

## 📞 Suporte

Em caso de dúvidas, abra uma **issue** neste repositório ou entre em contato com o desenvolvedor.
