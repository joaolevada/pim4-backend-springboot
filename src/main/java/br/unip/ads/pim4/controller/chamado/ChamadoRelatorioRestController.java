package br.unip.ads.pim4.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.ads.pim4.application.chamado.relatorio.ChamadoRelatorioAppService;
import br.unip.ads.pim4.application.chamado.relatorio.dto.QuantidadeChamadoPeriodoDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import br.unip.ads.pim4.controller.AbstractRestController;
import io.swagger.annotations.Api;

@Api(tags= SwaggerConfig.TAG_CHAMADORELATORIO)
@RestController
@RequestMapping("/api/chamados/relatorios")
public class ChamadoRelatorioRestController extends AbstractRestController {
	
	@Autowired
	private ChamadoRelatorioAppService relatorioAppService;
	
	@GetMapping("/resumo/ultimoano")	
	public ResponseEntity<Iterable<QuantidadeChamadoPeriodoDto>> obterResumoUltimoAno() {
		
		Iterable<QuantidadeChamadoPeriodoDto> chamados = relatorioAppService.chamadosUltimoAno();
		return ResponseEntity.ok().body(chamados);
		
	}

}
