package quintatentativa;

public class ThreadCorrida implements Runnable,Comparable{

	private static Integer nextNumero = 1;
	private static boolean estaAutorizado = true;
	private static Integer linhaDeChegada = 10;
	private static boolean parar;
	private final Integer numero;
	private Integer pontos = 0;
	private boolean correu = false;

	public ThreadCorrida(){
		ThreadCorrida.parar = false;
		this.numero = nextNumero++;

		new Thread(this).start();//inicia a thread no próprio construtor.
	}

	@Override
	public void run() {
		while(!parar){
			//System.out.println("Thread " + numero + " ativa");
			this.correr();
		}
		//System.out.println("Thread terminada");
	}

	public synchronized void setCorreu(boolean correu) {
		this.correu = correu;
	}

	private synchronized void correr(){
		synchronized(linhaDeChegada) {
			//System.out.println("EstaAutorizado = " + estaAutorizado);
			if(!correu && estaAutorizado) {
				this.pontos += ThreadCorrida.linhaDeChegada--;
				System.out.println("numero: " + numero + " pontos: " + pontos);
				this.correu = true;
				/*if(linhaDeChegada == 0) {
				parar = true;
			}*/
				notify();//notifica main que ele já correu.
			}
		}
	}

	public synchronized void esperaCorrer(){
		while(!correu){
			try {
				wait();//main dorme enquanto espera os corredores
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//this.correu = false;
	}

	public  static void encerrarCompeticao(){
		ThreadCorrida.parar = true;
	}


	@Override
	public int compareTo(Comparable other) {
		ThreadCorrida p = (ThreadCorrida) other;
		if (this.pontos < p.pontos || (this.numero > p.numero && this.pontos == p.pontos)) return -1;
		if (this.pontos == p.pontos) return 0;
		return 1;
	}

	@Override
	public Boolean equals(Comparable other) {
		ThreadCorrida c = (ThreadCorrida) other;
		return this.pontos  == c.pontos;
	}
	


	public static void main(String[] args) {
		
		final Integer NUM_CORRIDAS = 10;//número de corridas
		
		ThreadCorrida.estaAutorizado = false;
		//declaração das 10 threads:
		ThreadCorrida c1 = new ThreadCorrida();
		/*c1.esperaCorrer();
		c1.correu = false;
		c1.esperaCorrer();
		c1.correu = false;*/
		//ThreadCorrida.linhaDeChegada = 10;
		ThreadCorrida c2 =  new ThreadCorrida();
		ThreadCorrida c3 = new ThreadCorrida();
		ThreadCorrida c4 = new ThreadCorrida();
		ThreadCorrida c5 = new ThreadCorrida();
		ThreadCorrida c6 = new ThreadCorrida();
		ThreadCorrida c7 = new ThreadCorrida();
		ThreadCorrida c8 = new ThreadCorrida();
		ThreadCorrida c9 = new ThreadCorrida();
		ThreadCorrida c10 =  new ThreadCorrida();

		for(int i = 0;i < NUM_CORRIDAS;i++) {
			System.out.println("Corrida" + (i+1));
			ThreadCorrida.estaAutorizado = true;
			c1.esperaCorrer();
			c2.esperaCorrer();
			c3.esperaCorrer();
			c4.esperaCorrer();
			c5.esperaCorrer();
			c6.esperaCorrer();
			c7.esperaCorrer();
			c8.esperaCorrer();
			c9.esperaCorrer();
			c10.esperaCorrer();
			ThreadCorrida.estaAutorizado = false;
			System.out.println("Esta Autorizado = " + ThreadCorrida.estaAutorizado);
			ThreadCorrida.linhaDeChegada = 10;
			c1.correu = false;
			c2.correu = false;
			c2.correu = false;
			c3.correu = false;
			c4.correu = false;
			c5.correu = false;
			c6.correu = false;
			c7.correu = false;
			c8.correu = false;
			c9.correu = false;
			c10.correu = false;
		}
		ThreadCorrida.parar = true;//manda parar depois das corridas.
		
		ThreadCorrida[] competidores = new ThreadCorrida[10];//cria um vetor para colocar as threads e organizar
		competidores[0] = c1;
		competidores[1] = c2;
		competidores[2] = c3;
		competidores[3] = c4;
		competidores[4] = c5;
		competidores[5] = c6;
		competidores[6] = c7;
		competidores[7] = c8;
		competidores[8] = c9;
		competidores[9] = c10;
		
		
		//testando quando empata se ordena certo pelo número do competidor em ordem crescente.
		/*competidores[0].pontos = 2;
		competidores[1].pontos = 2 ;
		competidores[2].pontos = 2;
		competidores[3].pontos = 2;
		competidores[4].pontos = 2;
		competidores[5].pontos = 2;
		competidores[6].pontos = 2;
		competidores[7].pontos = 2;
		competidores[8].pontos = 2;
		competidores[9].pontos =2;*/
		
		
		
		Collections.sort(competidores,10);//organiza as threads
		System.out.println();
		System.out.println("RESULTADOS: ");
		
		System.out.println("Campeão 1ºlugar: "  + competidores[0].numero + "; pontos: " + competidores[0].pontos);
		for(int i = 1; i < 10; i++) {
			System.out.println((i+1) + "ºlugar: "  + competidores[i].numero + "; pontos: " + competidores[i].pontos);
		}
		
		System.out.println("Número de threads ativas ainda: ");
		System.out.println(Thread.activeCount());
	}



}