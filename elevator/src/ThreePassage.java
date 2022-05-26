
public class ThreePassage extends Elevator{

	 @Override
	   public String toString(){
	      StringBuilder response = new StringBuilder("++++++Elevator " + getElevatorID() + "++++++ \n");
	      response.append("엘리베이터 상태 : ").append(getBriefing()).append(" \n");
	      response.append("현재 사람수 : ").append(inElevator.size()).append(" 명");
	      for(Guest g : inElevator){
	         response.append(g);
	      }
	      response.append("\n");
	      response.append("현재 위치 :  ").append(this.currentLayer + 1).append(" 층 \n");
	      return response.toString();
	   }
	 
	    public void setMaxDirection(){
	    	//최소 코스트
	        int min_cost = Integer.MAX_VALUE;
	        //최소 코스트를 가진 층
	        int min_floor = -1;
	        //각 층에 대하여
	        for (Guest g : inElevator) {
	            int up_or_down = currentLayer -1 - q > 0 ? -1 : 1;
	            int current_cost = 0;
	            //각 층에 대하여 시뮬레이션
	            for(int i=currentLayer - 1;i!=q;i = i + up_or_down){
	                //현재 층에 사람이 있으면
	                if (g.size() >= 1 && q + 1 != currentLayer) {
	                    current_cost = current_cost + cost_of_open_door() + cost_of_close_door() + cost_of_guest() * g.size();
	                    if(current_cost < min_cost){
	                        min_cost = current_cost;
	                        min_floor = i;
	                    }
	                }
	            }

	            //현재 층에 사람이 있으면
	            if (g.size() >= 1 && q + 1 != currentLayer) {
	                current_cost = current_cost + cost_of_open_door() + cost_of_close_door() + cost_of_guest() * g.size();
	                if(current_cost < min_cost){
	                    min_cost = current_cost;
	                    min_floor = q;
	                }
	            }
	        }
	        if(min_floor != -1){
	            destination = (min_floor + 1);
	            
	        }
	    }

	    private int cost_of_guest(){
	        return 1;
	    }


	    private int cost_of_open_door(){
	        return 1;
	    }

	    private int cost_of_close_door(){
	        return 1;
	    }
	        
}
