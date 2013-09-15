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
@NamedQueries({
	@NamedQuery(name = "findAllCompanies", query = "Select c From Company c"),
	@NamedQuery(name = "matchCompanyById", query = "SELECT c FROM Company c WHERE c.id=:id"),})

public class Company {

	@Id 
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
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
	
	public static class Builder {
		private Company company;
		
		public Builder() {
			company = new Company();
		}
		
		public Builder id(long id) {
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
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
}
