package exercicios;

public class Corrida extends Thread{
	
	private Corredor[] corredores;
	private int n;
	private Integer numCorridas;
	
	public Corrida(int n,int numCorridas) {
		corredores = new Corredor[n];
		for(int i = 0; i < n;i++) {
			corredores[i] = new Corredor();
		}
			   
		for(int j = 0;j < n;j++) {
			corredores[j].start();
			System.out.println("Entrou\n");
		}
		this.numCorridas = numCorridas;
		this.n = n;
	}
	
	
	
	public Corredor[] getGanhadores() {
		Corredor [] aux = new Corredor[3];//3 ganhadores.
		Collections.sort(corredores,n);
		aux[0] = corredores[0];
		aux[1] = corredores[1];
		aux[2] = corredores[2];
		return aux;
	}
	
	private boolean isTodoMundoJaCorreu() {
		for(int i = 0; i < n;i++) {
			if(corredores[i].isJacorreu() == false) {
				return false;
			}
		}
		return true;
	}
	
	private synchronized  void setTodoMundo() {
		for(int i = 0; i < n;i++) {
			corredores[i].setJacorreu(false);
		}
	}
	
	private void showGanhadores() {
		Corredor[] aux = getGanhadores();
		for(int i = 0;i < aux.length; i++) {
			System.out.println((i+1) + "º lugar: " + aux[i].getNumero() + " pontos: " + aux[i].getPontos());
		}
	}
	
	@Override
	public void run() {
		Corredor.setCorridaEmAndamento(true);
		Corredor.setLinhaChegada(10);
		for(int i = 0; i < numCorridas;i++) {
			
			System.out.println("n = " + n);
			/*for(int j = 0;j < n;j++) {
				try {
					corredores[j].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("deu bo");
					e.printStackTrace();
				}
				System.out.println("Entrou\n");
			}*/
			while(isTodoMundoJaCorreu() == false) {
				//ainda tem thread correndo, não faz nada então.
			}
			//Corredor.setCorridaEmAndamento(false);//acabou a corrida
			Corredor.setLinhaChegada(10);//linha de chegada fica com a maior pontuaçao possivel novamente
			setTodoMundo();
			//Corredor.setCorridaEmAndamento(true);
		}
		Corredor.setCorridaEmAndamento(false);//acabou a corrida
		showGanhadores();
	}



	public static void main(String[] args) {
		 Corrida teste = new  Corrida(3,3);
		 teste.start();
		 
	}

}
