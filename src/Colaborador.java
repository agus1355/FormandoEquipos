
public class Colaborador {
	private String preguntas;
	
	Colaborador(String preguntas)
	{
		this.preguntas = preguntas;
	}
	
	public char obtenerRespuesta(int nroRespuesta)
	{
		return preguntas.charAt(nroRespuesta-1);
	}
	
	@Override
	public String toString() {
		return this.preguntas;
	}

	public String getPreguntas() {
		return preguntas;
	}
}
