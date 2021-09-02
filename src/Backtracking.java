
public class Backtracking {
	// Atributos
	private int ancho;
	private int alto;
	private int tablero [][];
	private int [][] caminoAbierto;
    private int [][] caminoCerrado;
    private boolean esAbierto;
    // Para calcular los tiempos de los caminos
    private long inicio;
    private long tiempoCaminoAbierto;
    private long tiempoCaminoCerrado;
    private final double n = 1000000;//para pasarlo a (ms)
	
    public Backtracking(int ancho, int alto){
		this.ancho = ancho;
        this.alto = alto;
		int matriz[][] = new int[this.ancho][this.alto];
		// Inicializamos la matriz a 0s
		for(int i = 0; i < ancho; i++){
            for(int j = 0; j < alto; j++)
                matriz[i][j] = 0;
        }
        this.tablero=matriz;
    }
    
    private int[][] copiaTablero(int tablero [][]){
        int matriz [][] = new int[tablero.length][tablero[0].length];
        for(int i = 0; i < tablero[0].length; i++){
            for(int j = 0; j < tablero.length; j++)
                matriz[j][i]=tablero[j][i];
        } 
        return matriz;
    }
    
    public void mostrarMatriz(int tablero [][]) {
		for (int i = 0; i < this.alto; i++) {
			for (int j = 0; j < this.ancho; j++) {
				//  Añadimos un 0 para que los numeros cuadren al mostrarlo
				if(tablero[j][i] < 10) {
					 System.out.print("0"+tablero[j][i]+" ");
				}
				else {
					System.out.print(tablero[j][i]+" ");
				}
			}
			System.out.println();
		}
	}
    
    // Mostrasmos por talla las posibles soluciones de los caminos
    public void solucion(){
    	this.tablero[0][0] = 1;
        int[] incrX = new int[] {-1,-2,-2,-1,1,2,2,1};
        int[] incrY = new int[] {2,1,-1,-2,-2,-1,1,2};
       
        // Iniciamos el tiempo
        inicio=System.nanoTime();
        boolean exito=buscar(0,0,tablero,incrX,incrY,2);
        
        if(!this.esAbierto) {
            System.out.println("No hay solucion para camino abierto");
        }
        if(!exito) {
            System.out.println("No hay solucion para camino cerrado");
        }else{
        	setCaminoCerrado(copiaTablero(tablero));
            mostrarMatriz(caminoCerrado);
        }
    }
    
    // Buscamos una solucion para los caminos en caso de haberlas.
    private boolean buscar(int x, int y, int tablero [][], int incrementoX [], int incrementoY [], int movimientos){
        boolean exito = false;
        
        for (int i = 0; i < 8 && !exito; i++) {
			int coordX = x + incrementoX[i];
			int coordY = y + incrementoY[i];
        
			if ((coordY >= 0 && coordY < this.alto) && (coordX >= 0 && coordX < this.ancho) ) {
				if(tablero[coordX][coordY] == 0){

                    tablero[coordX][coordY] = movimientos;
                    
                    if(completado()){
                        if(!esAbierto){
                        	
                        	this.tiempoCaminoAbierto = System.nanoTime() - this.inicio;
                        	this.esAbierto = true;
               
                            System.out.println("Encontrada solución para camino abierto!");
                            
                            // Guardamos el tablero para mostrarlo 
                            caminoAbierto = copiaTablero(tablero);
                            mostrarMatriz(caminoAbierto);
                            this.inicio = System.nanoTime();
                        }
                        if(ultimoMovimiento(coordX, coordY, tablero, incrementoX, incrementoY, movimientos+1)){
                        	exito = true;       
                            System.out.println("Encontrada solución para camino cerrado!");
                            this.tiempoCaminoCerrado = System.nanoTime() - this.inicio;
                            
                        }else{
                            exito = buscar(coordX, coordY, tablero, incrementoX, incrementoY, movimientos+1);
                            if(!exito) {
                            	 tablero[coordX][coordY] = 0;
                            } 
                        }
                    }else{
                        exito = buscar(coordX, coordY, tablero, incrementoX, incrementoY, movimientos+1);
                        if(!exito) {
                        	tablero[coordX][coordY] = 0;
                        }   
                    }
                }
            }
        }
        return exito;
    }
    
    private boolean completado(){
        boolean finalizado = true;
        
        for(int i = 0; i < this.alto && finalizado; i++){
            for(int j = 0; j < this.ancho && finalizado; j++){
            	
                if(this.tablero[j][i] == 0) {
                	finalizado = false;
                }  	
            }
        }
        return finalizado;
    }
    
    private boolean ultimoMovimiento(int x, int y, int tablero[][], int incrementoX[], int incrementoY[], int m){
        boolean cerrado = false;
        
        for(int k = 0; (k < 8) && (!cerrado); k++){
        	
            int coordX = x + incrementoX[k];
            int coordY = y + incrementoY[k];
            
            if(coordX >= 0 && coordX < this.ancho && coordY >= 0 && coordY<this.alto){
                if(coordX == 0 && coordY == 0){
                	cerrado = true;
                    tablero[coordX][coordY] = m;
                }
            }
        }
        return cerrado;
    }
     
    // Para que al imprimir cuadren los numeros de la matriz
    // y en caso de ser un tablero nulo rellenarlo de 0s.
    public String convertirtablero(int[][]tablero){
        String contenido = "";
        if(tablero != null){
            for(int i = 0; i <this.alto; i++){
                for(int j = 0; j < this.ancho; j++){
                	
                    if(tablero[j][i] < 10) {
                    	contenido = contenido+"0"+tablero[j][i]+" ";
                    }else {
                    	contenido = contenido+tablero[j][i]+" ";
                    }
                }
                contenido+="\n";
            }
        }else {
        	
        	contenido+="NO existe camino\n";
        	for(int i = 0; i <this.alto; i++){
                for(int j = 0; j < this.ancho; j++){
                	
                	System.out.print("0 ");
                	contenido = contenido+"0 ";
                }
                System.out.println("");
                contenido+="\n";
        	}
        	System.out.println("");
        }	
        return contenido;
    }
    
    // Getters y setters
    public int[][] getCaminoAbierto(){
        return caminoAbierto;
    }
    
    public int[][] getCaminoCerrado(){
        return caminoCerrado;
    }
    
    public double getTiempoCaminoAbierto(){
        return ((double)tiempoCaminoAbierto/this.n);
    }
    
    public double getTiempoCaminoCerrado(){
        return ((double)tiempoCaminoCerrado/this.n);
    }

	public void setCaminoAbierto(int[][] caminoAbierto) {
		this.caminoAbierto = caminoAbierto;
	}

	public void setCaminoCerrado(int[][] caminoCerrado) {
		this.caminoCerrado = caminoCerrado;
	}
}
