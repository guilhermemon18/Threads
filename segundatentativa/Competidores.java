package segundatentativa;
import exercicios.Comparable;

public class Competidores implements Runnable, Comparable {
	private static Integer nextNumero = 1;
	private static Integer corridaAtual = 0;
	private Integer corrida;
	private static Integer linhaDeChegada = 10;
	private static boolean corridaEmAndamento = true;
	private boolean correu;
	private Integer numero;
	private Integer pontos;
	


	public Competidores() {
		this.numero = Competidores.nextNumero++;
		this.corrida = 0;
		this.pontos = 0;
		new Thread(this).start();
	}
	

	@Override
	public void run() {
		while(isCorridaEmAndamento()) {
			System.out.println("Linha de chegada: " + getLinhaDeChegada());
			if(!isCorreu()) {
				System.out.println("numero: " + numero );
				if(corrida == getCorridaAtual()) {
					setCorreu(true);
					pontos += getLinhaDeChegada();
					System.out.println("pontos: " + pontos);
					corrida++;
				}
			}
		}
		
	}
	
	
	public synchronized void esperarCorrer(){
		while(!this.correu){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.correu = false;
	}
	
	public synchronized void acordar() {
		
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public Integer getPontos() {
		return pontos;
	}


	public synchronized void setPontos(Integer pontos) {
		this.pontos = pontos;
	}


	public synchronized boolean isCorreu() {
		return correu;
	}


	public synchronized void  setCorreu(boolean correu) {
		this.correu = correu;
	}
	
	private synchronized Integer getCorridaAtual() {
		return Competidores.corridaAtual;
	}
	
	private synchronized Integer getLinhaDeChegada() {
		System.out.println("Correndo!!!!\n");
		System.out.println("linha de chegada: " + Competidores.linhaDeChegada);
		return Competidores.linhaDeChegada--;
	}
		
	public synchronized boolean isCorridaEmAndamento() {
		return Competidores.corridaEmAndamento;
	}
	public synchronized void pararCorrida() {
		Competidores.corridaEmAndamento = false;
	}
	
	public synchronized static void zerarLinhaDeChegada() {
		Competidores.linhaDeChegada = 10;
	}
	public synchronized void setCorrida(Integer corrida) {
		Competidores.corridaAtual = corrida;
	}


	public synchronized static void setCorridaEmAndamento(boolean b) {
		Competidores.corridaEmAndamento = b;
		
	}


	public synchronized static void setCorridaAtual(Integer corridaAtual) {
		Competidores.corridaAtual = corridaAtual;
	}


	@Override
	public int compareTo(Comparable other) {
		Competidores p = (Competidores) other;
		if (this.pontos < p.pontos) return -1;
		if (this.pontos == p.pontos) return 0;
		return 1;
	}


	@Override
	public Boolean equals(Comparable other) {
		Competidores c = (Competidores) other;
		return this.numero  == c.numero;
	}


	
	
	


}
