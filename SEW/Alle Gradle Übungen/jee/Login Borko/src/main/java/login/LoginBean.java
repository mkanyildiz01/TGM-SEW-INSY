package login;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginBean {
	private final static String superSecret = "DasIstDasSicherstePasswortEver";
	private final static String bestUser = "admin";

	public static final String AUTH_KEY = "app.user.name";

	private String password;
	private String username;
	private boolean authenticated;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void validation() throws IOException {
		if (password.equals(superSecret) && username.equals(bestUser)) {
			setAuthenticated(true);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, username);
			FacesContext.getCurrentInstance().getExternalContext().redirect("app/welcome.xhtml");
		} else {
			setAuthenticated(false);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Wrong Credentials", "Username or Password are incorrect"));
		}
	}

	public void logout() throws IOException {
		setAuthenticated(false);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(AUTH_KEY);
		FacesContext.getCurrentInstance().getExternalContext().redirect("info.xhtml");
	}
}
