package com.airlife.wsAirLife.model;

import java.security.SecureRandom;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int iduser;
	
	@Column(unique=true)
	@Pattern(regexp = "^(.+)@(.+)$")
	private String email;
	
	@Pattern(regexp = "\\p{L}*(-\\p{L}*)*")
	private String firstname;
	
	@Column(unique=true)
	@Length(min=4)
	private String login;
	
	@Length(min=6)
	private String password;
	
	@Pattern(regexp = "\\p{L}*(-\\p{L}*)*")
	private String lastname;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;
	
	@Length(min=4)
	private String address;
	
	@Length(min=8, max=20)
	@Pattern(regexp = "^(0|\\+33)[1-9]([-. ]?[0-9]{2}){4}$")
	private String num_tel;
	
	@Column(name="player_id_mobile")
	private String player_id_mobile;
	
	@Column(name="player_id_navigateurs")	
	private String player_id_navigateurs;
	
	@Column(name="confirmation_token")
	private String confirmationToken;
	
	public Users() {
		this.confirmationToken = BCrypt.hashpw(generateToken(), BCrypt.gensalt()).replaceAll("[/|.]", "");
		this.player_id_mobile = "";
		this.player_id_navigateurs = "";
	}
	
	public Users(int iduser, String email, String firstname, String login, String password, String lastname, String address, String num_tel, String player_id_mobile, String player_id_navigateurs) {
		this.iduser = iduser;
		this.email = email;
		this.firstname = firstname;
		this.login = login;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.lastname = lastname;
		this.address = address;
		this.num_tel = num_tel;
		this.player_id_mobile = player_id_mobile;
		this.player_id_navigateurs = player_id_navigateurs;
	}
	
	public String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getConfirmation_token() {
		return confirmationToken;
	}

	public void setConfirmation_token(String confirmation_token) {
		this.confirmationToken = confirmation_token;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumTel() {
		return num_tel;
	}

	public void setNumTel(String numTel) {
		this.num_tel = numTel;
	}

	public String getPlayer_id_mobile() {
		return player_id_mobile;
	}

	public void setPlayer_id_mobile(String player_id_mobile) {
		this.player_id_mobile = player_id_mobile;
	}

	public String getPlayer_id_navigateurs() {
		return player_id_navigateurs;
	}

	public void setPlayer_id_navigateurs(String player_id_navigateurs) {
		this.player_id_navigateurs = player_id_navigateurs;
	}

	@Override
	public String toString() {
		return "Users [iduser=" + iduser + ", email=" + email + ", firstname=" + firstname + ", login=" + login
				+ ", password=" + password + ", lastname=" + lastname + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", address=" + address + ", num_tel=" + num_tel + ", player_id_mobile="
				+ player_id_mobile + ", player_id_navigateurs=" + player_id_navigateurs + ", confirmation_token="
				+ confirmationToken + "]";
	}
	
}
