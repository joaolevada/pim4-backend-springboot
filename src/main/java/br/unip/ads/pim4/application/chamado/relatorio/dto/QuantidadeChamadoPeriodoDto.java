package br.unip.ads.pim4.application.chamado.relatorio.dto;

public class QuantidadeChamadoPeriodoDto implements Comparable<QuantidadeChamadoPeriodoDto> {
	
	int ano;
	int mes;
	String nomeIntervalo;
	int abertos;
	int encerrados;
	
	public QuantidadeChamadoPeriodoDto() {
		// Rest
	}	

	public QuantidadeChamadoPeriodoDto(int ano, int mes, String nomeIntervalo, int abertos, int encerrados) {
		super();
		this.ano = ano;
		this.mes = mes;
		this.nomeIntervalo = nomeIntervalo;
		this.abertos = abertos;
		this.encerrados = encerrados;
	}
	
	

	public int getAno() {
		return ano;
	}

	public int getMes() {
		return mes;
	}

	public String getNomeIntervalo() {
		return nomeIntervalo;
	}

	public int getAbertos() {
		return abertos;
	}
	
	public void setAbertos(int abertos) {
		this.abertos = abertos;
	}

	public int getEncerrados() {
		return encerrados;
	}
	
	public void setEncerrados(int encerrados) {
		this.encerrados = encerrados;
	}
	
	public void incAbertos() {
		this.abertos++;
	}
	
	public void incEncerrados() {
		this.encerrados++;
	}

	@Override
	public int compareTo(QuantidadeChamadoPeriodoDto other) {		
	 	int compareAno = new Integer(getAno()).compareTo(other.getAno());
	 	if (compareAno != 0) {
	 		return compareAno;
	 	} else {
	 		return new Integer(getMes()).compareTo(other.getMes());
	 	}
	}		
	
}
