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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "computer")
@NamedQueries({//Requetes sur la table des machines utilisees dans la classe MachineDaoImpl
	@NamedQuery(name = "findAllMachines", query = "Select m From Machine m"),
	@NamedQuery(name = "matchMachineById", query = "SELECT m FROM Machine m WHERE m.id = :machineId"),
    @NamedQuery(name = "searchMachine", query = "Select m From Machine m WHERE name LIKE :searching")})

public class Machine {
	@Id 
	@GeneratedValue
	private long id;//Id de la machine
	
	@Column(name="name")
	private String name;//Nom de la machine
	
	@Column(name="introduced")
	@Temporal(TemporalType.TIMESTAMP)
	private Date introduced;//Date d'introduction de la machine
	
	@Column(name="discontinued")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinued;//Date d'arret de la machine
	
	@ManyToOne(fetch=FetchType.EAGER)//Prend en compte le fait que plusieurs ordinateurs peuvent appartenir a une meme compagnie
	@JoinColumn(name="company_id")//Fait la relation Id company <-> Nom de la company
	private Company company;//Compagnie (Id + nom)
	
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

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	
	private String getDateAsString(Date inputDate) {
		//Convertit une date au bon format et en chaine de caracteres, prend en charge les dates null
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;
        if (inputDate != null) {
            date = df.format(inputDate);
        }
        return date;
	}
	
	public String getIntroducedAsString() {
		//Retourne la date introduced en String et format souhaite
		return getDateAsString(introduced);
	}
	
	public String getDiscontinuedAsString(){
		//Retourne la date discontinued en String et format souhaite
		return getDateAsString(discontinued);
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

	public static class Builder {
		//Classe utilisee lors de l'ajout d'une nouvelle machine
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
}
