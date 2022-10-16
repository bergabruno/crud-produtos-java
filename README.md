# API RESPONSAVEL POR FAZER UM CRUD DE PRODUTOS

ENDPOINTS:

METODO GET - LISTAR TODOS OS PRODUTOS:

http://localhost:****/api/v1/produtos

METODO GET - RECUPERAR PRODUTO PELO ID:

http://localhost:****/api/v1/produtos/{id}

METODO POST - CRIAR PRODUTO:

http://localhost:****/api/v1/produtos/criar

body:
{
    "nome" : "ABAJUR",
    "quantidade" : 50,
    "valor" : 1000
}


METODO PUT - ALTERAR UM PRODUTO:

http://localhost:****/api/v1/produtos/{id}

body:
{
    "nome" : "ABAJUR",
    "quantidade" : 50,
    "valor" : 1000
}

METODO DELETE - DELETAR UM PRODUTO PELO ID:

http://localhost:****/api/v1/produtos/{id}
