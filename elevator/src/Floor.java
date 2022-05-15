import java.util.ArrayList;
//import java.util.Random;
import java.util.Scanner;

public class Floor {
    public Floor(int level){
        this.floor_level = level;
    }
    public int floor_level;
    public void add(Guest guest){
        this.guests.add(guest);
    }
    public Floor(){
        ;
    }

    public ArrayList<Guest> copy(){
        return new ArrayList<>(guests);
    }

    //현재 층에 있는 사람들
    public ArrayList<Guest> guests = new ArrayList<>();

    /**
     * 복사된 손님 리턴
     * @return 복사된 손님
     */
    public ArrayList<Guest> getCopiedGuests(){
        ArrayList<Guest> response = new ArrayList<>();
        for(Guest g : guests){
            response.add(g);
        }
        return response;
    }

    /**
     * 층을 생성
     * @return 생성된 층들
     */
    public static ArrayList<Floor> createFloor(){
        ArrayList<Floor> response = new ArrayList<>();
        for(int i=0;i<=BUILDING_HEIGHT;i++){
            response.add(new Floor(i+1));
        }
        return response;
    }

    /**
     * 엘베가 할당되지 않은 손님들 리턴
     * @return 엘베가 할당되지 않은 손님들.
     */
    public ArrayList<Guest> getWaitingGuests(){
        ArrayList<Guest> response = new ArrayList<>();
        for(Guest g : guests){
            if(g.getStatus() == GuestStatus.WAITING){
                response.add(g);
            }
        }
        return response;
    }

    public String toString(){
        StringBuilder response = new StringBuilder(floor_level + "층 : ");
        for(Guest guest : guests){
            response.append(guest);
        }
        return response.toString();
    }


    private static final int BUILDING_HEIGHT = 20;
}