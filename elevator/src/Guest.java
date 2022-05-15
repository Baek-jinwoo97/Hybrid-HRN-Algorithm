import java.util.Comparator;
import java.util.PriorityQueue;

public class Guest {
    public int start_time;
    public static int guest_size = 0;
    private int destination;
    private int currentLayer;
    public void setCurrentLayer(int currentLayer){
        this.currentLayer = currentLayer;
    }
    private int startLayer;
    private int waitTime;
    private int weight;
    private GuestStatus status;
    public void setStatus(GuestStatus guestStatus){
        this.status = guestStatus;
    }
    private String name;
    private int returnTime;

    //엘리베이터가 할당 됬는지 여부
    private boolean is_elavator_assigned = false;
    private int whichElevator;

    public static PriorityQueue<Guest> CreateGuest(int quest_size, int max_floor){
        //들어온 시간에 따라 정렬
        PriorityQueue<Guest> response = new PriorityQueue<>(Comparator.comparingInt(o -> o.start_time));
        int seq = 0;
        for(int i=0;i<quest_size;i++){
            //현재층 1층 - 20층에서 랜덤
            int current_layer = (int)(1 + Math.random() * 19);
            //목표층 1층 - 20층에서 랜덤
            int destination = current_layer;
            //목적 층은 시작층과 달라야함.
            while(destination == current_layer){
                destination = (int)(1 + (Math.random() * 19));
            }
            //시간 1층 - 20층에서 랜덤
            double start_time = 1 + Math.random() * 19;
            response.add(new Guest(1,(int)destination,(int)current_layer,"Guest"+ ++seq,(int)start_time));
        }
        //response.add(new Guest(1,3,1,"Guest"+ ++seq,1));
        //response.add(new Guest(1,4,2,"Guest"+ ++seq,2));
        //response.add(new Guest(1,10,3,"Guest"+ ++seq,3));
        //response.add(new Guest(1,10,3,"Guest"+ ++seq,13));
        return response;
    }

    public Guest(int weight, int destination, int currentLayer, String name,int start_time) {
        this.currentLayer = currentLayer;
        this.startLayer = currentLayer;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
        this.status = GuestStatus.WAITING;
        this.start_time = start_time;
    }

    public void setwhichElevator(int whichElevator) {
        this.whichElevator = whichElevator;
    }

    public int getwhichElevator() {
        return whichElevator;
    }

    public int getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    public void setWaitTime(int time) {
        this.waitTime = time;
    }
    public int getWaitTime() {
        return waitTime;
    }

    public GuestStatus getStatus() {
        return status;
    }

    /**
     * 위로 가야하는지 아래로 가야하는지 현재 층인지
     * @return 방향
     */
    public State getDirection(){
        if(currentLayer == destination){
            return State.CURRENT;
        }else if(currentLayer > destination){
            return State.DOWN;
        }else{
            return State.UP;
        }
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public int getCurrentLayer() {
        return currentLayer;
    }

    public int getStartLayer() {
        return startLayer;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        String response = "(" + start_time + "," + (destination + 1)+ "," + status +")";
        return response;
    }
}