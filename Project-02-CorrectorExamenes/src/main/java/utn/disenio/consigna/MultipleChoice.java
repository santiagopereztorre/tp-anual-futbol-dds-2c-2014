package utn.disenio.consigna;

import java.util.ArrayList;

public class MultipleChoice implements Consigna {

	private Integer peso;
	private Integer respuestaCorrecta;
	private ArrayList<String> opciones;
	private Integer respuestaAlumno;
	
	public MultipleChoice(ArrayList<String> unasOpciones, Integer unaRespuestaCorrecta, Integer unaRespuestaAlumno, Integer unPeso){
		this.peso = unPeso;
		this.respuestaCorrecta = unaRespuestaCorrecta;
		this.respuestaAlumno = unaRespuestaAlumno;
		this.opciones = unasOpciones;

	}

	@Override
	public boolean esCorrectaLaRta() {
		
		return (this.respuestaAlumno == this.respuestaCorrecta);
	}
	
	@Override
	public Integer getPesoFinal() 
	{
		return (esCorrectaLaRta())?this.peso:0;
	}

}
