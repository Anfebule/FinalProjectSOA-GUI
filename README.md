# FinalProjectSOA-GUI
GUI hecha en Java y JSF Prime Faces

Enunciado del proyecto
===================
Una reconocida empresa del sector financiero, requiere dentro de su operación, agilizar sus procesos de cotización de productos para clientes finales.

El objeto social de esta empresa, es la de servir como intermediario financiero, en la consecución de la mejor alternativa para clientes finales que requieren de un préstamo bancario.

Actualmente, se tiene convenio con los siguientes Bancos:
- Banco de América
- Broken Bank
- Latin Trump’s Bank
- Banco Chibcha
- Banco del pueblo

El siguiente, es el proceso que se ha levantado para la integración:

El cliente final, llena un formulario de solicitud de crédito con la siguiente información:

1. Tipo de identificación
2. Número de identificación
3. Nombres
4. Apellidos
5. Cantidad solicitada
6. Tipo de moneda
7. Plazo en meses
8. Referencias comerciales
8.1. NIT
8.2. Nombre empresa
8.3. Dirección
9. Contacto
9.1. Nombre completo
9.2. Identificación
9.3. Teléfono contacto
10. Referencias familiares
10.1. Identificación
10.2. Nombre
10.3. Parentesco

Con la solicitud diligenciada, se realiza una consulta sobre el “Perfil del cliente”.  Esta consulta, se realiza a la central de Riesgos “ConsultaElRiesgoDeTuCliente.com”.   Se trata de una empresa, que ofrece un servicio de consulta en línea del riesgo en el clasifica a una persona según su historial.
Para utilizar este servicio, la empresa ha pagado una suscripción anual, por medio de la cual, se le permite ejecutar consultas en línea por medio de una invocación a través de una API REST.  El siguiente, es el contrato de integración ofrecido por la empresa:

| Contrato de Integración No: | 001 
| ----------------------------------
| Tipo de Integración: | REST
| Patrón de Intercambio: | Síncrono
| Formato de Entrada | JSON
| Formato de Respuesta: | JSON
| Endpoint URI: | http://192.168.0.100:8080/creditAgencyService/creditProfile
| Método de solicitud | POST
| Parametros de entrada | {
||&nbsp;&nbsp;&nbsp;&nbsp; "Cliente" : {
||&nbsp;&nbsp;&nbsp;&nbsp; "tipoIdentificacion" : "[DATO]",
||&nbsp;&nbsp;&nbsp;&nbsp; "numeroDeIdentificacion" : "[DATO]",
||&nbsp;&nbsp;&nbsp;&nbsp; "nombres" : "[DATO]",
||&nbsp;&nbsp;&nbsp;&nbsp; "apellidos" : "[DATO]"
||&nbsp;&nbsp;&nbsp;&nbsp;}
|| }
|Parámetros de salida | {
||&nbsp;&nbsp;&nbsp;&nbsp; "CreditProfile" : {
||&nbsp;&nbsp;&nbsp;&nbsp; "CreditHistoryLegth" : "[DATO. AÑOS DE HISTORIA CREDITICIA DEL CLIENTE]",
||&nbsp;&nbsp;&nbsp;&nbsp; "CreditScore" : "[DATO - PUNTAJE ASIGNADO AL CLIENTE - RANGO ENTRE 0 Y 600 PUNTOS]"
||&nbsp;&nbsp;&nbsp;&nbsp;}
||}

Cuando se tenga el perfil crediticio del cliente, se procede a consultar cuál de los bancos con los que se tiene convenio, es elegible para ofrecer.. Se tiene en cuenta las siguientes reglas:

1. Banco de América y Broken Bank solo realizan préstamos, si el monto supera los $20’000.000. 

2. Latin Trump’s Bank y Banco Chibcha solo realizan préstamos, si el monto iguala o supera los $10.000.00 y NO iguala NI supera los $20.000.000

3. Banco del pueblo solo realiza préstamos por menos de $10.000.000


Una vez se tengan los posibles bancos que puedan prestar el dinero, se realiza una invocación a sus propios puntos de integración expuestos.  Cada banco, expone un punto de integración con protocolos y/o formatos distintos.  Sin embargo, cada banco recibe como entrada el perfil crediticio y la información básica del cliente. 

En la arquitectura general se utilizaron las siguientes tecnologías:

- GUI: Java, Spring Framework, JSF, PrimeFaces, Consumidor RESTful por GET.
- Perfil de riesgo (crediticio) y Latin Trump’s Bank: AnyPoint (MuleSoft), Servicio RESTful por POST.
- Banco de América y Banco Chibcha: AnyPoint (MuleSoft), Servicio SOAP.
- Broken Bank y Banco del Pueblo: AnyPoint (MuleSoft).
- IntegrationESB: AnyPoint (MuleSoft), ESB.

La respuesta de cada banco, es la tasa de interés y el tipo de tasa al que presta el dinero.

Se toma la tasa de interés más baja y se le envía una notificación al usuario vía correo electrónico, informando el banco que le puede servir para su préstamo.

El flujo general del proceso es:

Este proyecto es el correspondiente a la GUI