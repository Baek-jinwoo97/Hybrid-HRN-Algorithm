import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    /**
     * 마무리 이후 각 손님 출력
     * @param result 출력할 손님들
     */
    static void print_result(List<Guest> result){
        int end;
        int start;
        int sum = 0;
        int averageTime = 0;
        for (int q = 0; q < result.size(); q++) {
            averageTime += result.get(q).getReturnTime();
            end = result.get(q).getDestination();
            start = result.get(q).getStartLayer();
            sum += Math.abs((end - start));
            int layer_diff = Math.abs(end - start);

            System.out.printf("%d. 이름: %s 대기시간: %d초 경과시간: %d초 이동경로: %s층 -> %s층,거리: %d, 총거리: %d, Elevator:%d %n", q + 1, result.get(q).getName(),
                    result.get(q).getReturnTime(), result.get(q).getWaitTime(), result.get(q).getStartLayer(),
                    result.get(q).getDestination(), layer_diff, sum, result.get(q).getwhichElevator());

        }
    }
    /**
     * 엘베는 현재층에서 방향이 같은 사람을 데려감.
     * @param elevators 엘리베이터
     * @param floors 현재 층.
     */
    static void get_guest_current_layer(List<Elevator> elevators, List<Floor> floors){
        //각 엘베에 대하여
        for(Elevator e: elevators){
            //엘베의 현재층
            int current_layer_of_elevator = e.currentLayer;
            Floor current_floor = floors.get(current_layer_of_elevator);
            for(Guest g : current_floor.getCopiedGuests()){
                //만약 엘베랑 게스트랑 방향이 같거나 멈춰있으면
                if(g.getDirection() == e.state || e.state == State.PAUSE){
                    //태울수 있는 사람이면
                    if(e.is_guest_can_inside(g)){
                        //사람을 넣음.
                        e.inElevator.add(g);
                        //멈춰 있으면 방향을 틈.
                        if(e.state == State.PAUSE){
                            e.state = g.getDirection();
                        }
                        //현재 층에서 손님은 내림.
                        current_floor.guests.remove(g);
                        //현재 엘리베이터로 아이디 설정
                        g.setwhichElevator(e.getElevatorID());
                        //그리고 엘베는 현재 목표보다 방향이 더 멀게 설정함.
                        e.setMaxDirection();
                    }
                }
            }
        }
    }
    /**
     * 엘베 상태 출력
     * @param elevators 출력할 엘리베이터들
     */
    static void print_elevator(List<Elevator> elevators){

        for (Elevator elevator : elevators) {
            System.out.print(elevator);
        }
    }

    /**
     * 새로 들어온 geust 입력
     * @param guests guest
     * @param elapsedTime 현재 시간
     * @param elevators 넣을 엘리베이터
     * @param floors 각 층
     */
    static void add_new_guests(PriorityQueue<Guest> guests,int elapsedTime, List<Elevator> elevators,ArrayList<Floor> floors){
        //현재 층에 도착한 사람들은 floor에 넣음.
        while (guests.size() != 0 && guests.peek().start_time == elapsedTime) {
            Guest guest = guests.poll();
            floors.get(guest.getStartLayer()).add(guest);
        }
    }

    /**
     * 멈춰 있는 엘리베이터의 방향을 정함.
     * @param elevators 엘리베이터들
     * @param floors 각 층
     */
    static void set_stopped_elevator_direction(List<Elevator> elevators, List<Floor> floors){
        for(Elevator e: elevators){
            if(e.state == State.PAUSE){
                for(Floor f : floors){
                    ArrayList<Guest> waitingGuests = f.getWaitingGuests();
                    for(Guest guest : waitingGuests){
                        //멈춰있고 엘베가 할당되지 않으면 엘베 할당함.
                        if(e.state == State.PAUSE && guest.getStatus() == GuestStatus.WAITING){
                            //엘베가 갈수 있는지 체크
                            if(e.check_layer(guest.getCurrentLayer()) && e.is_guest_can_inside(guest)){
                                guest.setStatus(GuestStatus.ASSIGNED);
                                //만약 위면 위로 아래면 아래로
                                if(guest.getCurrentLayer() > e.currentLayer){
                                    e.state = State.UP;
                                }else if (guest.getCurrentLayer() < e.currentLayer){
                                    e.state = State.DOWN;
                                }
                                e.setDestination(guest.getCurrentLayer());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 엘베를 방향으로 한층 움직임
     * @param elevators 움직일 엘베들
     */
    static void move_one_floor_elavator(List<Elevator> elevators){
        for(Elevator e:elevators){
            if (e.state != State.PAUSE) {
                //한층 움직임
                e.move();
            }
        }
    }

    /**
     * 각 층을 출력함
     * @param floors 출력할 모든 층
     */
    static void print_floor(List<Floor> floors){
        boolean is_floor_print = false;
        for(Floor f : floors){
            if(f.guests.size()!=0){
                if(!is_floor_print){
                    System.out.println("+++각 층 정보+++");
                    is_floor_print = true;
                }
                System.out.println(f);
            }
        }
    }

    /**
     * 각 엘베에서 사람들 내림
     * @param elevators 엘베
     */
    static void exit_guest(List<Elevator> elevators,List<Guest> result, int time,List<Floor> floors){
        for(Elevator e: elevators){
            //엘베에 모든 사람마다 내릴수 있으면 내림 그리고 문염
            ArrayList<Guest> copied = e.getCopyOfGuests();
            for (Guest guest : e.getCopyOfGuests()) {
                //현재 층이면 문열고 내림
                if (guest.getDestination() == e.currentLayer) {
                    e.inElevator.remove(guest);
                    guest.setReturnTime(time);
                    result.add(guest);
                }
                //만약 엘베가 최대 층이면 다 내리게함.
                else if(e.is_guest_must_exit(guest)){
                    e.inElevator.remove(guest);
                    guest.setCurrentLayer(e.currentLayer);
                    guest.setStatus(GuestStatus.WAITING);
                    floors.get(e.currentLayer).add(guest);
                    int k = 0;
                }
                //만약 층이 목적지와 같고 사람이 다 내렸으면 멈춤
                if (e.destination == e.currentLayer && e.inElevator.size() == 0) {
                    e.state = State.PAUSE;
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //처음에 만들 guest에 대한 배열 우선순위는 입장 시간
        int quest_size = 5;
        int max_floor = 20;
        PriorityQueue<Guest> guests = Guest.CreateGuest(quest_size, max_floor);
        int guest_count = guests.size();

        //자신의 층에 도착한 사람들 리스트
        List<Guest> result = new ArrayList<>();

        ArrayList<Floor> floors = Floor.createFloor();

        List<Elevator> elevators = Elevator.createElevator(max_floor);
        int elapsedTime = 0;

        //각 시간마다
        while (result.size() != guest_count) {
            Thread.sleep(200);
            System.out.println("현재 시각 : " + elapsedTime + "초");
            //현재 엘리베이터 상태 출력
            print_elevator(elevators);
            //각 층에 새로운 손님 도착
            add_new_guests(guests,elapsedTime,elevators,floors);
            //엘베가 멈춰 있을 경우 다음 방향을 정함.
            set_stopped_elevator_direction(elevators, floors);
            //각 엘베는 한층 올라감.
            move_one_floor_elavator(elevators);
            //한층 올라갔으니 현재층이 목적층인 사람들 내림
            exit_guest(elevators,result,elapsedTime,floors);
            //각 엘베는 각 층에서 손님을 받음.
            get_guest_current_layer(elevators, floors);
            //엘리베이터는 현재층 확인 or 문열기 or 이동 or 내림 or 문닫기
            elapsedTime++;

            print_floor(floors);
        }
        //마무리 됬으면 출력
        print_result(result);
    }
}
