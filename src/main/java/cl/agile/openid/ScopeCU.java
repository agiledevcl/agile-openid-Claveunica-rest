package cl.agile.openid;

import com.nimbusds.oauth2.sdk.Scope;

public class ScopeCU extends Scope{

	public static final String SANDBOX = "3";
	public static final String RUT = "1";
	public static final String NOMBRE = "2";

	
	public ScopeCU(String value) {
		super();
		this.add("openid");
		
		if(value.equalsIgnoreCase(SANDBOX)){
			this.add("sandbox");
		}
		if(value.equalsIgnoreCase(RUT)){
			this.add("rut");
		}if(value.equalsIgnoreCase(NOMBRE)){
			this.add("nombre");
		}
	}

}
