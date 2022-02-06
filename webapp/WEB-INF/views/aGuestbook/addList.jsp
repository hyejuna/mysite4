<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp">
		</c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>

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
							<li class="last">ajax방명록</li>
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
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center">
									<button id="btnSubmit" type="submit">등록</button>
								</td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->
					<!-- </form> -->

					<div id="listArea">
						<!-- js이용해서 리스트 출력 할 곳 -->
					</div>



				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->
	
	
	
	<!-- 삭제 모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호 입력</h4>
				</div>
				
				<div class="modal-body">
					<!-- 비밀번호 입력 창-->
					비밀번호:
					<input id="modalPassword" type="password" name="password" value="">
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>

<script type="text/javascript">
	//로딩 되기 전 요청
	$(document).ready(function() {

		/* 리스트출력 */
		fetchList();

		console.log("로딩전 리스트 요청");
	});

	//저장버튼 클릭될 때
	$("#btnSubmit").on("click", function() {
		console.log("클릭");

		//입력되는 데이터 모으기.
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();

		var guestbookVo = { //객체 안에 원래 한줄로 쓰는거라서 , 씀!!!!!
			name : name,
			password : password,
			content : content
		};

		console.log(guestbookVo);

		//요청
		$.ajax({

			/* 요청 */
			url : "${pageContext.request.contextPath }/api/guestbook/write", //요청 보낼 주소		
			type : "post",
			//contentType : "application/json",
			data : guestbookVo, //여기다가 객체 써도 됨. 위에서 객체로 이미 묶어줘서 객체명만 씀.

			/* 응답 */
			//dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");
				//입력화면 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//삭제버튼 클릭할 때
	$("#listArea").on("click", ".btnDelPop", function() { //새로 생기는 애들이라 부모에게 이벤트 부여->위임
		var $this = $(this);

		console.log($this);
		$("#modalPassword").val(""); //password 입력 창 초기화(아니면 숨겼다 나타났다만 하는거라 값 계속 입력되어 있음.)
		$("#delModal").modal('show')
	});

	
	//리스트 출력
	function fetchList() {
		//ppt에서 복사해서 코드 붙여넣고(6-5(2), 20page) 고치기
		$.ajax({

			/* 요청 */
			url : "${pageContext.request.contextPath }/api/guestbook/list", //요청 보낼 주소		
			type : "post", //데이터 받을 type. 주소 안바뀌어서 get으로 할 필요 없음.
			//contentType : "application/json",
			//data : {name: "홍길동"},

			/* 응답 */
			dataType : "json",
			success : function(guestbookList) { //json -> js로 변경되어 들어옴.
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);
				//console.log(guestbookList[0].name); test

				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down"); //방명록 리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	};

	//리스트 그리기
	function render(guestbookVo, updown) {
		var str = '';
		str += '<table class="guestRead"> ';
		str += '	<colgroup> ';
		str += '		<col style="width: 10%;"> ';
		str += '		<col style="width: 40%;"> ';
		str += '		<col style="width: 40%;"> ';
		str += '		<col style="width: 10%;"> ';
		str += '	</colgroup> ';
		str += '	<tr> ';
		str += '		<td>' + guestbookVo.no + '</td> ';
		str += '		<td>' + guestbookVo.name + '</td> ';
		str += '		<td>' + guestbookVo.regDate + '</td> ';
		str += '		<td><button class="btnDelPop" type="button"'+guestbookVo.no+'>삭제</button></td> '; // 삭제버튼 여러개라 아이디 아니라 클래스 
		str += '	</tr> ';
		str += '	<tr> ';
		str += '		<td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td> ';
		str += '	</tr> ';
		str += '</table> ';

		if (updown == 'down') {
			$("#listArea").append(str); //body 영역에서 list 넣을 자리에 만든 html 넣어주기.	
		} else if (updown == 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("방향오류");
		}

	};
</script>

</html>