package br.unip.ads.pim4.application.chamado.relatorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.chamado.relatorio.dto.QuantidadeChamadoPeriodoDto;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.repository.ChamadoRepository;

@Service
public class ChamadoRelatorioAppServiceDefault implements ChamadoRelatorioAppService {

	@Autowired
	private ChamadoRepository chamadoRepo;

	@Override
	public Iterable<QuantidadeChamadoPeriodoDto> chamadosUltimoAno() {
		LocalDateTime haUmAno = LocalDateTime.now().withYear(LocalDateTime.now().getYear() - 1).withDayOfMonth(1);
		LocalDateTime agora = LocalDateTime.now();
		

		ArrayList<LocalDate> datas = new ArrayList<>();
		for (int i = 12; i > -1; i--) {
			// O primeiro dia de cada mes, dos ultimos 12 meses, em ordem crescente
			datas.add(LocalDate.now().minus(i, ChronoUnit.MONTHS).withDayOfMonth(1));
		}

		
		// Preencher um hashmap, usando a data (primeiro dia de cada mes) como chave
		HashMap<LocalDate, QuantidadeChamadoPeriodoDto> hashmap = new HashMap<>();
		final String pattern = "MMMM yyyy";
		datas.forEach((LocalDate d) -> {
			String nomeIntervalo = d.format(DateTimeFormatter.ofPattern(pattern));
			QuantidadeChamadoPeriodoDto dto = new QuantidadeChamadoPeriodoDto(d.get(ChronoField.YEAR),
					d.get(ChronoField.MONTH_OF_YEAR), nomeIntervalo, 0, 0);
			hashmap.put(d, dto);
		});
		
		
		/* 
		 * Percorrer a lista de chamados, os associando à lista de datas, conforme a data de abertura.
		 * Se o chamado estiver aberto, soma à quantidade de chamados em aberto, se o chamado estiver encerrado,
		 * soma à quantidade de chamados encerrados.
		 *  
		 */
		Iterable<Chamado> chamados = chamadoRepo.findByDataAberturaBetween(haUmAno, agora);
		chamados.forEach((Chamado c) -> {
			/*
			 *  Uso a data de abertura, pegando o primeiro dia do mes, para encontrar o item no 
			 *  hashmap. 
			 */
			LocalDate d = c.getDataAbertura().toLocalDate().withDayOfMonth(1);
			QuantidadeChamadoPeriodoDto hashitem = hashmap.get(d);
			
			if (Objects.nonNull(hashitem)) {
				
				// Somar chamado ao totalizador, conforme sua situação
				if (c.isEncerrado()) {
					hashitem.incEncerrados();
				} else {
					hashitem.incAbertos();
				}
				
			} // if nonNull
			
		}); // forEach


		// Montando a lista de objetos do relatorio
		TreeSet<QuantidadeChamadoPeriodoDto> relatorio = new TreeSet<>();
		relatorio.addAll(hashmap.values());

		return relatorio;

	}

}
