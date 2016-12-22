package cl.agile.openid;

/*
	 * Ejemplo de implementación ClaveUnica OpenIdConnect
	 * Author: Claudio Delgado
	 * Mail: dev@agile.cl
	 * Agile Ingeniería & Consultoría
	 * www.agile.cl    
 */

public class AccessToken {
	
	public String access_token;
	public String token_type;
	public int expires_in;
	public String id_token;
	public String refresh_token;
<<<<<<< HEAD
	public String error_description;
	public String error;
=======
	
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
>>>>>>> branch 'master' of https://github.com/agiledevcl/agile-openid-Claveunica-rest.git
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getId_token() {
		return id_token;
	}
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}
	
	
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	
	

}
