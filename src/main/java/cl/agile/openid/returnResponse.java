package cl.agile.openid;

/*
	 * Ejemplo de implementación ClaveUnica OpenIdConnect
	 * Author: Claudio Delgado
	 * Mail: dev@agile.cl
	 * Agile Ingeniería & Consultoría
	 * www.agile.cl    
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

/**
 * Servlet implementation class returnResponse
 */
public class returnResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SECRET = "[SECRETO DEL CLIENTID]";

	private static final String URL_CU_TOKEN = "https://www.claveunica.gob.cl/openid/token/";

	private static final String URL_CU_USER_INFO = "https://www.claveunica.gob.cl/openid/userinfo/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		String urlRedirect = (String)session.getAttribute("urlRedirect");
		
		if (!state.equals((String)session.getAttribute("state"))) {
		  // TODO proper error handling
		}
		
		try {
			HttpResponse<String> responseCU = Unirest.post(URL_CU_TOKEN)
					  .header("content-type", "application/x-www-form-urlencoded")
					  .header("cache-control", "no-cache")
					  
					  .body("code="+code+"&"
					  		+ "client_id="+clientId+"&"
					  		+ "client_secret="+SECRET+"&"
					  		+ "redirect_uri="+URLEncoder.encode(OpenIdConceptual.RETURN_URL, "UTF-8")+"&"
					  		+ "grant_type=authorization_code&"
					  		+ "state="+state+"&=")
					  .asString();
			
			System.out.println(responseCU.getBody());
			ObjectMapper mapper = new ObjectMapper();
			AccessToken accessToken = mapper.readValue(responseCU.getBody(), AccessToken.class);
			
			responseCU = Unirest.post(URL_CU_USER_INFO)
					  .header("content-type", "application/x-www-form-urlencoded")
					  .header("authorization", "Bearer " + accessToken.getAccess_token())
					  .header("cache-control", "no-cache")
					  .asString();
			System.out.println(responseCU.getBody());
			mapper = new ObjectMapper();
			CUResponse cuResponse = mapper.readValue(responseCU.getBody(), CUResponse.class);
			String redirectParameters = "nombres="+URLEncoder.encode(cuResponse.getNombres(), "UTF-8")
										+ "&apellidoPaterno="+URLEncoder.encode(cuResponse.getApellidoPaterno(), "UTF-8")
										+ "&apellidoMaterno="+URLEncoder.encode(cuResponse.getApellidoMaterno(), "UTF-8")
										+ "&RUT="+URLEncoder.encode(cuResponse.getRUT(), "UTF-8")
										+ "&sub="+URLEncoder.encode(cuResponse.getSub(), "UTF-8")
										;
			
			 response.sendRedirect(urlRedirect+"?"+redirectParameters);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
