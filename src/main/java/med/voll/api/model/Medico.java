package med.voll.api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.dto.MedicoDto;
import med.voll.api.controller.dto.PutMedicoDto;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Medico(MedicoDto medicoForm) {
		this.ativo = true;
		this.nome = medicoForm.nome();
		this.email = medicoForm.email();
		this.crm = medicoForm.crm();
		this.especialidade = medicoForm.especialidade();
		this.telefone = medicoForm.telefone();
		this.endereco = new Endereco(medicoForm.endereco());
	}

	public void atualizarInformacao(PutMedicoDto medicoForm) {
		if (medicoForm.nome() != null) {
            this.nome = medicoForm.nome();
        }
        if (medicoForm.telefone() != null) {
            this.telefone = medicoForm.telefone();
        }
        if (medicoForm.endereco() != null) {
            this.endereco.atualizarInformacoes(medicoForm.endereco());
        }
	}

	public void desativar() {
		this.ativo = false;
		
	}

	
}