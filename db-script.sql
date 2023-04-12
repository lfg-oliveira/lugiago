drop database if exists hospital;
create database hospital;
use hospital;

drop table if exists Turno;
drop table if exists Funcionario;
drop table if exists Cargo;

drop table if exists Cargo;
create table Cargo(
Id bigint not null primary key auto_increment,
TipoCodigo varchar(10) not null,
Descricao varchar(50) not null
);

drop table if exists Funcionario;
create table Funcionario(
Id bigint not null primary key auto_increment,
Codigo bigint not null unique key,
Nome varchar(80) not null,
idCargo varchar(30) not null,
foreign key (idCargo) references Cargo(Id)
);

drop table if exists Turno;
create table Turno (
Id bigint not null primary key auto_increment,
IdFuncionario bigint not null,
Data Timestamp not null,
foreign key (IdFuncionario) references Funcionario(Id)
);

insert into Cargo(Descricao, TipoCodigo)
values ('Enfermeiro', 'COREN')
	  ,('Médico', 'CRM')
	  ,('Residente', 'CRM');