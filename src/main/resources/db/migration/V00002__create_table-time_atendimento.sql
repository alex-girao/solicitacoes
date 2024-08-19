create table time_atendimento (
    id bigint auto_increment not null,
    nome varchar(255) not null,
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    id_tipo_solicitacao bigint not null,
    primary key (id)
);

alter table time_atendimento
    add constraint fk_timate_tipo_solicitacao
        foreign key (id_tipo_solicitacao)
            references tipo_solicitacao (id);