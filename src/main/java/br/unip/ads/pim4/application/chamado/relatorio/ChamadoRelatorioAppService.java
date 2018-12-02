package br.unip.ads.pim4.application.chamado.relatorio;

import br.unip.ads.pim4.application.chamado.relatorio.dto.QuantidadeChamadoPeriodoDto;

public interface ChamadoRelatorioAppService {
	
	Iterable<QuantidadeChamadoPeriodoDto> chamadosUltimoAno();

}
