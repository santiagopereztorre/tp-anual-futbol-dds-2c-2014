package utn.dds.jugador;

import java.util.ArrayList;
import java.util.List;

import utn.dds.admin.Admin;
import utn.dds.admin.Sugerencia;
import utn.dds.calificacion.Calificacion;
import utn.dds.infraccion.Infraccion;
import utn.dds.jugador.excepciones.NoEsAmigoException;
import utn.dds.jugador.excepciones.NoJugaronJuntosException;
import utn.dds.jugador.excepciones.YaFueCalificadoException;
import utn.dds.partido.*;
import utn.dds.tipoInscripcion.TipoInscripcion;


public class Jugador {
	
	private List<Infraccion> infracciones;
	private List<Jugador> amigos;
	private List<Calificacion> calificaciones;
	private String mail;
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Jugador(){
		infracciones = new ArrayList<Infraccion>();
		calificaciones = new ArrayList<Calificacion>();
		amigos = new ArrayList<Jugador>();
	}

	public void agregarAmigo(Jugador unAmigo){
		amigos.add(unAmigo);
	}
	
	public void recibirInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
	
	public int cantidadInfracciones() {
		return infracciones.size();
	}
	
	public List<Jugador> getAmigos(){
		return amigos;
	}
	
	/**
	 * Evalua si dos jugadores participaron del mismo partido
	 * @param unJugador
	 * @param unPartido
	 * @return True cuando jugaron juntos en unPartido
	 */
	public Boolean jugueCon(Jugador unJugador, Partido unPartido)
	{
		return unPartido.jugo(this) && unPartido.jugo(unJugador);
	}
	
	/**
	 * Califica a unJugador por su participacion en unPartido
	 * @param unJugador
	 * @param unPartido
	 * @param unTexto
	 * @throws Exception
	 */
	public void calificar(Jugador unJugador, Partido unPartido, String unTexto)
	{
		if (!this.jugueCon(unJugador, unPartido)) 
			throw new NoJugaronJuntosException("Los jugadores no jugaron juntos el partido indicado");
		
		Calificacion calificacion = new Calificacion(this, unPartido, unTexto);
		
		unJugador.agregarCalificacion(calificacion);
	}
	
	public void agregarCalificacion(Calificacion calificacion)
	{
		if (this.fuiCalificado(calificacion.getCalificador(), calificacion.getPartido())) 
			throw new YaFueCalificadoException();
		
		this.calificaciones.add(calificacion);
	}
	
	public Boolean fuiCalificado(Jugador unJugador, Partido unPartido){
		return (this.calificaciones.stream().filter( x -> x.getCalificador() == unJugador && x.getPartido() == unPartido).count()) > 0; 
	}
	
	/**
	 * Sugiere amigos al administrador de sistema.
	 * 
	 */
	 
	public void sugerirAmigo(Jugador unAmigo, Partido unPartido, TipoInscripcion insc)
	{
		/* Se fija que sea un amigo suyo */
		if (!esAmigo(unAmigo))
			throw new NoEsAmigoException("El jugador no es amigo");
		
		Admin.getInstancia().sugerir(new Sugerencia(unAmigo, unPartido, insc));
			
	}
	
	public Boolean esAmigo(Jugador unJugador){		// TODO HACER UN TEST?
		
		return amigos.contains(unJugador);
	}

	public int cantidadCalificaciones() {
		return this.calificaciones.size();
	}
}
