<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>방명록</li>
				</ul>
			</div>
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

</body>

<script type="text/javascript">

	/*로딩되기 전에 요청*/
	$(document).ready(function(){
		//리스트 그리기
		fetchList();
		console.log("리스트 요청");
	
	});//ready function
		
	
	//저장 버튼이 클릭되었을 때
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
			data : guestbookVo, // = {name: name, password: password, content: content}

			/*응답 받을 데이터*/
			dataType : "json", //받을 데이터 타입이 json입니다.
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
	});//#btnSubmit
	
	
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
		str += '		<td><a href="${pageContext.request.contextPath}/guestbook/deleteForm?no=${gbVo.no}">삭제</a></td>';
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