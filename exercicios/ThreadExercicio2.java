package exercicios;

import java.util.Random;
import java.util.concurrent.Semaphore;

/*2) Cinco lebres disputarão uma corrida. Cada lebre pode dar um salto que varia de 1 a 3 metros de distância.
 *  A distância percorrida é de 20 metros. Na corrida, cada lebre dará um salto de comprimento aleatório (dentro do intervalo permitido) e
 *   informará quantos metros ela pulou a cada salto realizado. Em seguida, a lebre para para descansar, 
 *   ficando parada enquanto as outras lebres saltam. 
 *   Escreva um programa, utilizando threads (uma para cada lebre), 
 *   que informe a lebre vencedora e a colocação de cada uma delas no final da corrida.
 *    Informar também quantos pulos cada uma delas deu.

Obs.: Só é permitido o uso de join() para thread main.*/

public class ThreadExercicio2 extends Thread{
	private static final int MAX_AVAILABLE = 10000;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
	private static boolean parar = false;
	private static final Integer DISTANCIA_LINHA_DE_CHEGADA = 20;
	private static Integer total = 0;
	private Integer pulos;
	private boolean pulou;
	private Integer distanciaPercorrida;
	private Integer numLebre;

	public ThreadExercicio2(Integer numLebre) {
		this.pulos = 0;
		this.distanciaPercorrida = 0;
		this.numLebre = numLebre;
	}

	@Override
	public  void run() {
		try {
			available.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			System.out.println("Deu merda!!!");
			e1.printStackTrace();
		}
		while(!parar) {
			if(!pulou) {
				Random gerador = new Random();
				System.out.println("pulando lebre" + numLebre);
				total++;
				pulos++;
				distanciaPercorrida += gerador.nextInt(3) + 1;
				if(distanciaPercorrida >= DISTANCIA_LINHA_DE_CHEGADA) {
					System.out.println("lebre" + numLebre + "ganhou");
					parar = true;
				}

				if(total == 5) {
					total = 0;
					System.out.println("AS 5 lebres pularam!");
					this.notifyAll();

				}else {
					System.out.println("botando a lebre" + numLebre + " para dormir");
					try {

						wait();//sleep(200);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		available.release();

	}

	public static void main(String[] args) {
		ThreadExercicio2 lebre1 = new ThreadExercicio2(1);
		ThreadExercicio2 lebre2 = new ThreadExercicio2(2);
		ThreadExercicio2 lebre3 = new ThreadExercicio2(3);
		ThreadExercicio2 lebre4 = new ThreadExercicio2(4);
		ThreadExercicio2 lebre5 = new ThreadExercicio2(5);

		lebre1.start();
		lebre2.start();
		lebre3.start();
		lebre4.start();
		lebre5.start();

		try {
			lebre1.join();
			lebre2.join();
			lebre3.join();
			lebre4.join();
			lebre5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println("lebre1 distancia: " + lebre1.distanciaPercorrida );
		System.out.println("lebre1 pulos: " + lebre2.pulos);

	}

}
