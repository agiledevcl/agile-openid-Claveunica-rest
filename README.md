# agile-openid-Claveunica-rest

El presente ejemplo se basa en J2EE utilizando el Servlet contiener para realizar los pasos de intgeración con [CLAVE ÚNICA](http://www.claveunica.cl) especificados por la Unidad de Modernización y Gobierno Digital del Ministerio Secretaría General de la Presidencia.

[Los pasos](https://www.claveunica.gob.cl/documentacion/configuracion.html) 1 y 2 se implementan el la clase ```OpenIdConceptual```, los pasos 3, 4, 5 y 6 en la clase returnResponse

## Instrucciones

1 Declarar el valor de las constantes constantes: 

* ```OpenIdConceptual.CLIENT_ID``` ID de cliente entregado por SegPres
* ```returnResponse.SECRET``` Secrete asociado al cliente id entregado por SegPres
* ```OpenIdConceptual.RETURN_URL```URL de callback/retorno declarada a SegPres para el servicio que utilizará Clave Única

2 Configurar el web.xml para los servlets ``` OpenIdConceptual returnResponse```para que tengan los ```url-pattern```correctos a tu configuración.

3 Correr Maven para compilar y en paquetar.

## Ejecutar

http://localhost:8080/OpenID/OpenIdCU?scope=3&redirectPage=http://localhost/OpenID/showResult

Donde Scope 3 es SandBox Clave Unica y redirectPage es la página donde se mostrará el resultado final de la operación.

## Dudas o feedback a : dev@agile.cl
