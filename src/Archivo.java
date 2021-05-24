import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivo {
	public List<String> leer(String ruta)
	{
		FileReader fr = null;
		Scanner sc = null;
		List<String> datos = new ArrayList<String>();
		try {
			fr = new FileReader(ruta);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc = new Scanner(fr);
		
		while(sc.hasNext())
		{
			datos.add(sc.nextLine());
		}
		
		sc.close();
		
		return datos;
	}
}
