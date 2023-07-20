# avaliacao-redemob
Avaliação Técnica para a RedeMob


Para subir a aplicação:

Existe um docker que facilita a criação do container do postgres e também um init.sql que ele chama automaticamente.
Para executar basta acessar a pasta backend/docker/ e executar o comando 'docker compose -f docker-compose.yaml up -d' ou 'nerdctl compose -f docker-compose.yaml up -d'.

Caso prefira não subir o container docker, criará a database rocket no postgres e executará os comandos sql que estão contidos no arquivo init.sql


Backend:

Utilizei Java 11 e Maven 3.6.3

Rode o 'mvn clean package' na pasta backend.
Rode o 'java -jar .\backend\target\avaliacao-redemob-0.0.1-SNAPSHOT.jar' 

Ou importe a aplicação na sua IDE como uma aplicação Spring Boot e rode normalmente.

Frontend:

Instalar o Node (14.17.5) e NPM (6.14.14) E o Angular versão 11.

Rodar o comando: 'npm install --userconfig .npmrc' dentro da pasta do frontend.

Após instalação, rodar o comando 'ng serve' para inicializar

Com tudo ok, acessar link: 'localhost:4200/'


Para tela de administrador, na tela de login usar:

login: admin
senha: 123

Para outros testes, pode criar o login e senha na tela de solicitação normalmente.