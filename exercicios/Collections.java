package exercicios;

public class Collections {

	public static void sort ( Comparable[] v, int n ) {
		
		Comparable aux;
		for ( int i=0; i<n; i++) {
			for ( int j=i+1; j<n; j++ ) {				
				if(v[i].compareTo(v[j])<0) {
					aux = v[i];
					v[i] = v[j];
					v[j] = aux;					
				}
			}
		}		
	}
	            
	
	public static Integer binarySearch (Comparable[] v, int i, int f, Comparable e) {
		int meio = (i+f)/2;
		int r = v[meio].compareTo(e);
		
		if(v[meio].equals(e)) {
			return meio;
		}
		if(i == f) {
			return null;
		}else {
			if(r > 0) {
				return binarySearch(v,i,meio-1,e);
			}else {
				return binarySearch(v,meio+1,f,e);
			}
			
		}
	}

}
