package med.vol.api.domain.endereco;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.domain.medico.DadosAtualizacaoMedico;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	
	public Endereco(DadosEndereco dados) {
		this.logradouro = dados.logradouro();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.cidade = dados.cidade();
		this.uf = dados.uf();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}
	
	private String logradouro;
	private String bairro; 
	private String cep; 
	private String cidade; 
	private String uf;
	private String complemento;
	private String numero;
	
	public void atualizarInformacoes(@Valid DadosEndereco dadosEndereco) {
		this.logradouro = StringUtils.isEmpty(dadosEndereco.logradouro()) ? this.logradouro : dadosEndereco.logradouro();
		this.bairro = StringUtils.isEmpty(dadosEndereco.bairro()) ? this.bairro : dadosEndereco.bairro();
		this.cep = StringUtils.isEmpty(dadosEndereco.cep()) ? this.cep : dadosEndereco.cep();
		this.cidade = StringUtils.isEmpty(dadosEndereco.cidade()) ? this.cidade : dadosEndereco.cidade();
		this.uf = StringUtils.isEmpty(dadosEndereco.uf()) ? this.uf : dadosEndereco.uf();
		this.numero = StringUtils.isEmpty(dadosEndereco.numero()) ? this.numero : dadosEndereco.numero();
		this.complemento = StringUtils.isEmpty(dadosEndereco.complemento()) ? this.complemento : dadosEndereco.complemento();
	}
	
}

