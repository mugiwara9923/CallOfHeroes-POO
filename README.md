# 🎮 Call of Heroes

Projeto desenvolvido como trabalho prático da disciplina de **Programação Orientada a Objetos**, utilizando **Java** e **Swing** para a interface gráfica.

O jogo simula a gestão de heróis que precisam responder a chamados espalhados pela cidade, tomando decisões estratégicas a cada turno.

---

## 🎬 Menu Inicial e Introdução

Ao iniciar o jogo, o jogador é apresentado a um **menu inicial**, que funciona como ponto de entrada da aplicação.

- O menu contém a **tela de introdução (intro)** do jogo
- A intro apresenta o clima e o contexto do universo do jogo
- Música e efeitos sonoros ajudam na imersão inicial
- A partir do menu, o jogador pode iniciar a partida

Essa separação entre **menu/intro** e **jogo principal** ajuda a organizar melhor o fluxo da aplicação.

---

## 🧠 Conceitos de POO Utilizados

O projeto aplica diversos conceitos fundamentais de Programação Orientada a Objetos, entre eles:

- Classes e Objetos
- Encapsulamento
- Herança
- Polimorfismo
- Interfaces
- Classes Abstratas
- Pacotes
- Eventos e Callbacks
- Manipulação de Exceções
- Uso de Timers
- Separação entre lógica do jogo e interface gráfica

---

## 🕹️ Funcionamento do Jogo

- O jogo é dividido em **turnos**
- A cada turno surgem **chamados** no mapa da cidade
- Os chamados podem ser **normais (amarelos)** ou **críticos (vermelhos)**
- Chamados críticos possuem **tempo limite** para resposta
- O jogador deve selecionar um herói e enviá-lo para a missão
- O sucesso ou falha depende dos atributos do herói em relação ao chamado
- Ao final dos turnos, é exibida uma **tela de resultados**

---

## 🦸‍♂️ Heróis

Cada herói possui:
- Nome
- Especialidade
- Atributos específicos
- Estado de disponibilidade
- Representação visual no painel de heróis

---

## 🗺️ Mapa da Cidade

- Os chamados aparecem como marcadores no mapa
- É possível clicar nos chamados para abrir a janela de detalhes
- Quando um herói é enviado, ele se move visualmente até o local do chamado

---

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Swing (JFrame, JPanel, JInternalFrame, Timer)**
- **IntelliJ IDEA**
- **Git e GitHub**

## ▶️ Como executar o jogo

### Pré-requisitos
- Java JDK 17 ou superior
- IntelliJ IDEA (recomendado)

### Executando pelo IntelliJ
1. Clone o repositório ou faça o download do projeto
2. Abra o IntelliJ IDEA e selecione "Open"
3. Escolha a pasta do projeto
4. Execute a classe `Main.java`

O jogo iniciará pelo menu principal, em seguida com uma tela de introdução imersiva na história,e depois vem a tela jogo permitindo ao jogador interagir com heróis, missões e chamados.
