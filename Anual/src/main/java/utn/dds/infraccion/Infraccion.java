package utn.dds.infraccion;

import java.util.Date;

import utn.dds.jugador.Jugador;
import utn.dds.tipoInscripcion.TipoInscripcion;

public class Infraccion {
	
	private String motivo;
	private Date fecha;
	
	public Infraccion(String motivo){
		setMotivo(motivo);
		setFecha(new Date());
	}

	public void setFecha(Date unaFecha) {
		fecha = unaFecha;
	}

	public void setMotivo(String unMotivo) {
		motivo = unMotivo;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getMotivo(){
		return motivo;
	}
	
}
