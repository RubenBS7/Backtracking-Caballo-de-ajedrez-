import java.io.FileWriter;
import java.io.IOException;

public class BateriaDePruebas {
	private int ancho;
	private int alto;
	private int[][] bateria = { { 3, 3 }, { 4, 4 }, { 5, 5 }, { 6, 6 }, { 3, 10 }, { 3, 12 }, { 5, 6 }, { 3, 4 },
			{ 3, 5 }, { 3, 6 }, { 3, 8 }, { 4, 6 }, { 4, 3 }, { 5, 3 }, { 6, 3 }, { 8, 3 }, { 6, 4 }, { 6, 5 },
			{ 10, 3 }, { 12, 3 } };
	
	public BateriaDePruebas() {}
	
	public void ejucatarBateria(int opcion) {
		
		try {
			FileWriter fichero = new FileWriter("tableros123.txt",true);
	
			switch (opcion) {
			case 1:
				for (int i = 0; i < bateria.length; i++) {
					for (int j = 0; j < bateria[i].length; j++) {
						if (j == 0) {
							this.ancho = bateria[i][j];
						} else {
							this.alto = bateria[i][j];
						}
					}	
					// Escribimos en el fichero los casos de prueba
					System.out.println("Tablero "+ancho+" x "+alto+"\n-----------------------------------");
					Backtracking b = new Backtracking(alto, ancho);
					b.solucion();

					fichero.write("Tablero "+ancho+" x "+alto+"\n");
					fichero.write("Camino Abierto:\n"+b.convertirtablero(b.getCaminoAbierto()));
					fichero.write("Camino Cerrado:\n"+b.convertirtablero(b.getCaminoCerrado()));
					fichero.write("Tiempo camino abierto: "+b.getTiempoCaminoAbierto()+"ms\n");
					fichero.write("Tiempo camino cerrado: "+b.getTiempoCaminoCerrado()+"ms\n\n");
					System.out.println();
				}
				break;
				
			case 2:
				Backtracking b = new Backtracking(alto, ancho);
				b.solucion();

				fichero.write("Tablero "+ancho+" x "+alto+"\n");
				fichero.write("Camino Abierto:\n" + b.convertirtablero(b.getCaminoAbierto()));
				fichero.write("Camino Cerrado:\n" + b.convertirtablero(b.getCaminoCerrado()));
				fichero.write("Tiempo camino abierto: " + b.getTiempoCaminoAbierto() + "ms\n");
				fichero.write("Tiempo camino cerrado: " + b.getTiempoCaminoCerrado() + "ms\n\n");
				System.out.println();
				break;
				
			case 3:
				FileWriter fichero1 = new FileWriter("tableros123.txt");
				break;
			}
			fichero.close();
			
		} catch (IOException e) {
			System.out.println("Error al escribir en fichero");
		}
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}	
}
