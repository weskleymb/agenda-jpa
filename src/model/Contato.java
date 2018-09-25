package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dao.PersistDB;

@Table
@Entity
public class Contato implements Serializable, PersistDB, Comparable<Contato> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CONTATO")
	@SequenceGenerator(name="SEQ_CONTATO", sequenceName="id_seq_contato", allocationSize=1)
    private Integer id;
    private String nome;
    private String fone;
	
    @Override
    public Integer getId() {
		return id;
	}
	
    @Override
    public void setId(Integer id) {
		this.id = id;
	}
	
    public String getNome() {
		return nome;
	}
	
    public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	
    public String getFone() {
		return fone;
	}
	
    public void setFone(String fone) {
		this.fone = fone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fone == null) ? 0 : fone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		return true;
	}

	@Override
	public int compareTo(Contato contato) {
		return contato.id.compareTo(this.id);
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", fone=" + fone + "]";
	}
    
}
