# 2주차 JPA

## 1단계 엔티티 매핑

### 요구사항
* QnA 서비스를 만들어가면서 JPA로 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.
* 아래의 DDL(Data Definition Language)을 보고 유추하여 엔티티 클래스와 리포지토리 클래스를 작성해 본다.
* [DDL 링크](src/main/resources/data/schema.sql)
* @DataJpaTest를 사용하여 학습 테스트를 해 본다.

### TODO!
* DDL 기반으로 엔티티 클래스 작성
* 리포지토리 클래스 작성
* DataJpaTest 사용하여 학습 테스트 진행

## 2단계 연관 관계 매핑

### 요구사항
* QnA 서비스를 만들어가면서 JPA로 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.
* 객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.

### TODO!
* Entity 연관 관계 설정
* 테스트 케이스 수정
* 깃 충돌 해결

## 3단계 질문 삭제하기 리팩터링
### 기능 요구 사항
* 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
* 로그인 사용자와 질문한 사람이 같은 경우 삭제할 수 있다.
* 답변이 없는 경우 삭제가 가능하다.
* 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
* 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
* 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
* 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### 기능 목록
* [x] 질문 삭제 시, Question 데이터의 deleted -> true로 변경한다.
* [x] 로그인 User id와 Question writer_id가 같은 경우 삭제, 같지 않을 경우 예외를 던진다.
* [x] Question 하위에 답변이 없는 경우 삭제 가능하다.
* [x] Question의 writer_id와 하위의 Answer들의 writer_id가 같은 경우 삭제 가능하다.
* [x] 위의 조건을 만족할 경우 Answer들 또한 삭제 처리(deleted -> true)한다.
* [x] Question의 writer_id와 Answer의 writer_id가 다른 경우 답변을 삭제할 수 없다.
* [x] 위의 과정에서 삭제될 경우 delete_history에 삭제된 정보를 추가한다.

### 1차 피드백 목록
* [x] CannotDeleteException Exception을 상속 -> RuntimeException 상속으로 변경
* [ ] 원시값, 참조값 래핑에 대한 의미있는 코드 작성
* [ ] 답변을 지울 때, answer.delete(loginUser) 시에 확인하는 로직으로 객체지향적인 코드 작성
* [ ] 양방향 연관관계에서의 여러 케이스 고려
* [ ] 변수명에 q1, a1과 같은 약어는 지양