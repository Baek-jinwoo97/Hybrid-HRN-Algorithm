/*
 * main문
 * <시나리오>
 * 한 빌딩 내에서 3개의 엘리베이터가 존재
 * Elevator[] elevatorGroup은 각 Elevator 객체를 담고 있는 배열
 * 승객이 Random하게 엘리비에터를 호출할 때 한 건물에 있는 Elevator 3대가 정의된 알고리즘에 따라 움직임
 *
 * 목표 결과: 100명의 사람이 빌딩 내에서 엘리베이터를 이용할 때 가장 시간(cost)이 적게 걸리는 알고리즘을 선택하는 시뮬레이션
 *
 * 참고자료
 *
 * https://tech.kakao.com/2018/10/23/kakao-blind-recruitment-round-2/
 * 카카오 블라인드 공채 2차 오프라인 코딩 테스트 문제와 추구하는 방향이 유사(또는 거의 같음)
 *
 * 알고리즘 쓸 깃 주소
 * https://github.com/00111000/Elevator-Scheduling-Simulator
 * 상단의 깃 주소에 각각의 (HRN)빼고 알고리즘이 정의되어 있어서 해당 부분 이 코드에 옮길 것임.
 *
 * 현재 돌아가는 결과 값(로그)처럼 각각의 알고리즘 선택 시 엘리베이터 움직이는 로그와 최종 승객(Guest)이 움직인 로그를 찍을 것임.
 *
 * 요청사항: 특정 알고리즘에 따라 움직이는 엘리베이터 시뮬레이션 프로그램 구현
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Building {

   /**
    * 빌딩 높이 설정 변수
    */
   private static final int BUILDING_HEIGHT = 20;

   /**
    * 엘리베이터 갯수 설정 변수  --> 엘리베이터 갯수 2개로 변경하였습니다.
    */
   private static final int BUILDING_ELEVATOR = 2;

   /**
    * 건물 각각의 층에 대기하고있는 사람의 리스트를 저장하는 배열
    */
   public static List<Guest>[] layer = new ArrayList[BUILDING_HEIGHT];

   /**
    * 건물의 각층을 담당할 lock.
    */
   public static Lock[] lock = new Lock[BUILDING_HEIGHT];
   /*
    * 엘리베이터 클래스 저장 변수
    */
   //public static Elevator elevator = new Elevator();
   /**
    * 엘리베이 클래스 배열
    */
   public static Elevator[] elevatorGroup = new Elevator[BUILDING_ELEVATOR];
   /**
    * 결과 저장 리스트
    */
   public static List<Guest> result = new ArrayList<>();
   /**
    * 경과 시간
    */
   public static int elapsedTime;
}