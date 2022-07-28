package exercicios;

import java.util.Random;

public class questao1 {
	
	
	public static void main(String[] args) {
		final Integer M = 100;//número de linhas e colunasd da matriz
		Random gerador = new Random();//gerador de números randomicos para  os valores da matriz
		Integer [][] matriz = new Integer[M][M];
		for(int i =0; i < M;i++) {
			for(int j = 0; j < M;j++) {
				matriz[i][j] = gerador.nextInt(50);
			}
		}
		System.out.println("Matriz: elementos: ");//imprimindo a matriz
		
		for(int i =0; i < M;i++) {
			for(int j = 0; j < M;j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		
		//Threads:
		
		Soma[] tarefasSoma = new Soma[M];
		Thread[] aux = new Thread[M];
		Integer somaFinal = 0;
		//somando por threads
		for(int i = 0; i < M;i++) {
				tarefasSoma[i] = new Soma(matriz[i],M,i);
				aux[i] = new Thread(tarefasSoma[i]);
				aux[i].start();
				/*try {
					aux[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				somaFinal += tarefasSoma[i].getSoma();*/
				//System.out.println("soma atual: " + tarefasSoma[i].getSoma());
		}
		
		for (Thread thread : aux) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Todas as threads terminaram!!\n");
		for(int i = 0; i < M;i++) {
			somaFinal += tarefasSoma[i].getSoma();
		}
		
		//somando da maneira convencional
		Integer somaFinal2 = 0;
		for(int i = 0; i < M;i++) {
			for(int j =0;j < M;j++) {
				somaFinal2 += matriz[i][j];
				//System.out.println("soma atual: " + atual.getSoma());
			}
		}
		
		
		System.out.println("Soma final thread: " + somaFinal);
		System.out.println("Soma final sem thread: " + somaFinal2);
		
	}
}
