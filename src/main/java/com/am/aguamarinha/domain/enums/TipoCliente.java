package com.am.aguamarinha.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	// Um método static, faz com que não seja necessário instanciar o objeto para que a função seja chamada.
	public static TipoCliente toEnum(Integer cod) { // Código para identificar o tipo de cliente, fisica ou juridica
		
		if (cod == null) {
			return null; // Caso seja nulo, retorna nulo (* Utilizado para proteçao)
		}
	
	
	for(TipoCliente x: TipoCliente.values() ) {
		if (cod.equals(x.getCod())) {// Se o código do x, for o msm do Integer cod irá retornar o valro de x
			return x;
		}
	}
	
	throw new IllegalArgumentException("Id inválido: " + cod); // Caso nao tenha a opção no for, a funçã irá retornar uma exceção
	}
}
