# TodoApp
Aplicativo de gerenciamento de Projetos e tarefas

1. Iniciando o Projeto

1.1. Definição do projeto basico:

	Nome: 		Todo App
	Descrição: 	Aplicação para o gerenciamento de projetos e as tarefas envolvidas nesses projetos.
	Objtivo: 	Resolver a questão de organização de tarefas de um ou varios projetos.

1.1.1. Requisitos do projeto (Funcionalidades):

	Entidades: 
		* Projeto;
			Atributos:
				- id
				- Nome
				- Descrição
				- Data de Criação
				- Data de atualização

		* Tarefa;
			Atributos:
				- id
				- Nome
				- Descrição
				- Status
				- Observações
				- Prazo
				- Data de Criação
				- Data de Atualização
				- Chave estrangeira


	Requisitos: 
		* Criar o Projetos;
		* Alterar o Projeto;
		* Deletar o Projeto;
		
		* Cadastro de Tarefas;
		* Alterar o Tarefas;
		* Deletar o Tarefas;

1.1.2. Regras de negocio:

	* Não contem tela de login
	* Não tem o conceito de usuario
	* Toda tarefa deve pertencer a um projeto

1.1.3. Definição da Tecnologia:

	* Java
	* MySql






----------------------------------- ETAPAS DE DESENVOLVIMENTO (Cronograma) -----------------------------------

1- Criação do banco de dados.

2- Criação das classes de modelos.

3- Criação da conecção com o banco de dados.

4- Criação das classes de controle (acesso aos dados do banco).

5- Criação da interface Grafica.
	
   5.1- Criação da tela principal.
		
      5.1.1- Criação do cellRederer Prazo.
		
      5.1.2- Criação do cellRederer Editar e Deletar.
	
   5.2- Criação da tela de cadastro de Projeto.
	
     5.3- Criação da tela de cadastro de Tarefa.

6- Implementação dos eventos.
	6.1- Validações.	

7- Ajustes finais.

8- Teste da Aplicação.
