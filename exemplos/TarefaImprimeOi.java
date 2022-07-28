package exemplos;


class TarefaImprimeOi implements Runnable {
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println("OI");
		}
	}   
	public static void main(String[] args) {
		TarefaImprimeOi tarefa1 = new TarefaImprimeOi();
		Thread thread1 = new Thread(tarefa1);
		thread1.start();
		
	}
}

