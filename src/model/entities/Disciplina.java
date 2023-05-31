package model.entities;
import java.util.Objects;
public class Disciplina {
	private Integer iddisciplina;
	private String nomedisciplina;
	private Integer cargahoraria;
	
	public Disciplina() {
		// TODO Auto-generated constructor stub
	}

	public Disciplina(int idDisciplina, String nomedisciplina, int cargahoraria) {
		super();
		this.iddisciplina = idDisciplina;
		this.nomedisciplina = nomedisciplina;
		this.cargahoraria = cargahoraria;
	}

	public int getIdDisciplina() {
		return iddisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.iddisciplina = idDisciplina;
	}

	public String getNomedisciplina() {
		return nomedisciplina;
	}

	public void setNomedisciplina(String nomedisciplina) {
		this.nomedisciplina = nomedisciplina;
	}

	public int getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(int cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + iddisciplina + ", nomedisciplina=" + nomedisciplina + ", cargahoraria="
				+ cargahoraria + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargahoraria, iddisciplina, nomedisciplina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return cargahoraria == other.cargahoraria && iddisciplina == other.iddisciplina
				&& Objects.equals(nomedisciplina, other.nomedisciplina);
	}

}
