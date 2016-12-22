package cl.agile.openid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CURUNResponse {
	
	
	public String sub = "";
	public RUN RUN = new RUN();
	public Name name = new Name();
	
	
	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	
	public RUN getRUN() {
		return RUN;
	}

	@JsonProperty("RolUnico")
	public void setRUN(RUN rUN) {
		RUN = rUN;
	}

	public Name getName() {
		return name;
	}

	@JsonProperty("Nombres y Apellidos")
	public void setName(Name name) {
		this.name = name;
	}

	
	
	public class Name {
		String[] nombres ={""};
		String[] apellidos ={""};
		public String[] getNombres() {
			return nombres;
		}
		@JsonProperty("Nombres")
		public void setNombres(String[] nombres) {
			this.nombres = nombres;
		}
		public String[] getApellidos() {
			return apellidos;
		}
		@JsonProperty("Apellidos")
		public void setApellidos(String[] apellidos) {
			this.apellidos = apellidos;
		}
		
	}
	
	public class RUN{
		public String numero = "";
		public String DV = "";
		public String tipo = "";
				
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public String getDV() {
			return DV;
		}
		public void setDV(String dV) {
			DV = dV;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
	}

	public String getNombresAsString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i =0;i!=this.name.nombres.length;i++)
			sb.append(" "+this.name.nombres[i]);
		return sb.toString();
	}

	public String getApellidosAsString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i =0;i!=this.name.apellidos.length;i++)
			sb.append(" "+this.name.apellidos[i]);
		return sb.toString();
	}

	public String getRUNAsString() {
		return this.RUN.getNumero()+"-"+this.RUN.getDV();
	}

	
	
	

}
