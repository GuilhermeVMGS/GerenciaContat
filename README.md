# Gerenciador de Contatos

Este projeto é um gerenciador de contatos em Java, com uma interface gráfica desenvolvida usando a biblioteca Swing. O objetivo do projeto é permitir que o usuário adicione, liste e busque contatos, além de salvar e carregar os dados de contatos a partir de um arquivo serializado.

## Funcionalidades

- **Adicionar Contato**: Permite que o usuário adicione um novo contato, incluindo nome, e-mail e telefones.
- **Listar Contatos**: Exibe uma lista de todos os contatos cadastrados.
- **Buscar Contato por Nome**: Permite que o usuário busque um contato pelo nome.
- **Salvar e Carregar Contatos**: Os dados de contatos são salvos em um arquivo serializado (`contatos.ser`), permitindo que as informações persistam entre execuções do programa.

## Requisitos

- Java 8 ou superior
- IDE ou editor de texto com suporte a Java (Ex: [VSCode](https://code.visualstudio.com/), IntelliJ IDEA, Eclipse)

## Como Usar

1. **Interface Gráfica**

    - O programa abrirá uma janela gráfica com os seguintes recursos:
        -- *Adicionar Contato:* Permite que você insira informações de um novo contato.
        -- *Listar Contatos:* Exibe todos os contatos cadastrados.
        -- *Buscar por Nome:* Permite que você busque um contato pelo nome.

2. **Persistência de Dados**

    - Os contatos são salvos automaticamente em um arquivo chamado contatos.ser, que é carregado sempre que o programa é iniciado.


## Estrutura do Código

### Classes Principais

- **GerenciaContato**:  
  Responsável pela gestão dos contatos. Possui métodos para adicionar, buscar, listar e remover contatos. Também é responsável pela serialização dos dados para o arquivo `contatos.ser`.

- **Contato**:  
  Representa um contato individual, com informações como nome, e-mail e uma lista de telefones.

- **MainFrame**:  
  A interface gráfica do programa, implementada com o Swing. Esta classe gerencia as interações do usuário e chama os métodos da classe `GerenciaContato` para realizar as operações.


## Exemplo de Uso

### Adicionar Contato

Ao clicar no botão "Adicionar Contato", uma janela será exibida para inserir o nome, e-mail e telefone do novo contato.

### Listar Contatos

Ao clicar no botão "Listar Contatos", todos os contatos cadastrados serão exibidos em uma área rolável na janela.

### Buscar Contato

Ao clicar em "Buscar por Nome/Email", será solicitado qeu escolha por qual informação deseja buscar e o programa exibirá as informações do contato correspondente, se encontrado.
