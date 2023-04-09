CREATE TABLE IF NOT EXISTS hospital.Turno (
                                Id BIGINT auto_increment NOT NULL,
                                idFuncionario BIGINT NOT NULL,
                                Data DATETIME NOT NULL,
                                CONSTRAINT Turno_PK PRIMARY KEY (Id),
                                CONSTRAINT Turno_FK FOREIGN KEY (idFuncionario) REFERENCES hospital.Funcionario(Id)
);