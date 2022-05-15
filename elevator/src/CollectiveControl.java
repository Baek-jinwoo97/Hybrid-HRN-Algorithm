public class CollectiveControl extends Elevator{

   private int guests = 0;
   /**
    * 엘리베이터 시작 층
    */
   public CollectiveControl(int Elevator_ID) {
      this.Elevator_ID = Elevator_ID;
   }

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
}