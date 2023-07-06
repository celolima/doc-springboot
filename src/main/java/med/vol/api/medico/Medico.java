package med.vol.api.medico;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; 
	private String email; 
	private String telefone;
	private String crm; 
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
		this.ativo = Boolean.TRUE;
	}

	public void atulizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
		this.nome = StringUtils.isEmpty(dados.nome()) ? this.nome : dados.nome();
		this.telefone = StringUtils.isEmpty(dados.telefone()) ? this.telefone : dados.telefone();
		if(dados.dadosEndereco() != null) {
			this.endereco.atualizarInformacoes(dados.dadosEndereco());
		}
	}

	public void excluir() {
		this.ativo = Boolean.FALSE;
	}
	
}
