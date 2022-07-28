package exercicios;

public class Soma implements Runnable{
	private int linha;
	private Integer soma;
	private Integer []vetor;
	private Integer n;
	
	public Soma(Integer []vetor,Integer n,int linha) {
		setSoma(0);
		this.vetor = vetor;
		this.n = n;
		this.linha = linha;
	}
	
	
	
	@Override
	public void run() {
		soma = 0;
		for(int i = 0; i < n; i++) {
			soma += vetor[i];
			
			System.out.println("somando linha: " + linha);
		}
	}



	public Integer getSoma() {
		return soma;
	}



	private void setSoma(Integer soma) {
		this.soma = soma;
	}

}
