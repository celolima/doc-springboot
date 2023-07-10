package med.vol.api.domain.paciente;

import med.vol.api.domain.endereco.Endereco;

public record DadosListagemPaciente(String nome, String email, Endereco endereco) {
	
	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getEndereco());
	}

}
