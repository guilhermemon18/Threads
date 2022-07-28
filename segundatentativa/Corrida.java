package segundatentativa;

import exercicios.Collections;
import exercicios.Corredor;

public class Corrida {
	private Competidores[] corredores;
	private int n;
	private Integer numCorridas;
	
	public Corrida(int n,int numCorridas) {
		corredores = new Competidores[n];
		for(int i = 0; i < n;i++) {
			corredores[i] = new Competidores();
		}	   
		this.numCorridas = numCorridas;
		this.n = n;
	}
	
	
	
	public Competidores[] getGanhadores() {
		Competidores [] aux = new Competidores[3];//3 ganhadores.
		Collections.sort(corredores,n);
		aux[0] = corredores[0];
		aux[1] = corredores[1];
		aux[2] = corredores[2];
		return aux;
	}
	
	private boolean isTodoMundoJaCorreu() {
		for(int i = 0; i < n;i++) {
			if(corredores[i].isCorreu() == false) {
				return false;
			}
		}
		return true;
	}
	
	private  void ResetarCorredores() {
		for(int i = 0; i < n;i++) {
			corredores[i].setCorreu(false);
		}
	}
	
	private void showGanhadores() {
		Competidores[] aux = getGanhadores();
		for(int i = 0;i < aux.length; i++) {
			System.out.println((i+1) + "º lugar: " + aux[i].getNumero() + " pontos: " + aux[i].getPontos());
		}
	}
	
	private void esperaTodoMundoCorrer() {
		for(int i = 0; i < n;i++) {
			corredores[i].esperarCorrer();
		}
	}
	
	public void rodar() {
		//Corredor.setLinhaChegada(10);
		for(int i = 1; i < numCorridas;i++) {
			
			System.out.println("n = " + n);
			
			/*while(isTodoMundoJaCorreu() == false) {
				//ainda tem thread correndo, não faz nada então.
			}*/
			//esperaTodoMundoCorrer();
			Competidores.zerarLinhaDeChegada();//linha de chegada fica com a maior pontuaçao possivel novamente
			Competidores.setCorridaAtual(i);
			ResetarCorredores();
			
		}
		Competidores.setCorridaEmAndamento(false);//acabou a corrida
		showGanhadores();
	}



	public static void main(String[] args) {
		 Corrida teste = new  Corrida(3,1);
		 teste.rodar();
		 
	}
}
