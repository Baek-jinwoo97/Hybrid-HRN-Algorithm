# 사용자의 효율적인 엘리베이터 탑승 대기시간을 위한 Hybrid HRN Algorithm 연구
사용자 대기시간을 단축하고자 CPU 스케줄링 기법인 HRN 알고리즘을 착안하여 엘리베이터 환경에서 사용할 수 있도록 변경하였습니다.

## 엘리베이터 시스템
* 일반적인 움직임
  * 엘리베이터의 이동 방향과 사용자의 목적 방향이 동일할 때 우선순위가 높다.
  * 엘리베이터는 현재 층에서 승하차를 할사람이 있는지 확인하고 있으면 승하차를 진행한다.
  * 사용자가 탑승하면 목적지를 설정하고 최종목적지는 엘리베이터에서 가장 먼 층으로 결정한다.
  * 진행방향이 결정되면 진행방향과 반대방향의 요청은 현재 진행하는 진행방향의 호출이 완료후 엘리베이터의 방향을 전환한다.

## 관련 연구
* FIFO(First In First Out)
  * 사용자의 요청이 들어온 순서대로 엘리베이터 이동한다.
  * 엘리베이터가 사용자 한 명만 태우고 동작한다.
* Collective Control
  * 우리의 실생활에서 자주 접하는 엘리베이터이다.
  * 사용자의 이동방향과 엘리베이터 움직임 방향에 따라 동작한다.
* Fuzzy Algorithm
  * 퍼지 추론 기법을 이용한 지능형 엘리베이터이다.
  * 근사적인 시스템 모델링 결정하는데 사용하며 각 층에서 사용자 호출이 발생할 때마다 적절한 if-then 규칙에 의해 선택한다.

## 알고리즘 구현
* Collective Control Algorithm
  * 일반적인 엘리베이터 시스템의 기본적인 알고리즘이다.
  * 요청이 도착하는 순서대로 엘리베이터를 할당한다.
* Zoning Algorithm
  * 엘리베이터가 이동하는 구역을 영구적으로 할당한다.
  * 모든 엘리베이터는 정해진 구역만을 움직인다.
* 3 - Passage Algorithm
  * 엘리베이터에 요청이 들어올 때 비용을 계산한다.
  * 비용이 가장 낮은 요청으로 엘리베이터가 이동한다.
* Hybrid-HRN Algorithm
  * CPU 스케줄링 방식인 HRN에서 착안하여 만든 알고리즘이다.
  * 서비스 실행 시간을 사용자의 도착위치와 사작위치의 차이로 정의하였다.

## 알고리즘 우선순위
* 기본 조건 : 엘리베이터의 이동 방향과 사용자의 목적지 방향이 같아야한다.
* Collective Control Algorithm
  * 엘리베이터 위치와 사용자의 현재 위치 사이의 거리가 가장 큰 경우 우선순위가 높다.
* Zoning Algorithm
  * 분할된 각 층에 대해서 엘리베이터 위치와 사용자의 현재 위치 사이의 거리가 가장 큰 경우 우선순위가 높다.
* 3 - Passage Algorithm
  * 비용 계산식을 통하여 계산 비용이 가장 낮은 경우 우선순위가 높다.
* Hybrid-HRN Algorithm
  * 비용 계산식을 통하여 계산비용이 가장 큰 경우 우선순위가 높다.

## 결과
![image](https://user-images.githubusercontent.com/81284275/168273753-10e5eb53-20b2-43dc-ba92-e9daa3d72f1f.png)
* 운행 거리와 운행 시간에 대한 결과로는 기존의 알고리즘인 Collective Control Algorithm이 우세하다.
* 하지만 엘리베이터 운행 balance를 따져 보았을 때 Hybrid-HRN Algorithm이 성능이 뛰어난 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/81284275/168274312-abdd198b-87ed-46bd-903c-0ed94a84344c.png)
* 대기 사간에 대한 결과는 두번 째로 우세한 결과를 보였다.
* 표준편차와 함께 비교해보면 대기시간에 대한 성능이 가장 좋은 것은 Hybrid-HRN Algorithm이라는 것을 확인 할 수 있다.

## Reference

|항목|세부사항|
|:------:|:---:|
|논문명(국문)|사용자의 효율적인 엘리베이터 탑승 대기시간을 위한 Hybrid HRN Algorithm 연구|
|논문명(영문)|Study on the Hybrid HRN Algorithm for Efficient Elevator Boarding Considering the User’s Waiting Time|
|제 1저자|백진우|
|제 2저자|염기훈|
|제 3저자|정성욱|
|Volume|15|
|Page|45-55|
|ISSN|2288-9302|
|Publisher|한국 정보 전자 통신 기술 학회(Korea Information Electronic Communication Technology)|
|Published|2022.02.28|

![image](https://user-images.githubusercontent.com/81284275/168276153-bc8c2369-e851-40f1-ad74-22ae998f0d94.png)
 
