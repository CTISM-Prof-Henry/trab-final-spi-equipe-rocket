# Sistema de Coordenação Institucional

Este sistema foi projetado para permitir que coordenadores de cursos visualizem o(s) cursos
administrados por eles, além de receber um relatório de probabilidade de evasão do alunos.

## Sumário

* [Requisitos](#requisitos)
* [Instalação](#instalacao)
* [Instruções de uso](#instrucoes-de-uso)
* [Estrutura do Projeto](#estrutura-do-projeto)
* [Créditos](#creditos)

## Requisitos

Para utilizar esse sistema web, é recomendado cumprir os seguintes requisitos:

| Requisito         | Descrição                                                            |
|-------------------|----------------------------------------------------------------------|
| Docker            | Docker desktop instalado e configurado na máquina.                   |
| Java              | Java Development Kit (JDK), preferêncialmente na versão 21.          |
| Rede              | Necessário acesso a internet para baixar os arquivos do repositório. |

## Instalação

Para rodar a aplicação em um container docker, basta clonar o repositório do github, abrir um terminal dentro da pasta
clonada e executar o seguinte comando:

```bash
docker-compose up -d --build
```

Ao fazer isso, a aplicação será configurada e estará rodando em um container no docker, juntamente ao banco de dados.
Por padrão, a porta utilizada é 8080 para a aplicação e 5433 para o banco de dados. As portas podem ser alteradas 
modificando os arquivos application.properties, Dockerfile e docker-compose.yml.

## Instruções de Uso

Ao iniciar o SCI, a primeira tela que você verá ao acessar o site será a tela de login. É necessário realizar o login antes
de utilizar o site. Para isso existem alguns usuários padrão para testes, como o e-mail "admin@admin.com", senha "admin".
Outros usuários padrão podem ser encontrados no arquivo [**data.sql**](../src/main/resources/data.sql).

Na página inicial após o login, você verá as opções Cursos e Alunos, além da opção de Sair próximo a foto de perfil.

Na página Cursos, o usuário Coordenador pode visualizar o(s) curso(s) que ele ministra. O usuário também pode pesquisar 
pelo nome ou código do curso que deseja visualizar, além de poder filtrar a ordem, clicando nos nomes do cabeçalho da tabela.
Clicando em um curso da tabela, o coordenador visualiza os alunos daquele curso também podendo filtrar e pesquisar.

Na página Alunos, o coordenador visualiza todos os alunos de todos os cursos ministrados. Também podendo pesquisar e filtrar
da forma que desejar.

Ao clicar em um aluno da tabela, o coordenador pode visualizar o perfil daquele aluno, visualizando nome, matrícula, curso, 
a foto do aluno, além do histórico de evasão nos anos escolares do aluno.

## Estrutura do Projeto

Utilizamos o modelo ER para o banco de dados da aplicação, o qual está representado na imagem a seguir:

![Texto Alternativo](img/Modelo%20ER.png)

Para a aplicação, seguimos a seguinte estrutura de classes, representada pelo diagrama UML abaixo:

![Texto Alternativo](img/Diagrama%20UML.png)

## Créditos

O repositório foi originalmente desenvolvido por:

* [**Rayka-Hyushi (Sidnei)**](https://github.com/Rayka-Hyushi), desenvolvedor do projeto inicial do banco de dados e da documentação, responsável pelo refinamento e
  organização do repositório e do projeto kanban da equipe, além de responsável pela manutenção dos códigos.
* [**JoaoAlisson1 (João)**](https://github.com/JoaoAlisson1), desenvolvedor do projeto inicial de classes e da modelagem com spring do projeto,
  programador da estrutura básica do projeto, incluindo backend e frontend.
* [**Liche008 (Gabriel)**](https://github.com/Liche008), desenvolvedor do sistema de login e segurança do projeto.
