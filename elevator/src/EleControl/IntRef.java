package EleControl;

public class IntRef {
	static int value = 0;
	
	public static void main(String[] args) {
		IntRef a = new IntRef();
		IntRef b = new IntRef();
		a.value ++;
		System.out.println(b.value);
		
	}
}
