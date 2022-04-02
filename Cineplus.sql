CREATE DATABASE Cineplus;

USE Cineplus;

CREATE TABLE Salas
(
	Id smallint auto_increment primary key,
    NumSala smallint not null,
    CanAsientos smallint not null,
    Id_Pelicula smallint not null,
    unique(NumSala),
    unique(Id_Pelicula),
    constraint fk_sala_peli foreign key (Id_Pelicula) references Peliculas(Id)
);

CREATE TABLE Peliculas
(
	Id smallint auto_increment primary key,
    NomPeli varchar(50) not null,
    HoraPeli varchar(5) not null,
    Duracion smallint not null,
    Idioma varchar(20) not null,
    Id_Clasificacion smallint not null,
    constraint fk_peli_clasi foreign key (Id_Clasificacion) references Clasificacion(Id)
);

CREATE TABLE Clasificacion
(
	Id smallint auto_increment primary key,
    Clasificacion VARCHAR(3)
);
INSERT INTO Clasificacion (Clasificacion) VALUES ('AA'),('A'),('B'),('B15'),('C'),('D');

CREATE TABLE VentaBoletos
(
	Id smallint auto_increment primary key,
    NomPeli varchar(50) not null,
    NumSala smallint not null,
    NumAsiento smallint not null,
    Id_TipoBoletos smallint not null,
    TotalBoleto smallint not null,
    constraint fk_venta_tipobolero foreign key (Id_TipoBoletos) references TipoBoletos(Id)
);

CREATE TABLE TipoBoletos
(
	Id smallint auto_increment primary key,
    TipoBoleto VARCHAR(20)
);
INSERT INTO TipoBoletos (TipoBoleto) VALUES ('Adulto'),('Niño'),('Tarjeta');






