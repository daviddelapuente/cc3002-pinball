# Pinball - Tarea CC3002

esta tarea representa la logica detras de un juego de pinball

## Introduccion

en este projecto, se encuentran los archivos para crear la logica de un juego de pinball

### Prerequisitos

debe instalarse inteliJ, y abrir con open, la carpeta donde esta este projecto, desde inteliJ

### Opening

en el escritorio debe crearse una carpeta que contenga los archivos de este programa (puede
usarse git clone desde github).

luego abrir inteliJ, ir a file->open y ahi buscar la carpeta en el arbol de carpetas.
de esta forma el projecto sera importado a inteliJ y podra editarse.


## Tests

el programa viene con test incluidos en el path src/test/java
para correrlos debe hacerse click derecho en la carpeta java o las que esa carpeta contiene
y apretar "run al test whit coverage"

estos testean la funcioinalidad de las clases que contiene el programa

para testear se recomienda usar las clases Game o HomeworktwoFacade en ellas se encuentran
los metodos que van a ser usados para la implementacion de la interfaz grafica.

inicialmente game tiene una mesa vacia, por lo que no se podra jugar en ella, es por esto
debe crearse una nueva tabla (no vacia) con la cantidad de game elements que se estimen
convenientes (ademas existe la posibilidad de agregar un seed para testear)

despues de pasarle Table a game, se pueden llamar a los elementos de table para interactuar
con ellos, como por ejemplo Bumpers o Targets. 


```
Game game=new Game();
Table table = new GameTable(10,0.5,5,5);
game.setTable(table);

game.getCurrentTable().getBumpers().get(0).hit() y puede simularse un hit
game.getCurrentTable().getBumpers().get(0).hit(900000); y este es un hit con seed 
(el seed en hit es para los bonus)

```

###implementacion

gameelements:

para los gamelements se uso un patron de template, donde se crearon bumpers y targets
abstractos y se especializaron en los KickerBumper, PopBumper, SpotTarget y DropTarget
ademas en los bumpers se implementaron los objetos bumpermode que define los upgrade de los bumpers

bonus:
para los bonus tambien se uso un template con abstract bonus

table:
en table se uso el patron null para crear una mesa vacia, ademas de gameTable que hace el resto del trabajo

cabe decir ademas, que bonus y game elements son observados por table, de esta forma pueden
avisarle a table cuando an sido golpeados
y a la ves table es observado por game para mandarle la informacion de quien fue golpeado

game:
game es una sola clase, que observa a table, para saber que elemento fue golpeado se uso el
patron visitor el cual tiene el metodo makeTheMagic() que es distinto para cada visitor
y se encarga de activar los bonus y agregar puntaje a game, the esta forma los elementos del
paquete logic no tienen que estar pendientes de sumar puntaje al juego sino que lo hace visitor.

visitor:
se implemento un visitor para cada gameelement y bonus.
 

## Build whit

* [Maven](https://maven.apache.org/) - Dependency Management