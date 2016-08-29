package cl.agile.openid;


/*
	 * Ejemplo de implementación ClaveUnica OpenIdConnect
	 * Author: Claudio Delgado
	 * Mail: dev@agile.cl
	 * Agile Ingeniería & Consultoría
	 * www.agile.cl    
 */

public class CUResponse {
	
	public String nombres = "";
	public String apellidoPaterno = "";
	public String apellidoMaterno = "";
	public String sub = "";
	public String RUT = "";
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getRUT() {
		return RUT;
	}
	public void setRUT(String rUT) {
		RUT = rUT;
	}
	
	

}
