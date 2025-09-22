# ReciclaVille - Sistema de Compensação de Carbono

Sistema para controle de compensação de carbono desenvolvido com Spring Boot.

## Funcionalidades

### Principais
- CRUD de Categorias (com hierarquia)
- CRUD de Materiais
- CRUD de Clientes
- CRUD de Usuários
- Gestão de Declarações com cálculo automático
- Dashboard com totais por material
- Autenticação JWT
- Controle de acesso por perfil (ADMIN/USER)

### Regras de Negócio
- Categorias podem ter subcategorias
- Materiais pertencem a uma categoria e têm percentual de compensação
- Declarações calculam automaticamente totais de materiais e compensação
- Usuários USER só acessam dados do próprio cliente
- Usuários ADMIN têm acesso total

## Tecnologias
- Spring Boot 3.5.6
- Spring Data JPA
- Spring Security
- JWT (jsonwebtoken)
- H2 Database (em memória)
- Lombok
- Bean Validation

## Como Executar

1. Clone o repositório
2. Execute: `./mvnw spring-boot:run`
3. Acesse: http://localhost:8080

## Usuário Padrão
- **Usuário:** admin
- **Senha:** admin
- **Perfil:** ADMIN

## Endpoints Principais

### Autenticação
- `POST /api/auth/login` - Login (público)

### Categorias
- `GET /api/categorias` - Listar (filtro opcional: categoriaPaiId)
- `POST /api/categorias` - Criar (ADMIN)
- `GET /api/categorias/{id}` - Buscar por ID
- `PUT /api/categorias/{id}` - Atualizar (ADMIN)
- `DELETE /api/categorias/{id}` - Deletar (ADMIN)

### Materiais
- `GET /api/materiais` - Listar (filtro opcional: categoriaId)
- `POST /api/materiais` - Criar (ADMIN)
- `GET /api/materiais/{id}` - Buscar por ID
- `PUT /api/materiais/{id}` - Atualizar (ADMIN)
- `DELETE /api/materiais/{id}` - Deletar (ADMIN)

### Clientes (apenas ADMIN)
- `GET /api/clientes` - Listar
- `POST /api/clientes` - Criar
- `GET /api/clientes/{id}` - Buscar por ID
- `PUT /api/clientes/{id}` - Atualizar
- `DELETE /api/clientes/{id}` - Deletar

### Usuários (apenas ADMIN)
- `GET /api/usuarios` - Listar
- `POST /api/usuarios` - Criar
- `GET /api/usuarios/{id}` - Buscar por ID
- `PUT /api/usuarios/{id}` - Atualizar
- `DELETE /api/usuarios/{id}` - Deletar

### Declarações
- `GET /api/declaracoes` - Listar (USER: apenas suas)
- `POST /api/declaracoes` - Criar
- `GET /api/declaracoes/{id}` - Buscar por ID (USER: apenas suas)
- `DELETE /api/declaracoes/{id}` - Deletar (USER: apenas suas)

### Dashboard
- `GET /api/dashboard` - Dados do dashboard (ADMIN: todos, USER: apenas seu cliente)

## Banco de Dados
- H2 Console: http://localhost:8080/h2-console
- URL: jdbc:h2:mem:testdb
- Usuário: sa
- Senha: password

## Exemplos de Uso

### Login
```json
POST /api/auth/login
{
  "nomeUsuario": "admin",
  "senha": "admin"
}
```

### Criar Categoria
```json
POST /api/categorias
Authorization: Bearer {token}
{
  "nome": "Metais",
  "descricao": "Materiais metálicos"
}
```

### Criar Material
```json
POST /api/materiais
Authorization: Bearer {token}
{
  "nome": "Alumínio",
  "categoriaId": 1,
  "percentualCompensacao": 15.50
}
```

### Criar Cliente
```json
POST /api/clientes
Authorization: Bearer {token}
{
  "nome": "Empresa ABC",
  "cnpj": "12.345.678/0001-90",
  "atividadeEconomica": "Reciclagem",
  "responsavel": "João Silva"
}
```

### Criar Declaração
```json
POST /api/declaracoes
Authorization: Bearer {token}
{
  "clienteId": 1,
  "dataInicialPeriodo": "2024-01-01",
  "dataFinalPeriodo": "2024-01-31",
  "itens": [
    {
      "materialId": 1,
      "toneladasDeclaradas": 100.50
    }
  ]
}
```

## Estrutura do Projeto
```
src/main/java/com/samalombo/ReciclaVille/
├── config/          # Configurações (Security, JWT)
├── controller/      # Controllers REST
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA
├── enums/          # Enumerações
├── exception/      # Tratamento de exceções
├── repository/     # Repositórios JPA
└── service/        # Lógica de negócio
```