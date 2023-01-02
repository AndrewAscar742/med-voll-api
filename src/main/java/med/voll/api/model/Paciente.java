package med.voll.api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.dto.form.PacienteDto;
import med.voll.api.controller.dto.form.PutPacienteDto;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Boolean ativo;

	@Embedded // atributo composto
	private Endereco endereco;

	public Paciente(PacienteDto dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
	}

	public void atualizarInformacoes(PutPacienteDto dados) {
	    if (dados.nome() != null)
	        this.nome = dados.nome();

	    if (dados.telefone() != null)
	        this.telefone = dados.telefone();

	    if (dados.endereco() != null)
	        endereco.atualizarInformacoes(dados.endereco());
	}

	public void inativar() {
	    this.ativo = false;
	}

}
