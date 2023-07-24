insert into usuario values ("1004870909","contraseña1","direccion1","correo1@gmail.com",1,"Juan","3136142910");
insert into usuario values ("1004399032","contraseña2","direccion2","correo2@gmail.com",0,"Didier","3136142911");
insert into usuario values ("1004223311","contraseña3","direccion3","correo3@gmail.com",1,"Juan Pablo","3136142912");
insert into usuario values ("1004254687","contraseña4","direccion4","correo4@gmail.com",0,"Pedro","3136142913");
insert into usuario values ("1004944713","contraseña5","direccion5","correo5@gmail.com",1,"Maria","3136142914");

insert into producto values (1,"descripcion1","SIN_REVISAR","2023-12-1 12:45:56","imagen1",1,1,"Xbox Series X",2000,5,"1004870909");
insert into producto values (2,"descripcion2","AUTORIZADO","2023-12-2 12:45:56","imagen2",0,0,"Play 5",3000,10,"1004399032");
insert into producto values (3,"descripcion2","AUTORIZADO","2023-12-2 12:45:56","imagen2",1,0,"Play 4",1000,3,"1004399032");
insert into producto values (4,"descripcion3","DENEGADO","2023-12-3 12:45:56","imagen3",1,0,"Pc gamer",5000,7,"1004223311");

insert into producto_categorias values (1,"TECNOLOGIA");
insert into producto_categorias values (3,"TECNOLOGIA");

insert into usuario_productos_favoritos values ("1004870909",2);



