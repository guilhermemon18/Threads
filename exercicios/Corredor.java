package exercicios;

public class Corredor extends Thread implements Comparable{
	private static Integer nextNumero = 1;
	private Integer numero;
	private Integer pontos;	
	private static  Integer linhaChegada = 10;
	private static boolean corridaEmAndamento = false;
	private static boolean temAlguemNaLinhaDeChegada;
	private boolean jacorreu;

	public Corredor() {
		this.numero = nextNumero++;
		this.pontos = 0;
		this.jacorreu = false;
	}

	private synchronized static Integer getLinhaDeChegada() {
		
			return Corredor.linhaChegada--;
		
	}
	
	private synchronized static boolean isCorridaEmAndamento() {
		return Corredor.corridaEmAndamento;
	}
	
	@Override
	public void run() {
		while(isCorridaEmAndamento() == true) {
			//if(Corredor.temAlguemNaLinhaDeChegada == false) {
				//Corredor.temAlguemNaLinhaDeChegada = true;
				if(jacorreu == false) {
					pontos += Corredor.getLinhaDeChegada();//Corredor.linhaChegada--;
					setJacorreu(true);//jacorreu = true;
					System.out.println("Executando thread numero: " + numero);
				}
				//Corredor.temAlguemNaLinhaDeChegada = false;
			//}
		}

	}

	public static Integer getLinhaChegada() {
		return linhaChegada;
	}

	public static void setLinhaChegada(Integer linhaChegada) {
		Corredor.linhaChegada = linhaChegada;
	}

	

	public static void setCorridaEmAndamento(boolean corridaEmAndamento) {
		Corredor.corridaEmAndamento = corridaEmAndamento;
	}

	public boolean isJacorreu() {
		return jacorreu;
	}

	public synchronized void setJacorreu(boolean jacorreu) {
		this.jacorreu = jacorreu;
	}

	@Override
	public int compareTo(Comparable other) {
		Corredor p = (Corredor)other; // casting

		if (this.pontos < p.pontos) return -1;
		if (this.pontos == p.pontos) return 0;
		return 1;

	}

	@Override
	public Boolean equals(Comparable other) {
		Corredor c = (Corredor) other;
		return this.numero  == c.numero;
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

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}






}
