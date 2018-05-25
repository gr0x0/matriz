package INF1016_T3;

final public class Matrix {
    private final int M;             // number of rows and columns
    private final String[] data; 	 // M-by-M array

    // create M-by-M matrix of 0's
    public Matrix(int M) {
        this.M = M;
        data = new String[M];
        for(int i=0; i<M; i++)
        {
        	data[i] = new String();
        	for(int j=0; j<M; j++)
	        	data[i].concat("0");
        }

    }

    // create matrix based on string array
    public Matrix(String[] data) {
        M = data.length;
        this.data = new String[M];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                    this.data[i] = data[i];
    }

    // copy constructor
    public Matrix(Matrix A) { this(A.data); }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                if (A.data[i].compareTo(B.data[i])!=0) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        int value = 0;
        char[] row;
        
        if (A.M != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.M; j++)
            {
            	row = C.data[i].toCharArray();
            	value = (int) row[j];
                for (int k = 0; k < A.M; k++)
                	value += (int)(A.data[i].charAt(k)) * (int)(B.data[k].charAt(j));
                row[j] = (char) value;
                C.data[i] = row.toString();
            }
        return C;
    }
    
    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) 
                System.out.printf("%9.4f ", data[i].charAt(j));
            System.out.println();
        }
    }

}