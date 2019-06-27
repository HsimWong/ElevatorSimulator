package EleControl;

import java.util.Iterator;
import java.util.PriorityQueue;

/*
 * The index of floor starts from 0
 * under any circumstance, exept for
 * displaying.
 * UP == 1;
 * DOWN == 0;
 * 
 * REMEMBER TO ALTER THE DIR OF CABIN WHEN NO REQ R IN THE Q OF DIR 
 */

class Cabin {
	public static final int ENDOFARRAY = 65535;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int STOP = 0;
	public static final int INREQ = 0;
	public static final int OUTREQ = 1;
	public static IntRef rqIDcount = new IntRef();
	public synchronized static int fetchRqID() {
		int ret = Cabin.rqIDcount.value;
		Cabin.rqIDcount.value++;
		return ret;
	}
	private int maxFloor;

    private int cabinIndex;
    private int dir;
    private int curFloor; 
    private int nextFloor;
    private boolean occupied;
    private OutPanel[] outPanels;

    private CabinButton[] buttons;
    private PriorityQueue<Request> upRqs;
    private PriorityQueue<Request> downRqs;
 
    public class Request implements Comparable<Request> {
    	int destination;
    	int status;
    	int direction; 
    	int requestID;
    	
    	public Request(int floorIndex, int status, int direction, int rqIDcount) {
			this.destination = floorIndex;
			this.status = status;
			this.direction = direction;
			this.requestID = rqIDcount;
		}

		/* 2 arrays will be allocated respectively representing UP Rq
    	 * and DOWN Rq. Therefore, comparison will only occurs within 
    	 * requests with the same Rq direction
    	 */
    	public int compareTo(Request r) {
//    		if (this == null) {
//    			return -1;
//    		} else if (r == null) {
//    			return r.compareTo(this);
//    		} else {
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
    			return Math.abs(this.destination - curFloor) <= Math.abs(r.destination - curFloor) ? -1 : 1;
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
    }
    
    public Cabin(int maxFloor, int cabinIndex, OutPanel[] outPanels) {
//        this.timeLag = 5;
        this.maxFloor = maxFloor;
        this.cabinIndex = cabinIndex;
        this.dir = STOP;
        this.curFloor = 0;
        this.nextFloor = 0;
        this.buttons = new CabinButton[maxFloor];
//        this.occupied = false;        
        this.upRqs = new PriorityQueue<Request>(); 
        this.downRqs = new PriorityQueue<Request>(); 
        this.outPanels = outPanels;
        
        for (int i = 0; i < maxFloor; i++ ) {
            this.buttons[i] = new CabinButton(i);
        }
    }

    public boolean ifOccupied() {
    	return this.occupied;
    }
    
    private void goToFloor() {
    	return;
    }
    
    private void clearRqs() {
    	// Clear RQs from outside
    	Iterator<Request> iter = ((this.dir == UP) ? this.upRqs : this.downRqs).iterator();
    	while (iter.hasNext()) {
    		Request temp = iter.next();
    		if ((temp.destination == this.curFloor) && (temp.direction == this.dir)) {
    			((this.dir == UP) ? this.upRqs : this.downRqs).remove(temp);
    			buttons[temp.destination].respondTheButton();
    			this.outPanels[this.curFloor].respondRequest(this.dir == UP);
    			break;
    		}
    	}
    }
  
    public int getNextFloor() {
    	return this.nextFloor;
    }
    
    public void doRequest(Request rq) {
    	this.nextFloor = rq.destination;
    	this.occupied = true;
    	this.goToFloor();
    	this.occupied = false;
    	this.curFloor = this.nextFloor;
    	this.clearRqs();
    }
    
    public int floorDir(Request rq1) {
    	return (rq1.destination > curFloor) ? UP : ((rq1.destination == curFloor) ? STOP : DOWN);
    }
    
    public void doNext() {
    	if ((this.dir == UP && this.upRqs.isEmpty()) 
    			|| (this.dir == DOWN && this.downRqs.isEmpty())) {
    		this.dir = STOP;
//    		return;
    	}
    	if (this.dir == STOP) { // At least one of the queues is not empty
    		if (this.upRqs.isEmpty() && this.downRqs.isEmpty()) {
    			return;
    		} else if (! this.upRqs.isEmpty()) {
    			this.dir = UP;
    			this.doRequest(this.upRqs.poll());
    			return;
    		} else if (! this.downRqs.isEmpty()) {
    			this.dir = DOWN;
    			this.doRequest(this.downRqs.poll());
    			return;
    		} else {
//    			Request upR = this.upRqs.peek();
    			Request downR = this.downRqs.peek();
    			if (this.floorDir(downR) == DOWN || this.floorDir(downR) == STOP) {
    				this.dir = DOWN;
    				doNext();
    				return;
    			} else { // floorDir(downR) == UP
    				this.dir = UP;
    				doNext();
    				return;
    			}
    		} 
    	} else if (this.dir == UP) {// respective queue is not empty
    		this.doRequest(this.upRqs.poll());
    		return;
    	} else { // going down, down queue is not empty
    		this.doRequest(this.downRqs.poll());
    		return;
    	}
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

    public void respondFloor(int floorIndex) {
        this.buttons[floorIndex].respondTheButton();
        // this.outPanels[floorIndex].respondRequest();
    }
    
    public void requestFloorFromInside(int floorIndex) {
//    	if ()
    	if ((!this.buttons[floorIndex].getStatus())
    			|| !(this.curFloor == floorIndex)) {
	    	int ID = fetchRqID();
	        this.buttons[floorIndex].pressButton();
	        int rqDir = (floorIndex > this.curFloor) ? UP : DOWN;
	        PriorityQueue<Request> Q2bInserted = (floorIndex > this.curFloor) ? this.upRqs : this.downRqs;
	        Request request = new Request(floorIndex, INREQ, rqDir, ID);
	        Q2bInserted.add(request);	        
    	}
    }
    
    public void requestFromOutside(int floor, int direction) {
    	this.manageOutReq(new Request(floor, OUTREQ, direction, Cabin.fetchRqID()));
    }
    
    private void manageOutReq(Request rq) {
    	switch (rq.direction) {
    	case UP:
    		Iterator<Request> upQueueIter = this.upRqs.iterator();
    		while(upQueueIter.hasNext()) {
    			Request next = upQueueIter.next();
    			if (rq.destination == next.destination) {
    				this.upRqs.remove(next);
    				break;
    			}
    		}
    		this.upRqs.add(rq);
    		break;
    	case DOWN:
    		Iterator<Request> downQueueIter = this.downRqs.iterator();
    		while(downQueueIter.hasNext()) {
    			Request next = downQueueIter.next();
    			if (rq.destination == next.destination) {
    				this.upRqs.remove(next);
    				break;
    			}
    		}
    		this.downRqs.add(rq);
    	}
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < this.maxFloor; i++) {
            ret += buttons[i].toString();
        }
        return ret;
    }

    
    public static void main(String[] args){
//    	Cabin cb = new Cabin(6, 1);
//    	Cabin cb1 = new Cabin(6, 1);
//    	System.out.print(cb.rqIDcount);
//    	System.out.println(cb1.rqIDcount);
//    
    }
}