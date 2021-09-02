import java.util.Scanner;
public class Principal {
	
	public static void main (String [] args) {
		Scanner in = new Scanner(System.in);
		
		BateriaDePruebas b = new BateriaDePruebas();
		int opcion;
		int ancho = 0;
		int alto = 0;
		int cont = 0;
		// Controlamos valores no deseados
		do {
			System.out.println("******************************");
			System.out.println("*            MENU            *");
			System.out.println("******************************");
			System.out.println("1) Cargar Bateria de pruebas");
			System.out.println("2) Introducir un nuevo tablero");
			System.out.println("3) Salir");
			System.out.print("Introduce una opcion: ");
			opcion = Integer.parseInt(in.nextLine());
			
			if(opcion == 3) {
				System.exit(0);
			}
			if(cont == 0) {
				b.ejucatarBateria(3);
			}
			if(opcion == 1) {
				b.ejucatarBateria(opcion);
			}
			if(opcion == 2) {
				while(ancho <= 0 || alto <= 0) {
					
					System.out.println("\nIntroduce las dimasiones de la matriz");
					System.out.print("Ancho: ");
					ancho = Integer.parseInt(in.nextLine());
					b.setAncho(ancho); // Pasamos los balores para el tablero
					System.out.print("Alto: ");
					alto =  Integer.parseInt(in.nextLine());
					b.setAlto(alto);
				}
				b.ejucatarBateria(opcion);
				ancho = 0;
				alto = 0;
			}
			cont++;
		} while (opcion != 3);
		in.close();
	}
}
