package EleControl;
import java.lang.Thread;

/*
 * The index of floor starts from 0
 * under any circumstance, exept for
 * displaying.
 * UP == 1;
 * DOWN == 0;
 */

class Cabin {
	public static final int ENDOFARRAY = 65535;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int STOP = 0;
	public static final int INREQ = 0;
	public static final int OUTREQ = 1;
	
    private int maxFloor;
    private int timeLag;
    private int cabinIndex;
    private int dir;
    private int curFloor; 
    private boolean occupied;
    private boolean[] innerRequests;
    private CabinButton[] buttons;
    private OutPanel[] outPanels;
    private int[] stopList;

    public class Request implements Comparable<Request> {
    	int destination;
    	int status;
    	int direction; 
    	int requestID;
    	
    	/* 2 arrays will be allocated respectively representing UP Rq
    	 * and DOWN Rq. Therefore, comparison will only occurs within 
    	 * requests with the same Rq direction
    	 */
    	public int compareTo(Request r) {
    		if (dir == UP) {
    			if (this.direction == UP && r.direction == UP) {
    				if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) >= 0) {
    					return this.destination >= r.destination ? 1 : -1;
    					
    				} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) < 0) {
    					return Math.abs(this.destination - curFloor) >= Math.abs(r.destination - curFloor) ? 1 : -1;
    					
    				} else if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) < 0) {
    					return 1;
    				} else {
    					return -1;
    				}  				
    			} else if ((this.direction == DOWN && r.direction == DOWN)) {
    				return this.destination > r.destination ? 1 : -1;    				
    			}
    		} else if (dir == STOP) {
    			return Math.abs(this.destination - curFloor) >= Math.abs(r.destination - curFloor) ? -1 : 1;
    		} else { // dir = DOWN;
    			if (this.direction == UP) {
    				return this.destination < r.destination ? 1 : -1;
    			} else { // this.direction = DOWN, r.direction = DOWN
    				if (this.destination <= curFloor && r.destination <= curFloor) {
    					return this.destination > r.destination ? 1 : -1;
    				} else if (this.destination > curFloor && r.destination <= curFloor) {
    					return -1;
    				} else if (this.destination <= curFloor && r.destination > curFloor) {
    					return 1;
    				} else {	// this > curFloor && r > curFloor
    					return this.destination < r.destination ? 1 : -1;
    				}   				
    			}
    		}
    		return 0;
    	}
    	
    	
//		@ Override
//		public int compareTo(Request r) {
//			if(this.status == INREQ && r.status == INREQ) {				
//				if(dir == UP) {
//					if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) >= 0) {
//						if (this.destination > r.destination) {
//							return 1;
//						} else if (this.destination < r.destination) {
//							return -1;
//						} else {
//							return 0;
//						}
//					} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) < 0) {
//						if (this.destination > r.destination) {
//							return -1;
//						} else if (this.destination < r.destination) {
//							return 1;
//						} else {
//							return 0;
//						}
//					} else if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) < 0) {
//						return 1;
//					} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) >= 0) {
//						return -1;
//					}
//				} else if (dir == DOWN) {
//					if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) >= 0) {
//						if (this.destination > r.destination) {
//							return -1;
//						} else if (this.destination < r.destination) {
//							return 1;
//						} else {
//							return 0;
//						}
//					} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) < 0) {
//						if (this.destination > r.destination) {
//							return 1;
//						} else if (this.destination < r.destination) {
//							return -1;
//						} else {
//							return 0;
//						}
//					} else if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) < 0) {
//						return -1;
//					} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) >= 0) {
//						return 1;
//					}
//				} else {
//					if (Math.abs(this.destination - curFloor) >= Math.abs(this.destination - curFloor)) {
//						return -1;
//					} else {
//						return 1;
//					}
//					
//				}
//				
//				
//			} else if (this.status == INREQ && r.status == OUTREQ) {
//				if(dir == UP) {
//					if (r.direction == UP) {
//						if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) >= 0) {
//							if (this.destination > r.destination) {
//								return 1;
//							} else if (this.destination < r.destination) {
//								return -1;
//							} else {
//								return 0;
//							}
//							
//						} else if ((this.destination - curFloor) >= 0 && (r.destination - curFloor) < 0) {
//							if (this.destination > r.destination) {
//								return -1;
//							} else if (this.destination < r.destination) {
//								return 1;
//							} else {
//								return 0;
//							}
//						} else if ((this.destination - curFloor) < 0 && (r.destination - curFloor) >= 0) {
//							return -1;
//						} else {
//							return 1;
//						}
//					} else if (r.direction == DOWN) {
//						if (this.destination >= curFloor) {
//							return -1;
//						} else {
//							
//						}
//						
//					}
//				} else if (dir == DOWN) {
//					
//				} else {
//					
//				}
//			} else if (this.status == OUTREQ && r.status == INREQ) {
//				return (r.compareTo(this));
//			}
//			return 0;
//		}
    }
    
    
    public Cabin(int maxFloor, int cabinIndex) {
        this.timeLag = 5;
        this.maxFloor = maxFloor;
        this.cabinIndex = cabinIndex;
        this.dir = 0;
        this.curFloor = 0;
        this.buttons = new CabinButton[maxFloor];
        this.occupied = false;
        this.outPanels = new OutPanel[maxFloor];
        this.stopList = new int[maxFloor * 2];
        this.stopList[0] = ENDOFARRAY;
        for (int i = 0; i < maxFloor; i++ ) {
            this.buttons[i] = new CabinButton(i);
            // this.outPanels[i] = new OutPanel(i, cabinIndex, );
        }
    }

    private void wait(int timeLag) {

    }

    public int getDir() {
        return this.dir;
    }

    public int getCurPos() {
        return this.curFloor;
    }
    
    public int getCabinIndex() {
    	return this.cabinIndex;
    }
//
//    public boolean assignCommand(int destFloorIndex) {
//        if (!(destFloorIndex == this.curFloor)) {
//            this.dir = ((destFloorIndex - this.curFloor) > 0) ? 1 : 0;
//            try {
//                this.wait(this.timeLag * (destFloorIndex - this.curFloor));
//                this.curFloor = destFloorIndex;
//                this.respondFloor(destFloorIndex);
//                this.occupied = true;
//                return true;   
//            } catch (Exception e) {
//                System.out.println(e.getStackTrace());
//                return false;
//            }
//        } else {
//            return true;
//        }
//    }
    
    
    public void updateQueue() {
    	
    }

    

    public void respondFloor(int floorIndex) {
        this.buttons[floorIndex].respondTheButton();
        // this.outPanels[floorIndex].respondRequest();
    }

    public void requestFloor(int floorIndex) {
        this.buttons[floorIndex].pressButton();
        this.innerRequests[floorIndex] = true;
    }

//    public void requestFromOut(int floorIndex, boolean dir) {
//        this.outPanels[floorIndex].makeRequest(dir);
//    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < this.maxFloor; i++) {
            ret += buttons[i].toString();
        }
        return ret;
    }

    public static void main(String[] args){
        // Cabin a = new Cabin(6);
        // a.assignCommand(4);
        // System.out.println(a.getCurPos());
        // a.assignCommand(1);
        // System.out.println(a.getCurPos());
        // System.out.println(a.getDir());
    }
}