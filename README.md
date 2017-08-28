<h1 id="finalprojectsoa-gui">FinalProjectSOA-GUI</h1>

<p>GUI hecha en Java y JSF Prime Faces</p>



<h1 id="enunciado-del-proyecto">Enunciado del proyecto</h1>

<p>Una reconocida empresa del sector financiero, requiere dentro de su operación, agilizar sus procesos de cotización de productos para clientes finales.</p>

<p>El objeto social de esta empresa, es la de servir como intermediario financiero, en la consecución de la mejor alternativa para clientes finales que requieren de un préstamo bancario.</p>

<p>Actualmente, se tiene convenio con los siguientes Bancos: <br>
- Banco de América <br>
- Broken Bank <br>
- Latin Trump’s Bank <br>
- Banco Chibcha <br>
- Banco del pueblo</p>

<p>El siguiente, es el proceso que se ha levantado para la integración:</p>

<p>El cliente final, llena un formulario de solicitud de crédito con la siguiente información:</p>

<ol>
<li>Tipo de identificación</li>
<li>Número de identificación</li>
<li>Nombres</li>
<li>Apellidos</li>
<li>Cantidad solicitada</li>
<li>Tipo de moneda</li>
<li>Plazo en meses</li>
<li>Referencias comerciales <br>
8.1. NIT <br>
8.2. Nombre empresa <br>
8.3. Dirección</li>
<li>Contacto <br>
9.1. Nombre completo <br>
9.2. Identificación <br>
9.3. Teléfono contacto</li>
<li>Referencias familiares <br>
10.1. Identificación <br>
10.2. Nombre <br>
10.3. Parentesco</li>
</ol>

<p>Con la solicitud diligenciada, se realiza una consulta sobre el “Perfil del cliente”.  Esta consulta, se realiza a la central de Riesgos “ConsultaElRiesgoDeTuCliente.com”.   Se trata de una empresa, que ofrece un servicio de consulta en línea del riesgo en el clasifica a una persona según su historial. <br>
Para utilizar este servicio, la empresa ha pagado una suscripción anual, por medio de la cual, se le permite ejecutar consultas en línea por medio de una invocación a través de una API REST.  El siguiente, es el contrato de integración ofrecido por la empresa:</p>

<table>
<thead>
<tr>
  <th>Contrato de Integración No:</th>
  <th>001</th>
</tr>
</thead>
<tbody><tr>
  <td>Tipo de Integración:</td>
  <td>REST</td>
</tr>
<tr>
  <td>Patrón de Intercambio:</td>
  <td>Síncrono</td>
</tr>
<tr>
  <td>Formato de Entrada</td>
  <td>JSON</td>
</tr>
<tr>
  <td>Formato de Respuesta:</td>
  <td>JSON</td>
</tr>
<tr>
  <td>Endpoint URI:</td>
  <td><a href="http://192.168.0.100:8080/creditAgencyService/creditProfile">http://192.168.0.100:8080/creditAgencyService/creditProfile</a></td>
</tr>
<tr>
  <td>Método de solicitud</td>
  <td>POST</td>
</tr>
<tr>
  <td>Parametros de entrada</td>
  <td>{</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “Cliente” : {</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “tipoIdentificacion” : “[DATO]”,</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “numeroDeIdentificacion” : “[DATO]”,</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “nombres” : “[DATO]”,</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “apellidos” : “[DATO]”</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp;}</td>
</tr>
<tr>
  <td></td>
  <td>}</td>
</tr>
<tr>
  <td>Parámetros de salida</td>
  <td>{</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “CreditProfile” : {</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “CreditHistoryLegth” : “[DATO. AÑOS DE HISTORIA CREDITICIA DEL CLIENTE]”,</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp; “CreditScore” : “[DATO - PUNTAJE ASIGNADO AL CLIENTE - RANGO ENTRE 0 Y 600 PUNTOS]”</td>
</tr>
<tr>
  <td></td>
  <td>&nbsp;&nbsp;&nbsp;&nbsp;}</td>
</tr>
<tr>
  <td></td>
  <td>}</td>
</tr>
</tbody></table>


<p>Cuando se tenga el perfil crediticio del cliente, se procede a consultar cuál de los bancos con los que se tiene convenio, es elegible para ofrecer.. Se tiene en cuenta las siguientes reglas:</p>

<ol>
<li><p>Banco de América y Broken Bank solo realizan préstamos, si el monto supera los $20’000.000. </p></li>
<li><p>Latin Trump’s Bank y Banco Chibcha solo realizan préstamos, si el monto iguala o supera los <script type="math/tex" id="MathJax-Element-121">10.000.00 y NO iguala NI supera los </script>20.000.000</p></li>
<li><p>Banco del pueblo solo realiza préstamos por menos de $10.000.000</p></li>
</ol>

<p>Una vez se tengan los posibles bancos que puedan prestar el dinero, se realiza una invocación a sus propios puntos de integración expuestos.  Cada banco, expone un punto de integración con protocolos y/o formatos distintos.  Sin embargo, cada banco recibe como entrada el perfil crediticio y la información básica del cliente. </p>

<p>En la arquitectura general se utilizaron las siguientes tecnologías:</p>

<ul>
<li>GUI: Java, Spring Framework, JSF, PrimeFaces, Consumidor RESTful por GET.</li>
<li>Perfil de riesgo (crediticio) y Latin Trump’s Bank: AnyPoint (MuleSoft), Servicio RESTful por POST.</li>
<li>Banco de América y Banco Chibcha: AnyPoint (MuleSoft), Servicio SOAP.</li>
<li>Broken Bank y Banco del Pueblo: AnyPoint (MuleSoft).</li>
<li>IntegrationESB: AnyPoint (MuleSoft), ESB.</li>
</ul>

<p>La respuesta de cada banco, es la tasa de interés y el tipo de tasa al que presta el dinero.</p>

<p>Se toma la tasa de interés más baja y se le envía una notificación al usuario vía correo electrónico, informando el banco que le puede servir para su préstamo.</p>

<p>Este proyecto es el correspondiente a la GUI</p>