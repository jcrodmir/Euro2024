API Eurocopa 2024:

Este proyecto tiene como único proposito el aprendizaje, ya que busca poner en práctica 
los conocimientos adquiridos durante mis prácticas.  Además, me he dedicado a investigar y 
añadir nuevas funcionalidades que desconocía.

El proyecto es una API de la Eurocopa 2024 donde con diferentes llamadas podremos acceder
a los datos más relevantes de jugadores y  equipos participantes.

El proyecto consiste en una API sobre la Eurocopa 2024, que permite acceder a la información 
más relevante de los jugadores y equipos participantes a través de diversas consultas.

Todo los datos han sido obtenidos  de la página oficial de la Eurocopa 2024 https://www.uefa.com/euro2024/teams/.

Version 1.0.0:

/********************* Players *********************/

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player?pageNo=1&pageSize=10

Call for all the players

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/id/{id}

Call  the player by ID.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/name?name

Call  the player by Name.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/goal?number

Call  the player by Goal.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/yellowcard?number

Call  the player by Yellow cards.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/redcard?number

Call  the player by Red card.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/save?number

Call  the player by Goalkeeper saves

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/assist?number

Call  the player by Assist.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/recover?number

Call  the player by Ball Recover .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/dorsal?number

Call  the player by Dorsal .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/minute?number

Call  the player by Minutes .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/match?number

Call  the player by Matches .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/position?position

Call  the player by Position("Portero","Defensa","Mediocampistam,"Delantero") .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/team/{teamId}

Call  the player by Team Id .


Call all the players by properties order by descendant .

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/goals

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/yellowcards

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/redcards

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/saves

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/assists

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/recovered

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/dorsals

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/minutes

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/player/matches

/********************* Teams *********************/

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team?pageNo=1&pageSize=10

Call for all the teams

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/id/{id}

Call  the team by ID.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/country?country

Call  the team by Country.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/coach?coach

Call  the team by Coach.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/federation?federation

Call  the team by Federation.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/foundation?foundation

Call  the team by Foundation.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/championships?championships

Call  the team by Championships.

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/{country}/championships

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/{country}/coach

![Get](./assets/get.png) https://euro2024-naqw.onrender.com/v1/team/{country}/federation

Call the properties by country






