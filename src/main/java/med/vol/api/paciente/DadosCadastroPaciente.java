package med.vol.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email,
		
		@NotNull
		@Valid
		DadosEndereco endereco		
		) {

}
