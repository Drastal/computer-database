package computer.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@NamedQuery(name = "findAllCompanies", query = "Select c From Company c")
public class Company {

	@Id 
	@GeneratedValue
	private int id;
	
	@Column(name="name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static class Builder {
		private Company company;
		
		public Builder() {
			company = new Company();
		}
		
		public Builder id(int id) {
			company.setId(id);
			return this;
		}
		
		public Builder name(String name) {
			company.setName(name);
			return this;
		}
		
		public Company build() {
			return company;
		}
	}
}
