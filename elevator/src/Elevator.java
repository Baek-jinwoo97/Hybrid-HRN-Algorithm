import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elevator {
    public static final int ELEVATOR_COUNT = 2;
    /**
     * 엘리베이터에 Guest를 적재하는 역할을 하는 리스트
     */
    public List<Guest> inElevator = new ArrayList<>();
    /**
     * 현재 엘리베이터가 위치한 층
     */
    protected int currentLayer = 1;

    /**
     * 엘리베이터의 목적지
     */

    protected int destination = 0;


    /**
     * 엘리베이터 구별하기 위한 ID
     */
    public int Elevator_ID;


    /**
     * 엘리베이터의 현재 운행상태 PAUSE, GOING_UP, GOING_DOWN 3가지로 나눠짐
     */
    public State state = State.PAUSE;

    public Elevator(){

    }

    public String getBriefing() {
        if(state == State.PAUSE){
            return "멈춤";
        }else{
            return "이동중 (목적지 : " + (destination + 1) + "층) 방향 : " + getDirection();
        }
    }

    /**
     * 방향을 한글로 작성
     * @return 방향
     */
    public String getDirection(){
        if(state == State.DOWN) {
            return "하강";
        }
        if(state == State.UP){
            return "상승";
        }
        return "None";
    }

    /**
     * 엘리베이터가 갈수 있는지 체크
     * @param guestLayer 고객의 층
     * @return
     */
    public boolean check_layer(int guestLayer){
        return true;
    }

    /**
     * 한층 움직임
     */
    public void move(){
        //목적이 같으면 안움직임.
        if(this.currentLayer == this.destination){
            return;
        }
        if(this.state == State.DOWN){
            this.currentLayer--;
        }else if(this.state == State.UP){
            this.currentLayer++;
        }
        //만약 움직였는데 목적지면 엘베는 멈춤
        if(this.destination == this.currentLayer){
            this.state = State.PAUSE;
        }
    }

    /**
     * 엘베의 목표 층을 제일 먼 곳으로 설정.
     */
    public void setMaxDirection(){
        if(state == State.DOWN){
            int last = Integer.MAX_VALUE;
            for(Guest g : inElevator){
                last = Math.min(g.getDestination(), destination);
            }
            destination = last;
        }else if(state == State.UP){
            int last = 0;
            for(Guest g : inElevator){
                last = Math.max(g.getDestination(), destination);
            }
            destination = last;
        }
    }

    /**
     * 엘베 사람들의 복제
     * @return
     */
    public ArrayList<Guest> getCopyOfGuests(){
        return new ArrayList<>(inElevator);
    }

    public State getState(){
        return this.state;
    }

    public void setDestination(int destination){
        this.destination = destination;
    }

    public int getDestination(){
        return this.destination;
    }
    /**
     * 엘리베이터 왼쪽(0), 중앙(1), 오른쪽(2)
     */
    public int getElevatorID(){
        return Elevator_ID;
    }

    public static ArrayList<Elevator> createElevator(int max_floor){
        ArrayList<Elevator> response = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        // Chose algorithm
        System.out.println("\nChoose algorithm:\n");
        System.out.println("\t1 - Collective Control");
        System.out.println("\t2 - Zoning");
        System.out.println("\t3 - ThreePassage");
        System.out.println("\t4 - HRN");
        int start = 1;
        int end = (start + max_floor/ELEVATOR_COUNT) + 1;
        int algorithm_no = sc.nextInt();
        for(int i=0;i<ELEVATOR_COUNT;i++){
            start = i* (max_floor/ELEVATOR_COUNT);
            if(start<=0){
                start = 0;
            }
            end = (i+1)*(max_floor/ELEVATOR_COUNT);
            if(end == max_floor){
                end = max_floor - 1;
            }
            switch (algorithm_no){
                case 2:
                    response.add(new Zoning(i,start,end));
                    break;
                default:
                    response.add(new CollectiveControl(i));
            }
        }
        return response;
    }
    
    //태울수 있는 사람인지
    public boolean is_guest_can_inside(Guest g){
        return true;
    }



    public boolean is_guest_must_exit(Guest g){
        return false;
    }
}
