import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Equipo {
	private List<Colaborador> colaboradores;
	private int afinidad;
	private String respuestasIguales;
	private static int cantPreguntas;
	
	Equipo(LinkedList<Colaborador> colaboradores)
	{
		this.colaboradores = colaboradores;
	}
	
	public Equipo(List<String> datos) {
		cantPreguntas = Integer.valueOf(datos.get(0));
		int cantColab = Integer.valueOf(datos.get(1));
		
		this.colaboradores = new ArrayList<Colaborador>(cantColab);
		
		for (int i = 2; i < datos.size(); i++) {
			
			this.colaboradores.add(new Colaborador(datos.get(i)));
		}
	}
	
	public Equipo buscarEquipo(int nroPregunta)
	{
		if(nroPregunta > cantPreguntas)
		{
			this.calcularAfinidad(nroPregunta-1);
			this.obtenerRespuestasIguales(nroPregunta-1);			
			return this;
		}
		
		Map<Character,List<Colaborador>>
		posiblesEquipos = this.obtenerPosiblesEquipos(nroPregunta);
	
		List<Equipo> equipos = this.generarEquipos(nroPregunta, posiblesEquipos);
		
		if(equipos.isEmpty())
		{
			this.calcularAfinidad(nroPregunta-1);
			this.obtenerRespuestasIguales(nroPregunta-1);
			return this;
		}
		
		
		return this.obtenerEquipoMaxAfinidad(nroPregunta, equipos);
	}

	private void calcularAfinidad(int nroPregunta) {	
		this.afinidad = this.colaboradores.size() * (int)Math.pow(nroPregunta, 2);
	}
	
	private void obtenerRespuestasIguales(int nroPregunta)
	{
		this.respuestasIguales = this.colaboradores.get(0).getPreguntas().substring(0,nroPregunta);
	}

	@Override
	public String toString() {
		return this.afinidad + "\n" + this.respuestasIguales;
	}
	
	private Map<Character,List<Colaborador>> obtenerPosiblesEquipos(int nroPregunta)
	{
		Map<Character,List<Colaborador>> colaboradoresPorRespuesta = new HashMap<Character,List<Colaborador>>(); 
		
		Character respuesta;
		List<Colaborador> lista;
		
		for (Colaborador colaborador : this.colaboradores) {
			respuesta = colaborador.obtenerRespuesta(nroPregunta);
			
			if(colaboradoresPorRespuesta.containsKey(respuesta))
			{
				colaboradoresPorRespuesta.get(respuesta).add(colaborador);
			}
			else
			{
				lista = new LinkedList<Colaborador>();
				lista.add(colaborador);
				colaboradoresPorRespuesta.put(respuesta,lista);				
			}
		}
		
		return colaboradoresPorRespuesta;
	}
	
	private List<Equipo> generarEquipos(int nroPregunta,Map<Character,List<Colaborador>> equiposPosibles)
	{
		List<Equipo> equiposNuevos = new LinkedList<Equipo>();
		for (Iterator<List<Colaborador>> it = equiposPosibles.values().iterator(); it.hasNext();)
		{
			LinkedList<Colaborador> colaboradores = (LinkedList<Colaborador>)it.next();
			if(colaboradores.size() >= 2)
			{
				equiposNuevos.add(new Equipo(colaboradores));				
			}
		}
		
		return equiposNuevos;
	}
	
	private Equipo obtenerEquipoMaxAfinidad(int nroPregunta,List<Equipo> equipos)
	{
		Equipo 	maxAfinidad = null,
				retornado;
		
		for (Equipo equipo : equipos) {
			retornado = equipo.buscarEquipo(nroPregunta+1);
			if(maxAfinidad == null || (retornado.afinidad > maxAfinidad.afinidad) )
			{
				maxAfinidad = retornado;
			}
		}
		
		return maxAfinidad;
	}
	
}
