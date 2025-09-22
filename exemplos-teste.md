# Exemplos de Teste - ReciclaVille

## 1. Login (Obter Token)
```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "nomeUsuario": "admin",
    "senha": "admin"
  }'
```

## 2. Criar Categoria Principal
```bash
curl -X POST http://localhost:8081/api/categorias \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Metais",
    "descricao": "Materiais metálicos recicláveis"
  }'
```

## 3. Criar Subcategoria
```bash
curl -X POST http://localhost:8081/api/categorias \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Metais Ferrosos",
    "descricao": "Metais que contêm ferro",
    "categoriaPaiId": 1
  }'
```

## 4. Criar Material
```bash
curl -X POST http://localhost:8081/api/materiais \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Alumínio",
    "categoriaId": 1,
    "percentualCompensacao": 15.50
  }'
```

## 5. Criar Cliente
```bash
curl -X POST http://localhost:8081/api/clientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Empresa Recicladora ABC",
    "cnpj": "12.345.678/0001-90",
    "atividadeEconomica": "Reciclagem de materiais",
    "responsavel": "João Silva"
  }'
```

## 6. Criar Usuário USER
```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Maria Santos",
    "nomeUsuario": "maria",
    "senha": "123456",
    "perfil": "USER",
    "clienteId": 1
  }'
```

## 7. Criar Declaração
```bash
curl -X POST http://localhost:8081/api/declaracoes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "clienteId": 1,
    "dataInicialPeriodo": "2024-01-01",
    "dataFinalPeriodo": "2024-01-31",
    "itens": [
      {
        "materialId": 1,
        "toneladasDeclaradas": 100.50
      }
    ]
  }'
```

## 8. Consultar Dashboard
```bash
curl -X GET http://localhost:8081/api/dashboard \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

## 9. Listar Categorias
```bash
curl -X GET http://localhost:8081/api/categorias \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

## 10. Listar Materiais por Categoria
```bash
curl -X GET "http://localhost:8081/api/materiais?categoriaId=1" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

## Sequência Completa de Teste

1. **Login como admin** para obter token
2. **Criar categorias** (Metais, Plásticos, Papel)
3. **Criar materiais** para cada categoria
4. **Criar cliente**
5. **Criar usuário USER** associado ao cliente
6. **Login como USER** para obter novo token
7. **Criar declaração** com vários materiais
8. **Consultar dashboard** para ver totais
9. **Testar permissões** (USER não pode acessar clientes, etc.)

## Dados de Exemplo Completos

### Categorias
- Metais (id: 1)
  - Metais Ferrosos (id: 2, pai: 1)
  - Metais Não-Ferrosos (id: 3, pai: 1)
- Plásticos (id: 4)
- Papel (id: 5)

### Materiais
- Alumínio (categoria: 1, compensação: 15.5%)
- Ferro (categoria: 2, compensação: 12.0%)
- Cobre (categoria: 3, compensação: 18.0%)
- PET (categoria: 4, compensação: 8.5%)
- Papel Branco (categoria: 5, compensação: 5.0%)

### Clientes
- Empresa Recicladora ABC (CNPJ: 12.345.678/0001-90)
- Indústria Verde Ltda (CNPJ: 98.765.432/0001-10)

### Usuários
- admin/admin (ADMIN)
- maria/123456 (USER, cliente: 1)
- joao/654321 (USER, cliente: 2)