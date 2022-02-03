<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 시트는 순서대로 적용된다. bootstrap 기본 설정 -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>

<body>
	<div id="wrap">

	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			
			<c:import url="/WEB-INF/views/include/asideBoard.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<%-- <form action="${pageContext.request.contextPath}/guestbook/add" method="get"> --%>
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72"
											rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
					</form>
				
				
					<div id="listArea">
					
						<!-- list 출력할 자리 -->
						
					</div>


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->
	
	
	
<!-- 삭제 모달창 -->	
	
<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호를 입력하세요.</h4>
      </div>
      
      <div class="modal-body">
      	비밀번호:
        <input id="modalPassword" type="password" name="password" value=""><br>
         <input id="modalNo" type="text" name="no" value="">
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->	
	
<!-- /삭제 모달창 -->	

</body>


<script type="text/javascript">

	/*로딩되기 전에 요청*/
	$(document).ready(function(){
		//리스트 그리기
		fetchList();
		console.log("리스트 요청");
	
	});//ready function
		
	
	//저장 버튼이 클릭되었을 때(파라미터 방식)
	$("#btnSubmit").on("click",function(){
		console.log("클릭");
		
		//폼에 있는 데이터 모으기
		var name = $("#input-uname").val(); //이름
		var password = $("#input-pass").val(); //비밀번호
		var content = $("[name='content']").val(); //내용
		
		//객체 만들기
		var guestbookVo = {
			// 필드명: 데이터에 붙인 변수 이름
			name: name,
			password: password,
			content: content
		};
		
		//확인
		console.log(guestbookVo);
		
		//요청
		$.ajax({

			/*요청할 데이터*/
			url : "${pageContext.request.contextPath}/api/guestbook/write",
			type : "post",
			//contentType : "application/json",
			/*↓ url에 파라미터로 들어갈 데이터*/
			data : guestbookVo, // = {name: name, password: password, content: content}

			/*응답 받을 데이터*/
			dataType : "json", //받을 데이터 타입이 json입니다.(혹은 text 타입으로 받을 때도 가끔 있다.)
			success : function(guestbookVo) { //json --> js(최종적으로는 자바 스크립트로 해석한다.)
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");
				
				//입력화면 초기화 --> 방명록 등록 후 방명록 입력칸에 넣어진 데이터 지우기(이 과정이 없으면 입력한 값이 화면에 남아있음)
				$("#input-uname").val("");  // ""(빈 칸)을 value 값으로 입력
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});//ajax
	});//#btnSubmit
	
	//저장 버튼이 클릭되었을 때 - json 방식으로 요청
	$("#btnSubmit2").on("click",function(){
		console.log("json 전송 클릭");
		
		//폼에 있는 데이터 모으기
		var name = $("#input-uname").val(); //이름
		var password = $("#input-pass").val(); //비밀번호
		var content = $("[name='content']").val(); //내용
		
		//객체 만들기
		var guestbookVo = {
			// 필드명: 데이터에 붙인 변수 이름
			name: name,
			password: password,
			content: content
		};
		
		//확인
		console.log(guestbookVo);
		
		//요청
		$.ajax({

			/*요청할 데이터*/
			url : "${pageContext.request.contextPath}/api/guestbook/write2",
			type : "post",
			contentType : "application/json",
			data : JSON.stringfy(guestbookVo), //자바 스크립트 객체를 json 형식으로 변경 

			/*응답 받을 데이터*/
			dataType : "json", 
			success : function(guestbookVo) { //json --> js
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");
				
				//입력화면 초기화 --> 방명록 등록 후 방명록 입력칸에 넣어진 데이터 지우기(이 과정이 없으면 입력한 값이 화면에 남아있음)
				$("#input-uname").val("");  // ""(빈 칸)을 value 값으로 입력
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});//ajax
	});//#btnSubmit2
	
	
	/*class btnDelPop에 명령했을 경우 작동되지 않는다.
	기존 화면(html)에 있던 data가 아닌 새로 로딩된 data(ajax)이기 때문에 부모(html에 있는 데이터)에게 시켜야 한다.
	이때 각 버튼을 구별하기 위해 클릭한 해당 버튼을 인식하는 this를 추가해야한다.
	(jqueryex의 exam-15 파일 참조)*/
	
	//삭제 팝업 버튼을 눌렀을 때
	$("#listArea").on("click", ".btnDelPop", function(){
		//데이터 수집
		var $this = $(this);
		var no = $this.data("no");
		console.log(no);
		
		//초기화
		$("#modalPassword").val(""); //전에 입력했던 비밀번호 초기화(비밀번호 입력 팝업창 뜨기 전)
		$("#modalNo").val(no); //해당 게시글 번호 넣어서 전달
		$('#delModal').modal('show');
		
	});
	
	//모달창의 삭제 버튼을 클릭했을 때
	$("#modalBtnDel").on("click", function(){
		console.log("모달창 삭제 버튼 클릭");
		
		//데이터 수집
		var no = $("#modalNo").val();
		var pw = $("#modalPassword").val();
		
		var delInfoVo = {
			no: no,
			password: pw
		};
		
		console.log(delInfoVo);
		
		//ajax 요청 --> no, password
		$.ajax({
				
				url : "${pageContext.request.contextPath }/api/guestbook/remove",		
				type : "post",
				//contentType : "application/json",
				data : delInfoVo,
		
				dataType : "json",
				success : function(state){
					console.log(state);
					
					if(state==='success'){
						//해당 테이블 html 삭제
						$("#t"+no).remove();
						
						//모달창 닫기
						$('#delModal').modal('hide');
						
					}else {
						//모달창 닫기
						$('#delModal').modal('hide');
						alert("비밀번호가 틀렸습니다. 다시 시도해주세요.");
					}
				
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
		});//ajax
			
	});

	
	//리스트 출력
	function fetchList() {
		$.ajax({

			/*요청할 데이터*/
			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			/*응답 받을 데이터*/
			dataType : "json", //받을 데이터 타입이 json입니다.
			success : function(guestbookList) { //json --> js
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);
				//console.log(guestbookList[0].name);

				for (var i = 0; i < guestbookList.length; i++) {

					render(guestbookList[i],"down"); //방명록 리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});//ajax
	}//fetchList

	//리스트 그리기
	function render(guestbookVo, updown) {

		var str = '';
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no + '</td>';
		str += '		<td>' + guestbookVo.name + '</td>';
		str += '		<td>' + guestbookVo.regDate + '</td>';
		//★★★ data-(변수 이름) : 변수 이름은 소문자로 지어야 한다. 대문자를 인식하지 못한다.
		str += '		<td><button class="btnDelPop" type="button" data-no= "' + guestbookVo.no + '">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '	</tr>';
		str += '</table>';
		
		//방명록 추가시 방향 설정
		if(updown == 'down'){//뒤(아래)에 추가
			$("#listArea").append(str);
		}else if(updown == 'up'){//앞(위)에 추가
			$("#listArea").prepend(str);
		}else {
			console.log("방향오류");
		}
		
		
	};//render
		
</script>

</html>