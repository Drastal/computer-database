package computer.database.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "computer")
@NamedQuery(name = "findAllMachines", query = "Select m From Machine m")
public class Machine {
	@Id 
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="introduced")
	@Temporal(TemporalType.TIMESTAMP)
	private Date introduced;
	
	@Column(name="discontinued")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinued;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
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

	public Date getIntroduced() {
		return introduced;
	}
	
	public String getDateAsString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
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
		
		public Builder id(long id) {
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
