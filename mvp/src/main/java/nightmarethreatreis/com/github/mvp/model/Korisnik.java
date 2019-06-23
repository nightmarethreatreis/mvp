package nightmarethreatreis.com.github.mvp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Korisnik {
	@Id
	private long id;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	
	
	public Korisnik() {
		this(null, null);
	}
	public Korisnik(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}