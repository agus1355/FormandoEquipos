import java.util.List;

public class Main {
	public static void main(String[] args) {
		Archivo a = new Archivo();
		
		List<String> datos = a.leer("in\\caso_pdf.in");
		
		Equipo equipo = new Equipo(datos);
		
		System.out.println(equipo.buscarEquipo(1));
	}
}