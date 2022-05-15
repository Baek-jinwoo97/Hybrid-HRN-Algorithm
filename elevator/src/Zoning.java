public class Zoning extends Elevator{
    public Zoning(int Elevator_ID, int start, int end){
        this.currentLayer = start;
        this.Elevator_ID = Elevator_ID;
        if(start <= 0){
            start = 0;
        }
        this.start = start;
        this.end = end;
    }

    //시작과 끝의 zon
    int start = 0;
    int end = 0;

    public String toString(){
        StringBuilder response = new StringBuilder("++++++Elevator " + getElevatorID() + "++++++ " +(start + 1) + "-" + (end + 1) +  "\n");
        response.append("엘리베이터 상태 : ").append(getBriefing()).append(" \n");
        response.append("현재 사람수 : ").append(inElevator.size()).append(" 명");
        for(Guest g : inElevator){
            response.append(g);
        }
        response.append("\n");
        response.append("현재 위치 :  ").append(this.currentLayer + 1).append(" 층 \n");
        return response.toString();
    }



    /**
     * 엘리베이터가 갈수 있는지 체크
     * @param guestLayer 고객의 층
     * @return
     */
    public boolean check_layer(int guestLayer){
        return guestLayer >= start && guestLayer <= end;
    }


    //태울수 있는 사람인지
    public boolean is_guest_can_inside(Guest g){
        //그사람이 시작층에 있으며, 아래로 내려가면 못받음.
        if(start == g.getCurrentLayer() &&g.getDirection() == State.DOWN){
            return false;
        }
        //그사람이 끝 층에 있으며 위로 올라가면 못받음.
        if(end == g.getCurrentLayer() &&g.getDirection() == State.UP){
            return false;
        }
        return true;
    }

    public boolean is_guest_must_exit(Guest g){
        //현재층이 마지막 층이고 더 내려가고 싶으면
        if(currentLayer == start && g.getDestination() < start){
            return true;
        }
        //현재층이 최대 층이고 더 올라가고 싶으면
        if(currentLayer ==  end && g.getDestination() > end){
            return true;
        }
        return false;
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
            destination = Math.max(start, last);
        }else if(state == State.UP){
            int last = 0;
            for(Guest g : inElevator){
                last = Math.max(g.getDestination(), destination);
            }
            destination = Math.min(end, last);
        }
    }
}