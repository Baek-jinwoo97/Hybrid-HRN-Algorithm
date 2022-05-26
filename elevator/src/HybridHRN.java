

import java.util.ArrayList;

public class HybridHRN extends Elevator{

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
		 ArrayList<Double> checkTime = new ArrayList<>();
	      Double tempTime = (double) 0;
	      int test = 0;
	      int stop = -1;
	      int returnTime = 0;
	      for(Guest g: inElevator){
	            returnTime = Math.abs(g.getDestination() - g.getCurrentLayer()) * 2;
	            checkTime.add((double)((returnTime +g.getWaitTime())  / returnTime));            
	         }
	         
	      if(checkTime.size() > 1) {
	         for(int i = 0; i < checkTime.size() - 1; i ++ ) {
	            tempTime = checkTime.get(i);
	            if(tempTime < checkTime.get(i + 1)) {
	               tempTime = checkTime.get(i + 1);
	               test = i + 1;
	            }
	         }      
	      }
	      
	      for(Guest g : inElevator) {
	    	  
	            returnTime = Math.abs(g.getDestination() - g.getCurrentLayer()) * 2;
	            
	            if(checkTime.get(test) == (returnTime + g.getWaitTime()) / returnTime) {
	               destination = g.getDestination();
	            }
	            
	         }
	       
	 }
}
