# 산타클로스 <!-- omit in toc -->
## README 작성자: 김영욱

김영욱, 안원모, 양시현, 채 원, 천진혁, 황상하 웹프로젝트 </br>
웹사이트 결과물 주소: http://15.164.192.203 </br>
  
## 목차  <!-- omit in toc -->


- [1. 역할분담](#1-역할분담)
- [2. 작성규칙](#2-작성규칙)
- [3. 오류 및 해결 방법](#3-오류-및-해결-방법)
- [4. Git 사용하는 방법](#4-git-사용하는-방법)
- [5. 알게된 점](#5-알게된-점)


## 1. 역할분담


| 역할|담당자|작업페이지|결과 확인 URL|
|---|---|---|---|
| (Front) 헤더푸터 <br> (Back) 관리자페이지, 이미지업로드   |  채  원  |headerfooter.html |  localhost:8080
| (Front) 메인 <br> (Back) 브랜치관리, 장바구니, 주문, 회원등록 <br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  상품평 등록/수정  |  김영욱(팀장) | main.html | localhost:8080 |     
| (Front) 회원가입 <br> (Back) 관리자페이지, 이미지업로드 | 황상하 | signup.html | localhost:8080/signup
| (Front) 고객센터 <br> (Back) 브랜치관리, 장바구니, 주문, <br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 상품평 등록/수정| 양시현 |  customercenter.html |   localhost:8080/customerCenter/
| (Front) 로그인 <br> (Back) 상품평 등록/수정 |  천진혁 | login.html | localhost:8080/login
| (Front) 상품조회, 주문정보, 장바구니 | 안원모| goods.html | localhost:8080/goods

백엔드개발을 담당하는 영욱, 채원, 상하, 시현은   YoungWook_Test 브랜치에 최신 병합된 내용을 각자의 브랜치에 머징하여 코드를 업데이트 한다. 그 후, 각자 목표대로 개발후 완료되면, 채원상하 팀은 먼저 코드를 서로 병합하고 영욱시현팀은 서로 코드를 병합한다. 그리고, 채원상하영욱시현 팀은 팀장(영욱)이 하나로 병합한다.

- 구현해야할 기능

 |엔티티 | 기능|
 |---|---|
 |상품| 상품설명을 상품조회 페이지에서 표시|
 |상품평,평점|등록, 조회, 수정, 삭제|
 |회원|등록, 조회(마이페이지), 수정, 삭제|
 |카트상품|등록, 조회, 수정(수량), 삭제|
 |주문정보|등록, 조회|
        
## 2. 작성규칙
</br>
css파일은 common 없이 각자 영문이름으로 작성할 것. 필요한 경우, JavaScript 파일도 마찬가지로 영문이름으로 작성할것 </br>
하이퍼링크의 경우 < a href = "#" >으로 일단  통일하기 </br>
작업 시작하기 전 깃에서 PULL을 먼저하고 작업완료후 PUSH 하기 </br> 


## 3. 오류 및 해결 방법
- "nothing to fetch" 오류 해결책 https://d-e-v.tistory.com/5
- JPA를 사용하여 엔티티 클래스를 구성할 때, 클래스 이름중에 `주문정보(Order)`가 있었다. 이를 영어로 Order 라고 명명하고 컴파일을 해보니, 다른 테이블든은 정상적으로 생성되었는데, `Order`테이블만 생성이 되질 않았다. 콘솔창에는 SQL DDL 구문에서 계속 오류가 발생했다는 메시지만 있었다. 그러나 계속 코드를 확인해보아도 엔티티의 구조가 워낙 단순하여 오히려 어디가 오류인지 알기 어려웠다. 바꿀게 없어 보였기 때문이다. 엔티티의 칼럼, 연결관계 등을 바꿔보고 마침내 Order를 다른 이름으로 바꾸어 컴파일 해보니, DB에 정상적으로 테이블이 생성되었다. 알고보니 엔티티의 이름`Order`와 SQL의 기본문법 `Order by` 예약어가 계속 충돌이 생겼던 것이다.   

<hr>

 ```java
 
public class Member {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long m_num;
	
	private String email; //이메일
	private String password; //상품명
	private String name; //이름
	private String phone; //전화번호 
	private String nickname; //닉네임

	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime m_reg_date; //가입날짜
	
	private String address; //주소
	
	
}

```

회원(Member) 엔티티를  만들때, 처음에는`m_num` 없이 `String email` 에다가 `@Id` 어노테이션을 붙여주어 PK로 설정하였다. 그런데  나중에 문제가 발생하였다. `MemberRepository` 인터페이스를 만들고, `memberRepository.deleteById(email)` 함수를 호출하려니, `deleteById(Long id)` 의 파라미터 타입이 `Long` 형이고 `email`칼럼은 `String` 타입이어서 에러가 떴다. 그래서 `m_num`컬럼을 생성하고 PK로 설정하였다. 


```java

	@Commit
	@Transactional
	@Test
	public void testDeleteMember() {
		Long m_num = 8L; //Member의 m_num
		Member member = Member.builder().m_num(m_num).build();
		System.out.println(member.getEmail());
		reviewRepository.deleteByMember(member);
		memberRepository.deleteById(m_num);
	}
	
	@Commit
	@Transactional
	@Test
	public void testDeleteByMemberEmail() {
		String email = "m13@nowon.com";
		Member member = Member.builder().email(email).build();
		
		System.out.println(member.getNickname());
		reviewRepository.deleteByMember(member); //memberRepository.findByEmail(email));
		memberRepository.deleteByEmail(email);
	}

```	

혹시 `Email`을 이용해서 임시적인 `member` 멤버객체를 만들고, 이 객체를 활용해서 삭제할 방법이 없는지 고민을 해보았으나, 결과적으로는  JUnit 테스트 결과 fail이 되었다. 내 생각에는 빌더를 통해서 임시로 만든 
멤버 객체는 id가 자동으로 할당이 되도록 설정이 되어있는데, 이미 DB에 id가 100을 넘게 저장을 미리 해두었기때문에, 101 이상의 번호가 할당이 되어있고, `m13@nowon.com` 에 해당하는 레코드가 없어서 fail이 난 것이라 이해했다. 결론은 member 엔티티의 id로는 String 보다는 숫자형 타입을 지정하는게 사용하기 좋다고 판단했다. 

<hr>

## 컨트롤러의 메서드 파라미터가 있을경우, 데이터를 넘겨주는 방법

```java
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		log.info("dto...."+dto);
		//새로 추가된 엔티티의 번호
		Long bno = boardService.register(dto);
		log.info("BNO: " +bno);
		redirectAttributes.addFlashAttribute("msg",bno);
		return "redirect:/board/list";
	}
```


html에서 꼭 쿼리스트링으로 url에 BoardDTO 객체를 넘겨줄 필요가 없다. 다른 방법이 있다.
register.html을 만들어서 form 태그와 버튼 type"=submit"을 이용하여, BoardDTO의 멤버 일부를 <input> 태그에 name 속성을 지정해주면 자동으로 dto를 보내는 것과 동일한 효과가 있다.

```java
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	
	private Long r_num; //리뷰번호
	
	private Long pnum; //상품의 pnum을 가져올 수 있습니다.
	
	private Long m_num; //회원의 m_num
	
	private String nickname;  //회원의 닉네임
	
	private String email; //회원의 이메일
	
	private int grade; //평점
	
	private String r_content; //상품에 대한 리뷰내용
	
	private LocalDateTime r_reg_date; //작성날짜
	private LocalDateTime r_mod_date; //수정날짜
}

```


```html

<form th:action="@{/ReviewRegister}" th:method="post">
	
		<div class="form-group">
			<label>pnum</label>
			<input type="text" class="form-control" name="pnum" placeholder="Enter Title">
		</div>
		
		<div class="form-group">
			<label>m_num</label>
			<input type="text" class="form-control" name="m_num" placeholder="Enter Title">
		</div>
		
		
		<div class="form-group">
			<label>nickname</label>
			<input type="text" class="form-control" name="nickname" placeholder="Enter Title">
		</div>
		
		<div class="form-group">
			<label>email</label>
			<input type="text" class="form-control" name="email" placeholder="Enter Title">
		</div>
		
		
		<div class="form-group">
			<label>grade</label>
			<input type="text" class="form-control" name="grade" placeholder="Enter Title">
		</div>
		
		
		<div class="form-group">
			<label >r_content</label>
			<textarea class="form-control" rows="5" name="r_content"></textarea>
		</div>
		
		
		<button type="submit" class="btn btn-primary">Submit</button>
		
	</form>
```

여기서 알 수 있듯이, PK인 r_num 과 r_reg_date, r_mod_date는 input 태그로 지정해주지 않았다. 그래도 정상작동한다.


<hr>

- JPA에서 엔티티 칼럼이름을 정할때, _(언더스코어 바)를 넣지마라. DB에서 테이블이 생성될때, FK 칼럼의 이름에 테이블의 이름과 언더스코어바와 PK 이름이 들어가는데,
이때, 이름이 복잡해 지면서, JPA를 컴파일 하여 SQL문을 만들때, FK를 인식을 엉터리로 해버리는 위험이 있다. 이 오류를 해결하는데 시간을 낭비했다.
EX) 프로덕트 테이블의 ID를 P_num 이라고 지었더니, DB에는 product_p_num 이런식으로 저장되고, 컴파일 시에는 'p' 를 ID로 잘못 인식한다. 


- 부트스트랩을 써서 모달창을 띄우고, Ajax 비동기 처리를 통해서, 댓글을 뿌려주는 기능을 구현하려고 하였다. 그 기능을 책을보고 프로젝트에 적용을 시도했는데, 라이브러리 내의 함수를 불러왔는데, 정의되지 않은 함수를 사용하고 있다는 에러 메시지가 떴다. 문제의 원인은, 헤드부분에서 JQuery 라이브러리를 추가하는 코드가 중복되었기 때문이다. 이럴경우, JS 라이브러리에 정의된 함수가 인식이 되지 않는다. 

- 이미지가 엑박으로 표시 되는 경우가 자주 있다.. 이럴경우 <img src = "경로"> 에서 경로부분에 ` /` 백슬래쉬를 처음부터 입력을 안해줬는지 먼저 확인해라.


## 4. Git 사용하는 방법
로컬저장소에 저장된 A 프로젝트와 유사한 B프로젝트가 원격저장소의 특정브랜치(B)에 저장되어 있을경우, 프로젝트 A, B를 병합하는 방법

1. A 로컬프로젝트 디스커넥트
2. A 로컬프로젝트로 새로 로컬 저장소 만들기
3. 로컬 저장소의 리모트에 원격저장소 연결해주기
4. B브랜치만 받아오기
5. fetch 환경 설정해주고 fetch 하기
6. A (로컬마스터) 커밋만 하기!! 푸쉬 금지
7. B브랜치와 A(로컬마스터) 브랜치 충돌 비교,수정
8. B브랜치 체크아웃
9. B브랜치에서 A를 병합해줌
10. A(로컬마스터)로 체크아웃
11. A에서 B브랜치로 병합하면 fast-forward 시행됨
12. 결과 확인후 A(마스터) 푸쉬


## 5. 알게된 점

[프론트엔드 디버깅 팁](https://subicura.com/2018/02/14/javascript-debugging.html)<br>
[버튼아이콘을 가운데 정렬하는 방법](https://klssh3126.tistory.com/32) <br>
[토글버튼 구현하기](https://homnay.tistory.com/16)
