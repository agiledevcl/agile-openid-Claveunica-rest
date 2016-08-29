package cl.agile.openid;

/*
	 * Ejemplo de implementación ClaveUnica OpenIdConnect
	 * Author: Claudio Delgado
	 * Mail: dev@agile.cl
	 * Agile Ingeniería & Consultoría
	 * www.agile.cl    
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.langtag.LangTag;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import com.nimbusds.openid.connect.sdk.Nonce;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import com.nimbusds.openid.connect.sdk.claims.ACR;

/**
 * Este Servlet es el punto de entrada para hacer una consulta a Clave Única de SegPres
 * 
 */
public class OpenIdConceptual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//URL de autorización de Clave Única
	public static final String CU_URI_STR =  new String("https://www.claveunica.gob.cl/openid/authorize");

	//URL de retorno/callback registrada en Clave Única y asociada al Client ID
	public static final String RETURN_URL = "http://secure.agilesigner.com/openidconnectexample/return";
	
	// ID Cliente entregado por SegPres
	private static final String CLIENT_ID = "[ID_CLIENTE]";
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
	
		HttpSession session = request.getSession();
		
		//URL de autorización de CU
		URI uri = new URI(CU_URI_STR);
		
		//Socope de Query a Clave Única
		String scopeId = request.getParameter("scope"); //ID 1: rut, 2: nombre, 3:Sandbox
		
		//Delclaración URL a donde se responderá finalmente los datos obtenidos desde CU
		String redirectPag = request.getParameter("redirectPage");
		session.setAttribute("urlRedirect", redirectPag);
		
		String clientId = CLIENT_ID;
		session.setAttribute("clientId", clientId);
		
		//response_type: Este parámetro debe ser siempre code, esto solo indica el tipo de flujo de OpenID Connect.
		ResponseType rts = new ResponseType();
		rts.add(ResponseType.Value.CODE);

		//Scope puede ser rut, nombre, sandbox
		ScopeCU scope = new ScopeCU(scopeId);
		
		
		ClientID clientIdOpenId = new ClientID(clientId);
		// Generate random state string for pairing the response to the request
		State state = new State();
		// State se utiliza en las sigueintes fases
		session.setAttribute("state", state.getValue());
		
		// Generate nonce
		Nonce nonce = new Nonce();
		
		// Se prepara el request a CU
		AuthenticationRequest authenticationRequest = new AuthenticationRequest(
		  uri,
		  new ResponseType(ResponseType.Value.CODE),
		  scope, clientIdOpenId, new URI(RETURN_URL), state, nonce);

		  authenticationRequest =	AuthenticationRequest.parse(uri, authenticationRequest.toQueryString());
		  URI authReqURI = authenticationRequest.toURI();
		  
		  //Crea la URL de Respuesta se debe eliminar %E2%80%8B ya que la API usada lo genera y CU no lo soporta
		  response.sendRedirect(authReqURI.toString().replaceAll("%E2%80%8B",""));
				
		
		} catch(Exception e) {
			//AQUI hacer el manejo de errores
			e.printStackTrace();
			
		}

	}

}
