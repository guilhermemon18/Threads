package quartatentativa;

import exercicios.Collections;

public class Corrida {
	private ThreadCorrida[] participantes;
	private Integer corridas;
	
	public Corrida(Integer m) {
		participantes = new ThreadCorrida[10];
		ThreadCorrida.setCorridas(m);
		for(int j = 0; j < 10; j++) {
			participantes[j] = new ThreadCorrida();
			participantes[j].start();
		}
		corridas = m;
	}
	
	
	private void showGanhadores() {
		ThreadCorrida[] aux = getGanhadores();
		for(int i = 0;i < aux.length; i++) {
			System.out.println((i+1) + "º lugar: " + aux[i].getNumero() + " pontos: " + aux[i].getPontos());
		}
	}
	
	
	public ThreadCorrida[] getGanhadores() {
		ThreadCorrida [] aux = new ThreadCorrida[3];//3 ganhadores.
		Collections.sort(participantes,10);
		aux[0] = participantes[0];
		aux[1] = participantes[1];
		aux[2] = participantes[2];
		return aux;
	}
	
	private boolean isTodoMundoJaCorreu() {
		for(int i = 0; i < 10;i++) {
			if(participantes[i].isCorreu() == false) {
				return false;
			}
		}
		return true;
	}
	
	private void reset() {
		for(int i = 0; i < 10;i++) {
			participantes[i].setCorreu(false);
		}
	}
	
	
	public void executarCorridas() {
		//for(int i = 0; i < corridas;i++) {
		while(ThreadCorrida.getCorridas() > 0) {
			System.out.println("Corrida (" + ThreadCorrida.getCorridas() + ")");
			
			for (ThreadCorrida threadCorrida : participantes) {
				threadCorrida.esperaCorrer();
			}
			
			ThreadCorrida.setLinhaDeChegada(10);
			ThreadCorrida.setCorridas(ThreadCorrida.getCorridas() - 1);
			//ThreadCorrida.setNextNumero(1);
			reset();
			//notifyAll();
		}
		ThreadCorrida.setCorridaEmAndamento(false);//acabaram as corridas.
		showGanhadores();
		
	}
	
	public static void main(String[] args) {
		Corrida c = new Corrida(2);
		c.executarCorridas();
	}
}
