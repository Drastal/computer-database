package computer.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@NamedQueries({//Liste des requetes utilisees dans la classe CompanyDaoImpl
	@NamedQuery(name = "findAllCompanies", query = "Select c From Company c"),
	@NamedQuery(name = "matchCompanyById", query = "SELECT c FROM Company c WHERE c.id=:id"),})

public class Company {

	@Id 
	@GeneratedValue
	private long id;//Id de la company
	
	@Column(name="name")
	private String name;//Nom de la company
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
