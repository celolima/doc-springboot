package med.vol.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.medico.DadosAtualizacaoMedico;
import med.vol.api.medico.DadosCadastroMedico;
import med.vol.api.medico.DadosDetalhamentoMedico;
import med.vol.api.medico.DadosListagemMedico;
import med.vol.api.medico.Medico;
import med.vol.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	// Códigos HTTP
	// retorna 200 - return ResponseEntity.ok(listagem);
	// retorna 201 - Requiscição processada e novo recurso criado
	// retorna 204 - return ResponseEntity.noContent().build(); 
	
	private static final String PATH_MEDICOS_ID = "/medicos/{id}";
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(dados);
		repository.save(medico);
		
		DadosDetalhamentoMedico medicoDTO = new DadosDetalhamentoMedico(medico);
		
		var uri = uriBuilder.path(PATH_MEDICOS_ID).buildAndExpand(medico.getId()).toUri(); // localização do registro recém criado
		return ResponseEntity.created(uri).body(medicoDTO);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
		Page<DadosListagemMedico> listagem = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(listagem);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<DadosDetalhamentoMedico>> listar(@PathVariable Long id) {
		return ResponseEntity.ok(repository.findById(id).map(DadosDetalhamentoMedico::new));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atulizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		//repository.deleteById(id);
		var medico = repository.getReferenceById(id);
		medico.excluir();
		
		return ResponseEntity.noContent().build(); // retorna 204
	}
	
}
