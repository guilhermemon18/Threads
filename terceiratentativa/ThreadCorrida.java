package terceiratentativa;
import java.util.concurrent.Semaphore;

import exercicios.Comparable;

public class ThreadCorrida extends Thread implements Comparable {
	
	private static final int MAX_AVAILABLE = 1000;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
	private static Integer nextNumero = 1;
	private static Integer corridas;//numero de corridas
	private static Integer linhaDeChegada = 10;
	private Integer numero;
	private Integer pontos;
	private boolean correu;
	
	
	
	public void setCorreu(boolean correu) {
		this.correu = correu;
		this.pontos = 0;
	}

	public static void setNextNumero(Integer nextNumero) {
		ThreadCorrida.nextNumero = nextNumero;
	}

	public static void setLinhaDeChegada(Integer linhaDeChegada) {
		ThreadCorrida.linhaDeChegada = linhaDeChegada;
	}

	public ThreadCorrida() {
		numero = ThreadCorrida.nextNumero++;
		
	}
	
	@Override
	public void run() {
		if(!correu) {
			try {
				available.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pontos = ThreadCorrida.linhaDeChegada--;
			correu = true;
			// dá a pontuação
			available.release();

		}
	}

	public static Integer getCorridas() {
		return corridas;
	}

	public static void setCorridas(Integer corridas) {
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
