create table solicitacao (
    id bigint auto_increment not null,
    texto varchar(1000) not null,
    id_tipo_solicitacao bigint not null,
    id_atendente bigint,
    status_atendimento ENUM (
		'AGUARDANDO_ATENDIMENTO',
    	'EM_ATENDIMENTO',
		'FINALIZADA'
	),
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    primary key (id)
);

alter table solicitacao
    add constraint fk_sol_tipo_solicitacao
        foreign key (id_tipo_solicitacao)
            references tipo_solicitacao (id);
            
alter table solicitacao
    add constraint fk_sol_atendente
        foreign key (id_atendente)
            references atendente (id);