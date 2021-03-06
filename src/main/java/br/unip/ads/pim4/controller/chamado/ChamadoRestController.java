package br.unip.ads.pim4.controller.chamado;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.unip.ads.pim4.application.chamado.ChamadoAppService;
import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import br.unip.ads.pim4.controller.AbstractRestController;
import br.unip.ads.pim4.domain.DomainException;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends AbstractRestController {
	
	@Autowired
	private ChamadoAppService chamadoAppService;
	
	@PostMapping
	public ResponseEntity<String> criar(@RequestBody NovoChamadoDto dto) throws DomainException {
		String protocolo = chamadoAppService.criar(dto);
		URI location = super.criarUriPorId(protocolo);
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{protocolo}")
	public ResponseEntity<ChamadoResumoDto> buscar(@PathVariable("protocolo") String protocolo) {
		ChamadoResumoDto chamadoEncontrado = chamadoAppService.buscar(protocolo);
		return ResponseEntity.ok().body(chamadoEncontrado);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ChamadoResumoDto>> buscarTodos() {
		Iterable<ChamadoResumoDto> todosChamados = chamadoAppService.buscarTodos();
		return ResponseEntity.ok().body(todosChamados);
	}
	
	@GetMapping("atendente/{id}")	
	public ResponseEntity<Iterable<ChamadoResumoDto>> buscarPorAtendente(@PathVariable(name="id") String atendenteId) throws DomainException {
		Iterable<ChamadoResumoDto> chamadosDoAtendente = chamadoAppService.buscarDoAtendente(atendenteId);
		return ResponseEntity.ok().body(chamadosDoAtendente);
	}

	@PutMapping("{protocolo}/atualizar")
	public ResponseEntity<Void> atualizarChamado(@PathVariable("protocolo") String protocolo, @RequestBody AtualizaChamadoDto dto) throws DomainException {
		chamadoAppService.atualizarChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}	
	
	@PutMapping("{protocolo}/transferir")
	public ResponseEntity<Void> transferirChamado(@PathVariable("protocolo") String protocolo, @RequestBody TransfereChamadoDto dto) throws DomainException {
		chamadoAppService.transferirChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}	

	@PutMapping("{protocolo}/encerrar")
	public ResponseEntity<Void> encerrarChamado(@PathVariable("protocolo") String protocolo, @RequestBody EncerraChamadoDto dto) throws DomainException {
		chamadoAppService.encerrarChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{protocolo}")
	public ResponseEntity<Void> excluir(@PathVariable("protocolo") String protocolo) throws DomainException {
		chamadoAppService.excluir(protocolo);
		return ResponseEntity.ok().build();
	}	

}
