CREATE TABLE usuario (
                         id serial4 NOT NULL,
                         cpf varchar(255) NULL,
                         dt_nascimento date NULL,
                         login varchar(255) NULL,
                         nome varchar(255) NULL,
                         nome_mae varchar(255) NULL,
                         senha varchar(255) NULL,
                         CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login),
                         CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

INSERT INTO public.usuario
(id, cpf, dt_nascimento, login, nome, nome_mae, senha)
VALUES(999999999, NULL, NULL, 'admin', 'Admin', NULL, '123');

CREATE TABLE solicitacao (
                             id serial4 NOT NULL,
                             aprovado bool NULL,
                             foto_comprova_moradia oid NULL,
                             foto_documento oid NULL,
                             foto_rosto oid NULL,
                             usuario_id int4 NULL,
                             CONSTRAINT solicitacao_pkey PRIMARY KEY (id)
);

ALTER TABLE solicitacao ADD CONSTRAINT fkffu0flughrpsk76ojkdh1rfdb FOREIGN KEY (usuario_id) REFERENCES usuario(id);