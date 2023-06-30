package med.vol.api.paciente;

import med.vol.api.endereco.Endereco;

public record DadosListagemPaciente(String nome, String email, Endereco endereco) {
	
	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getEndereco());
	}

}
