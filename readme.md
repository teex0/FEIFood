# Projeto FEIFood 

**Projeto acadêmico da disciplina CCP140 (Profa. Gabriela Biondi).**

O FEIFood é uma plataforma de pedidos de alimentos desenvolvida como projeto final da disciplina. O sistema é inspirado em plataformas como o iFood, permitindo que usuários se cadastrem, façam login, busquem por alimentos, montem um carrinho de compras e avaliem seus pedidos.

O projeto foi desenvolvido seguindo a arquitetura **MVC (Model-View-Controller)** e utiliza **Java Swing** para a interface gráfica, **JDBC** para a comunicação com o banco de dados e **PostgreSQL** como SGBD.

---

## 1. Tecnologias Utilizadas 

* **Linguagem:** Java (JDK 21)
* **Interface Gráfica (GUI):** Java Swing
* **Banco de Dados:** PostgreSQL
* **Driver de Conexão:** JDBC (postgresql-42.7.8.jar)
* **Arquitetura:** MVC (Model-View-Controller)
* **IDE:** Apache NetBeans
* **Database Hosting:** O projeto está configurado para usar um banco de dados PostgreSQL hospedado na nuvem (Neon.tech).

---

## 2. Funcionalidades Implementadas 

O sistema foi projetado para atender às funcionalidades essenciais de um aplicativo de delivery de comida, com foco no perfil do usuário.

### Autenticação de Usuário
* **Cadastro:** Novos usuários podem se registrar fornecendo Nome, Nome de Usuário e Senha.
* **Login:** Usuários existentes podem acessar o sistema com seu nome de usuário e senha. A autenticação é validada contra o banco de dados.

### Navegação e Pedidos
* **Tela Principal:** Após o login, o usuário é direcionado para a `TelaPrincipalUsuarioView`, onde pode navegar entre os restaurantes parceiros:
    * El Cacto (Comida Mexicana)
    * La Nonna Trattoria (Comida Italiana)
    * Satorii Sushi (Comida Japonesa)
    * Route 66 Burger (Hamburgueria)
* **Busca de Alimentos:** Uma tela de busca (`BuscarAlimentoView.java`) permite ao usuário procurar por um alimento específico pelo nome em todos os restaurantes.
* **Cardápio do Restaurante:** Cada restaurante possui sua própria tela (`Elcacto.java`, `Trattoria.java`, `Sushi.java`, `Burger.java`), onde os itens do cardápio são listados diretamente do banco de dados.
* **Carrinho de Compras (`Carrinho.java`):**
    * **Adicionar Itens:** O usuário pode adicionar itens ao carrinho informando o ID do produto na tela do restaurante.
    * **Remover Itens:** O usuário pode remover itens do carrinho da mesma forma.
    * **Cálculo de Imposto:** O sistema implementa a interface `ImpostoAlcool`. Se um item for uma `Bebida` e for alcoólica (`isAlcoolica()`), um imposto de 100% é adicionado ao preço final desse item.
    * **Atualização de Total:** O valor total do carrinho é calculado e atualizado em tempo real na interface.
* **Finalizar Pedido:** O usuário pode finalizar a compra a partir da tela do carrinho. O sistema exibe uma mensagem de confirmação e, em seguida, redireciona o usuário para a tela de avaliação.
    * *Nota: Conforme a especificação, a lógica de pagamento e acompanhamento de entrega não foi implementada*.

### Avaliação
* **Avaliar Pedido:** Após a "compra", o usuário pode avaliar o pedido na tela `AvaliacaoView.java`.
* **Nota:** A avaliação é feita através de um `JSlider`, permitindo uma nota de 1 a 5 estrelas.
* **Persistência:** A nota é salva no banco de dados através do `AvaliacaoDAO`.

---

## 3. Arquitetura do Projeto (MVC) 

O projeto é estruturado no padrão MVC (Model-View-Controller) e utiliza o padrão DAO (Data Access Object) para a camada de persistência.

### Model
O pacote `model` contém as classes que representam os dados da aplicação, alinhadas com o diagrama de classes do projeto.
* `Usuario.java`: Armazena os dados do cliente (nome, usuário, senha).
* `Alimento.java`: Classe abstrata base para os itens vendáveis (possui código, nome, preço, etc.).
* `Comida.java`: Subclasse de `Alimento`.
* `Bebida.java`: Subclasse de `Alimento` que implementa `ImpostoAlcool`.
* `ImpostoAlcool.java`: Interface que define o método `calcImposto()`.
* `Pedido.java` e `Avaliacao.java`: Classes de modelo para representar pedidos e avaliações.

### View
O pacote `view` contém todas as janelas (JFrames) construídas com Java Swing.
* `LoginView.java` / `.form`: Tela inicial de login e cadastro.
* `CadastroUsuarioView.java` / `.form`: Tela de registro de novos usuários.
* `TelaPrincipalUsuarioView.java` / `.form`: Hub principal de navegação.
* `BuscarAlimentoView.java` / `.form`: Tela de busca de alimentos.
* `Carrinho.java` / `.form`: Tela do carrinho de compras.
* `AvaliacaoView.java` / `.form`: Tela para avaliação do pedido.
* `Elcacto.java`, `Trattoria.java`, `Sushi.java`, `Burger.java`: Telas de cardápio dos restaurantes.

### Controller
O pacote `controller` faz a ponte entre o Model e a View, contendo a lógica de negócios.
* `UsuarioController.java`: Valida o login e gerencia a sessão.
* `AlimentoController.java`: Processa a busca por alimentos.
* `AvaliacaoController.java`: Salva a nota da avaliação.
* `PedidoController.java` (e suas variantes): Gerenciam a lógica de adicionar/remover itens do carrinho e listar o cardápio de um restaurante específico.

### DAO (Data Access Object)
O pacote `dao` é responsável exclusivamente pela comunicação com o banco de dados PostgreSQL.
* `ConnectionDAO.java`: Fornece um método estático `getConnection()` para obter uma conexão com o banco de dados.
* `UsuarioDAO.java`: Contém os métodos `consultar(Usuario)` (para login) e `cadastrarUsuario(Usuario)`.
* `AlimentoDAO.java`: Contém os métodos `buscarPorNome(String)`, `buscarPorRestaurante(String)` e `buscarPorId(int)`.
* `AvaliacaoDAO.java`: Contém o método `salvarAvaliacao(int nota)`.

---

## 4. Diagrama de Classes UML

O projeto segue o diagrama de classes fornecido na especificação:

* **Usuário** faz um **Pedido** (Agregação).
* **Pedido** contém múltiplos **Alimentos** (Agregação).
* **Estabelecimento** (modelado como um atributo de `Alimento`) oferece **Alimentos**.
* **Alimento** é uma classe abstrata/pai.
* **Comida** e **Bebida** são subclasses (herança) de `Alimento`.
* **Bebida** implementa a interface **Imposto_Alcool**.

---

## 5. Configuração e Execução 

### Pré-requisitos
1.  **Java (JDK):** JDK 21 ou superior.
2.  **PostgreSQL:** Um servidor PostgreSQL ativo e acessível.
3.  **Banco de Dados:** As tabelas (`usuarios`, `alimentos`, `avaliacoes`) devem ser criadas no banco de dados.
