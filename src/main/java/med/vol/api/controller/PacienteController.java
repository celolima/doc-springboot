package med.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.vol.api.paciente.DadosCadastroPaciente;
import med.vol.api.paciente.Paciente;
import med.vol.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
		repository.save(new Paciente(dados));
	}
	
}
