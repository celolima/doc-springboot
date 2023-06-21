package med.vol.api.paciente;

import med.vol.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email, DadosEndereco endereco) {

}
