package Functions;

public class Function {

	public Function() {
//		this.status = false;
	}
	
	public int realFloor(int storageFloor, int baseFloor) {
//		this.status = true;
		return storageFloor + baseFloor + ((storageFloor + baseFloor) >= 0 ? 1 : 0);
	}
	
	public int storageFloor(int realFloor, int baseFloor) {
		return realFloor - baseFloor - (realFloor >= 0 ? 1 : 0);
	}
	
	
	public static void main(String[] args) {
		Function f = new Function();
		System.out.println(f.storageFloor(f.realFloor(4, -3), -3));
	}
}
