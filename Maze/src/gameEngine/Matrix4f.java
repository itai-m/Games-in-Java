package gameEngine;

public class Matrix4f {

	private float [][] m;
	
	///Constructor
	public Matrix4f(){
		m = new float[4][4];
	}
	
	///make the matrix the identity matrix
	public Matrix4f initIdentity(){
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		return this;
	}

	/// multiply the matrix with another matrix
	public Matrix4f mul(Matrix4f mat){
		Matrix4f res = new Matrix4f();
		for(int i = 0; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++)
				res.set(i, j, m[i][0] * mat.get(0, j) + 
							  m[i][1] * mat.get(1, j) +
							  m[i][2] * mat.get(2, j) +
							  m[i][3] * mat.get(3, j));
		return res;
	}
	
	///get the matrix
	public float[][] getM() {
		return m;
	}
	
	///get one number in the matrix
	public float get(int x, int y){
		return m[x][y];
	}

	///set a new matrix
	public void setM(float[][] m) {
		this.m = m;
	}
	
	///set one number in the matrix
	public void set(int x, int y, float value){
		m[x][y] = value;
	}
}
