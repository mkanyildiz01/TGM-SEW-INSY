/**
 *  Class: User
 *  Datum: 11.06.2016
 *  Name: Kanyildiz Muhammedhizir
 *  Klasse: 4AHITM
 */
package model;

import javax.faces.context.FacesContext;

public class User {
	private String name;
	private String password;
	private boolean loggedIn;

	public User() {
		loggedIn = false;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		String ret = "failed";

		return ret;
	}

	public String logout() {
		loggedIn = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "logout";
	}
}