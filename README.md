# Tarea Reservas de Aulas
## Profesor: AndrÃ©s Rubio del RÃ­o
## Alumno: Fabricio Israel Conde Rojas

El cliente nos acaba de dar unos nuevos requisitos a aplicar sobre la �ltima versi�n que le mostramos y que le gust� bastante. Lo que nos pide el cliente es lo siguiente:

Que la aplicaci�n no almacene los datos en ficheros y que lo haga en una base de datos creada para tal efecto.

Los datos de la BD, que es una BD MongoDB, son los siguientes:

	Servidor: localhost o 127.0.01.
	Puerto: 27017
	BD: reservasaulas
	Usuario: reservasaulas
	Contrase�a: reservasaulas-2022
	Tu tarea consiste en dotar a la aplicaci�n de la tarea anterior de un nuevo modelo de datos que en vez de utilizar ficheros para almacenar los datos lo haga haciendo uso de una Base de Datos NoSQL. Se pide al menos:

Acomodar el proyecto para que gradle gestione la dependencia con el driver para java de MongoDB en su �ltima vesi�n. Adem�s deber�s modificar el proyecto para que se puedan ejecutar todas las versiones: ficheros con IU textual, ficheros con IU gr�fica, BD con IU textual y BD con IU gr�fica.
Gestionar las aulas para que su persistencia se lleve a cabo por medio de dicha BD.
Gestionar los profesores para que su persistencia se lleve a cabo por medio de dicha BD.
Gestionar las reservas para que su persistencia se lleve a cabo por medio de dicha BD.

Para ello debes realizar las siguientes acciones:

	Lo primero que debes hacer es crear un repositorio  en GitHub a partir de tu repositorio de la tarea anterior.
	Clona dicho repositorio localmente para empezar a modificarlo. Modifica el fichero README.md para que indique tus datos y los de esta tarea. Realiza tu primer commit.
	Realiza los cambios necesarios para que el proyecto pueda contener cuatro aplicaciones diferentes: ficheros con IU textual, ficheros con IU gr�fica, BD con IU textual y BD con IU gr�fica. Haz un commit.
	Haz que la gesti�n de aulas utilice la persistencia en la BD. Haz un commit.
	Haz que la gesti�n de profesores utilice la persistencia en la BD. Haz un commit.
	Haz que la gesti�n de reservas utilice la persistencia en la BD. Haz un commit.

Se valorar�:

	La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
	La indentaci�n debe ser correcta en cada uno de los apartados.
	El nombre de las variables debe ser adecuado.
	El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa ser� validada para evitar que el programa termine abruptamente debido a una excepci�n.
	Se deben utilizar los comentarios adecuados.
	Se valorar� la correcci�n ortogr�fica tanto en los comentarios como en los mensajes que se muestren al usuario.