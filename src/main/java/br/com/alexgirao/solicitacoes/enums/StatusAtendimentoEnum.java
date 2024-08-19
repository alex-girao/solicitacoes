package br.com.alexgirao.solicitacoes.enums;

import lombok.Getter;

@Getter
public enum StatusAtendimentoEnum {
	
	AGUARDANDO_ATENDIMENTO(1),
    EM_ATENDIMENTO(2),
    FINALIZADA(3),
    ;

    private StatusAtendimentoEnum(Integer codigo){
        this.codigo = codigo;
    }

    private Integer codigo;
    
    @Override
    public String toString() {
    	return super.toString();
    }
	

}
