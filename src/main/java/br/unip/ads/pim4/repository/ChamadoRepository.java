package br.unip.ads.pim4.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;

public interface ChamadoRepository extends CrudRepository<Chamado, Protocolo> {
	
	Iterable<Chamado> findAll();
	
	Optional<Chamado> findById(Protocolo protocolo);
	
	Iterable<Chamado> findByCliente(Cliente cliente);
	
	Iterable<Chamado> findByResponsavel(Atendente responsavel);
	
	Iterable<Chamado> findByDataAberturaBetween(LocalDateTime value1, LocalDateTime value2);

}
