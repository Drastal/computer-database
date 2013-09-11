package computer.database.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//@Entity
//@Table(name = "user")
//@NamedQuery(name = "findAllUsers", query = "Select u From User u")
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
	
	@Column(name="company_id")
	private int company_id;
	
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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
		
		public Builder company_id(int company_id) {
			machine.setCompany_id(company_id);
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
				", company id= " + company_id + "]";
	}
	
	
}
