package computer.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQuery(name = "findAllUsers", query = "Select u From User u")
public class User {
	@Id 
	@GeneratedValue
	private int id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static class Builder {
		private User user;
		
		public Builder() {
			user = new User();
		}
		
		public Builder id(int id) {
			user.setId(id);
			return this;
		}
		
		public Builder login(String login) {
			user.setLogin(login);
			return this;
		}
		
		public Builder password(String password) {
			user.setPassword(password);
			return this;
		}
		
		public User build() {
			return user;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ "]";
	}
	
	
}
