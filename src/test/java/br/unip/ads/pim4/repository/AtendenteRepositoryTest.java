package br.unip.ads.pim4.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtendenteRepositoryTest {
	
	@Autowired
	private AtendenteRepository atendenteRepo;
	
	private final EMail testEMail = new EMail("atendente@pimquatro.com");	
	
	@Test
	public void a001createAtendente() {	
		
		
		if (atendenteRepo.findByPessoa_Email(testEMail).isPresent()) {
			fail("Atendente já cadastrado neste banco de dados.");
		}

		Id novoId = Id.proximo();
		Cpf novoCpf = Cpf.gerarCpf();
		
		Pessoa novaPessoa = new Pessoa("Atendente Stub da Silva", novoCpf, testEMail);
		Atendente novoAtendente = new Atendente(novoId, novaPessoa, "123456");
		atendenteRepo.save(novoAtendente);
		
	}
	
	@Test
	public void a002recuperarAtendente() {
		Atendente atendente = atendenteRepo.findByPessoa_Email(testEMail).orElseGet(null);
		assertNotNull("Não recuperou o Atendente.", atendente);
	}
	
	@Test
	public void a000reportEnvironment() {
		
		System.out.println("*** ENVIRONMENT ***");
		System.out.println(System.getenv("DATASOURCE_URL"));
		System.out.println(System.getenv("DATASOURCE_DIALECT"));
		System.out.println(System.getenv("DATASOURCE_USERNAME"));
		
	}

}
