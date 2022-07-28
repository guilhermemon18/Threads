package quartatentativa;
import java.util.concurrent.Semaphore;

import exercicios.Comparable;

public class ThreadCorrida extends Thread implements Comparable {

	private static final int MAX_AVAILABLE = 10000;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
	private static boolean corridaEmAndamento = true;
	private static Integer nextNumero = 1;
	private static Integer corridas;//numero de corridas
	private static Integer linhaDeChegada = 10;
	private Integer numero;
	private Integer pontos;
	private boolean correu;



	public static boolean isCorridaEmAndamento() {
		return corridaEmAndamento;
	}

	public static void setCorridaEmAndamento(boolean corridaEmAndamento) {
		ThreadCorrida.corridaEmAndamento = corridaEmAndamento;
	}

	public synchronized void setCorreu(boolean correu) {
		this.correu = correu;
		this.pontos = 0;
	}

	public static void setNextNumero(Integer nextNumero) {
		ThreadCorrida.nextNumero = nextNumero;
	}

	public synchronized static void setLinhaDeChegada(Integer linhaDeChegada) {
		ThreadCorrida.linhaDeChegada = linhaDeChegada;
	}

	public ThreadCorrida() {
		numero = ThreadCorrida.nextNumero++;

	}

	@Override
	public void run() {
			while(corridaEmAndamento) {
				if(!correu) {
					try {
						available.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Chegou na linha de chegada! " + numero);
					pontos += ThreadCorrida.linhaDeChegada--;
					correu = true;
					notify();

					available.release();

				}
			}
		
	}


	public synchronized void esperaCorrer() {
		while(!this.correu) {
			System.out.println("Esperando correr!!!");
			System.out.println("Esperando correr!!\n");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized static Integer getCorridas() {
		return corridas;
	}

	public synchronized static void setCorridas(Integer corridas) {
		ThreadCorrida.corridas = corridas;
	}

	@Override
	public int compareTo(Comparable other) {
		ThreadCorrida p = (ThreadCorrida) other;
		if (this.pontos < p.pontos) return -1;
		if (this.pontos == p.pontos) return 0;
		return 1;
	}

	@Override
	public Boolean equals(Comparable other) {
		ThreadCorrida c = (ThreadCorrida) other;
		return this.numero  == c.numero;
	}

	public boolean isCorreu() {
		return correu;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getPontos() {
		return pontos;
	}


}
