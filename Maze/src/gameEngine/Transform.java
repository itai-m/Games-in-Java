package gameEngine;

public class Transform {

	private Vector3f translation;
	
	///Constructor
	public Transform(){
		translation = new Vector3f(0, 0, 0);
	}

	///Get the translation
	public Vector3f getTranslation() {
		return translation;
	}

	///Set the translation vector
	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	///Set the translation floats
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}
}
