package EleControl;
//import OutPanel.OutPanel;
import java.util.*;

class Elevator {
 private Cabin[] cabins;
 private OutPanel[] outPanels;
 private int cabinNum;
 private LinkedList<int []> requests;

 public Elevator(int maxFloor, int cabinNum) {
     this.cabins = new Cabin[cabinNum];
     this.cabinNum = cabinNum;
     this.outPanels = new OutPanel[maxFloor];
     for (int i = 0; i < cabinNum; i++) {
         this.cabins[i] = new Cabin(maxFloor, i);
     }
     for (int i = 0; i < maxFloor; i++) {
         this.outPanels[i] = new OutPanel(i, i == (maxFloor - 1));
     }  
     this.requests = new LinkedList<int []>();      
 }
 public int getCabinNum() {
	 return this.cabinNum;
 }

 public void makeRequest(int from, int toFloor) {
     int[] request = new int [2];
     request[0] = from; request[1] = toFloor;
     this.requests.add(request);
 }

 
// public void 
 
 public static void main(String[] args) {
//     Elevator a = new Elevator(4, 5);
     
 }

 
}