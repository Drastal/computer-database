package computer.database.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "computer")
@NamedQuery(name = "findAllMachines", query = "Select m From Machine m")
public class Machine {
	@Id 
	@GeneratedValue
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="introduced")
	private Date introduced;
	
	@Column(name="discontinued")
	private Date discontinued;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static class Builder {
		private Machine machine;
		
		public Builder() {
			machine = new Machine();
		}
		
		public Builder id(int id) {
			machine.setId(id);
			return this;
		}
		
		public Builder name(String name) {
			machine.setName(name);
			return this;
		}
		
		public Builder introduced(Date introduced) {
			machine.setIntroduced(introduced);
			return this;
		}
		
		public Builder discontinued(Date discontinued) {
			machine.setDiscontinued(discontinued);
			return this;
		}
		
		public Builder company(Company company) {
			machine.setCompany(company);
			return this;
		}
		
		public Machine build() {
			return machine;
		}
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", name=" + name + ", introduced=" + introduced + 
				", discontinued=" + discontinued + ", discontinued=" + discontinued + 
				", company= " + company + "]";
	}
	
	
}
