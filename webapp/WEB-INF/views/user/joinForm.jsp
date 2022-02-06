<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="user">
					<div id="joinForm">
						<form action="${pageContext.request.contextPath}/user/join" method="get">

							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="btnIdCheck">중복체크</button>
							</div>
							
							<!-- 아이디 중복체크 결과 출력 -->
							<div id="checkIdResult">
							
							</div>

							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="password" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
							</div>

							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>

							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male"> 
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female">

							</div>

							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								<input type="checkbox" id="chk-agree" value="" name=""> 
								<label for="chk-agree">서비스 약관에 동의합니다.</label>
							</div>

							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>

						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
	/* id 중복체크 */
	$("#btnIdCheck").on("click", function(){
		console.log("아이디중복체크클릭");
		
		var userVo = {
				id: $("#input-uid").val()
		};
		console.log(userVo)
		
		$.ajax({
			
			/* 요청 */
			url : "${pageContext.request.contextPath }/user/checkId", //요청 보낼 주소		
			type : "post", 
			//contentType : "application/json",
			data : userVo, //여기다가 객체 써도 됨. 위에서 객체로 이미 묶어줘서 객체명만 씀.
			
			/* 응답 */
			dataType : "json",
			success : function(result){ 
				/*성공시 처리해야될 코드 작성*/
				if(result == 'fail') {
					$("#checkIdResult").html("<p>이미 있는 아이디입니다. 아이디를 다시 입력해주세요</p>")
				} else {
					$("#checkIdResult").html("<p>사용가능한 아이디입니다.</p>")
				}
					
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	/* 회원가입 폼 빈칸 있을 경우 전송 안함 */
	$("#btn-submit").on("click", function(){
		console.log("회원가입버튼 클릭")
		
		var id = $("#input-uid").val();
		var pw = $("#input-pass").val();
		var name = $("#input-name").val(); 
		if(id==""){
			alert("아이디를 입력해 주세요");
			return false; // 원래 하던일(submit) 멈춤.
		}
		if(pw==""){
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if(name==""){
			alert("이름을 입력해 주세요");
			return false;
		}
		
		
		return true;
		
	});
</script>
</html>