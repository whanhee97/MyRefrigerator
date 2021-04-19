# MyRefrigerator
모바일프로그래밍 프로젝트

---

# 프로젝트 선정 이유
- 코로나로 인해 강제적으로 ‘사회적 거리두기’ 진행중
- 혼자 집에 있는 시간이 많아지고 냉장고를 들락 날락 거리며 ‘오늘 뭐먹지?’ 라는 고민을 한다
- 냉장고를 자주 열었다 닫았다 하면 전기세가 많이 나온다
- 마트에서 쇼핑을 할 때 집 냉장고에 그게 있었는지 항상 생각이 안 난다

# 개발 과정
## 냉장고
![image](https://user-images.githubusercontent.com/55576129/115226183-e44f0300-a149-11eb-8def-b806888dff07.png)
SQLiteOpenHelper를 상속 받는 DBHelper를 구현하고
핸들러를 만들어 삽입, 조회, 삭제, 수정을 수행한다.

![image](https://user-images.githubusercontent.com/55576129/115226260-00eb3b00-a14a-11eb-947e-44058d90c1a2.png)
식품 클래스는 아이디, 이름, 수량, 유통기한, 아이콘을 가진다

![image](https://user-images.githubusercontent.com/55576129/115226372-20826380-a14a-11eb-815d-1db7e9761dcb.png)
DB에서 식품을 가져와
FoodList를 만들고 
유통기한 지난것과
3일 이하로 남은 것을 분리 후
각각을 CustomAdapter로
ListView에 뿌려줌

## 레시피 추천
![image](https://user-images.githubusercontent.com/55576129/115226482-414ab900-a14a-11eb-9a67-9b2d1e9b328c.png)
![image](https://user-images.githubusercontent.com/55576129/115226589-60e1e180-a14a-11eb-8750-6b68855bc24f.png)

## 장보기 메모
![image](https://user-images.githubusercontent.com/55576129/115226638-722aee00-a14a-11eb-92b6-77975bca7882.png)

# 사용자 인터페이스 및 메뉴얼
![image](https://user-images.githubusercontent.com/55576129/115226677-7e16b000-a14a-11eb-8402-e0a917a640e6.png)
## 식품등록
![image](https://user-images.githubusercontent.com/55576129/115226740-8cfd6280-a14a-11eb-9b54-1b428b7871e1.png)
## 상세보기
![image](https://user-images.githubusercontent.com/55576129/115226794-9981bb00-a14a-11eb-8be2-0a1927ef3489.png)
## 추천
![image](https://user-images.githubusercontent.com/55576129/115226832-a43c5000-a14a-11eb-9a9e-ad4028b34be3.png)
## 장보기 메모
![image](https://user-images.githubusercontent.com/55576129/115226876-af8f7b80-a14a-11eb-99e0-9fed30296d25.png)

# 개선 방안
- 아이콘을 더 늘리던가 식품 사진을 찍어 아이콘을 대체

- 일정 시간이 되면 유통기한이 지났거나 얼마 남지 않은 
  식품에 대해 노티피케이션으로 알람기능

- 단순 유튜브 키워드로 레시피 추천을 하는 것이 아닌 
  다른 방식을 활용한 추천 방식 착안
  
# 성과 및 결론
- 수업시간에 배운 것들을 직접 써보면서 각각의 요소들이 
  어느 때 쓰이고 어떻게 동작하는지 알았다.

- 예를 들어 인텐트의 동작 방식과 액티비티, 
  서비스의 생명 주기등

- 이론과 실습만으로는 이해하기 어려운 부분도 많았는데 역시 직접 해보는게 이해가 확실히 되고 도움이 되었다.
