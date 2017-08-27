package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "formController")
@ViewScoped
public class FormController {
	
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombres;
	private String apellidos;
	private String cantidadSolicitada;
	private String tipoMoneda;
	private String plazoMeses;

	//Referencias comerciales
	private String refNITEmpresa;
	private String refNombreEmpresa;
	private String refDireccionEmpresa;

	//Contacto referencias
	private String refNombreContacto;
	private String refContIdentificacionContacto;
	private String refContTelefonoContacto;

	//Referencias familiares
	private String refIdentificacionFamiliar;
	private String refNombreFamiliar;
	private String refParentescoFamiliar;
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(String cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	public String getPlazoMeses() {
		return plazoMeses;
	}
	public void setPlazoMeses(String plazoMeses) {
		this.plazoMeses = plazoMeses;
	}
	public String getRefNITEmpresa() {
		return refNITEmpresa;
	}
	public void setRefNITEmpresa(String refNITEmpresa) {
		this.refNITEmpresa = refNITEmpresa;
	}
	public String getRefNombreEmpresa() {
		return refNombreEmpresa;
	}
	public void setRefNombreEmpresa(String refNombreEmpresa) {
		this.refNombreEmpresa = refNombreEmpresa;
	}
	public String getRefDireccionEmpresa() {
		return refDireccionEmpresa;
	}
	public void setRefDireccionEmpresa(String refDireccionEmpresa) {
		this.refDireccionEmpresa = refDireccionEmpresa;
	}
	public String getRefNombreContacto() {
		return refNombreContacto;
	}
	public void setRefNombreContacto(String refNombreContacto) {
		this.refNombreContacto = refNombreContacto;
	}
	public String getRefContIdentificacionContacto() {
		return refContIdentificacionContacto;
	}
	public void setRefContIdentificacionContacto(String refContIdentificacionContacto) {
		this.refContIdentificacionContacto = refContIdentificacionContacto;
	}
	public String getRefContTelefonoContacto() {
		return refContTelefonoContacto;
	}
	public void setRefContTelefonoContacto(String refContTelefonoContacto) {
		this.refContTelefonoContacto = refContTelefonoContacto;
	}
	public String getRefIdentificacionFamiliar() {
		return refIdentificacionFamiliar;
	}
	public void setRefIdentificacionFamiliar(String refIdentificacionFamiliar) {
		this.refIdentificacionFamiliar = refIdentificacionFamiliar;
	}
	public String getRefNombreFamiliar() {
		return refNombreFamiliar;
	}
	public void setRefNombreFamiliar(String refNombreFamiliar) {
		this.refNombreFamiliar = refNombreFamiliar;
	}
	public String getRefParentescoFamiliar() {
		return refParentescoFamiliar;
	}
	public void setRefParentescoFamiliar(String refParentescoFamiliar) {
		this.refParentescoFamiliar = refParentescoFamiliar;
	}
	
	@Override
	public String toString() {
		return "FormController [tipoIdentificacion=" + tipoIdentificacion + ", númeroIdentificacion="
				+ numeroIdentificacion + ", nombres=" + nombres + ", apellidos=" + apellidos + ", cantidadSolicitada="
				+ cantidadSolicitada + ", tipoMoneda=" + tipoMoneda + ", plazoMeses=" + plazoMeses + ", refNITEmpresa="
				+ refNITEmpresa + ", refNombreEmpresa=" + refNombreEmpresa + ", refDireccionEmpresa="
				+ refDireccionEmpresa + ", refNombreContacto=" + refNombreContacto + ", refContIdentificacionContacto="
				+ refContIdentificacionContacto + ", refContTelefonoContacto=" + refContTelefonoContacto
				+ ", refIdentificacionFamiliar=" + refIdentificacionFamiliar + ", refNombreFamiliar="
				+ refNombreFamiliar + ", refParentescoFamiliar=" + refParentescoFamiliar + "]";
	}
	
	public void sendInfo(){
		
		FacesMessage message = null;
		String recievedMessage = "";
		
		if(!tipoIdentificacion.isEmpty() &&
			!numeroIdentificacion.isEmpty() &&
			!nombres.isEmpty() &&
			!apellidos.isEmpty() &&
			!cantidadSolicitada.isEmpty() &&
			!tipoMoneda.isEmpty() &&
			!plazoMeses.isEmpty() &&
			!refNITEmpresa.isEmpty() &&
			!refNombreEmpresa.isEmpty() &&
			!refDireccionEmpresa.isEmpty() &&
			!refNombreContacto.isEmpty() &&
			!refContIdentificacionContacto.isEmpty() &&
			!refContTelefonoContacto.isEmpty() &&
			!refIdentificacionFamiliar.isEmpty() &&
			!refNombreFamiliar.isEmpty() &&
			!refParentescoFamiliar.isEmpty()){
			
			try {

				URL url = new URL("http://localhost:8081/initialRequest");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Error : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				while ((output = br.readLine()) != null) {
					recievedMessage += output;
					System.out.println(output);
				}

				conn.disconnect();
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, recievedMessage, toString());
				RequestContext.getCurrentInstance().showMessageInDialog(message);

			  } catch (MalformedURLException e) {
				  message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar", e.toString());
				  RequestContext.getCurrentInstance().showMessageInDialog(message);
				  e.printStackTrace();
				  
			  } catch (IOException e) {
				  message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar", e.toString());
				  RequestContext.getCurrentInstance().showMessageInDialog(message);
				  e.printStackTrace();
			  }
			
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar", "Hay campos vacíos");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}
}
