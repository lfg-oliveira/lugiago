CREATE TABLE IF NOT EXISTS hospital.Funcionario (
      Id BIGINT auto_increment NOT NULL,
      Nome varchar(100) NOT NULL,
      Cargo BIGINT NOT NULL,
      Codigo BIGINT NOT NULL,
      CONSTRAINT Funcionario_PK PRIMARY KEY (Id),
      CONSTRAINT Funcionario_FK FOREIGN KEY (Cargo) REFERENCES hospital.Cargo(Id)
);