FROM openjdk:17-alpine
MAINTAINER sameera_m

ADD bet-game-1.0.jar bet-game-1.0.jar

CMD java -jar bet-game-1.0.jar