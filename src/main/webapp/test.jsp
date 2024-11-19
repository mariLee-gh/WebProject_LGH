<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Elements - Editorial by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">
	<%
	/*session 영역에 해당 속성값이 있는지 확인한다.
	즉, session영역에 저장된 속성이 없다면 '로그아웃' 상태이므로 로그인 폼을 웹브라우저에 출력해야한다.*/
	if(session.getAttribute("UserId")==null){
	
	%>
	
				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="main.jsp" class="logo"><strong>Editorial</strong> by HTML5 UP</a>
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
								</header>
		

								<h3>로그인을 하세요.</h3>
								
											<script>
											/*로그인 폼의 입력값을 서버로 전송하기전에 검증하기 위해 정의한 함수.
											입력값이 빈값인지 확인하여 경고창을 띄워준다.*/
											function validateForm(form){
												/* 매개변수로 전달된 <form>태그의 DOM을 통해 하위태그인 <input>에 접근할 수 있다.
												접근시에는 name속성값을 사용하고, value는 입력된 값을 가리키게된다.*/
												if (!form.username.value){
													//입력된 값이 없다면 경고장을 띄우고..
													alert("아이디를 입력하세요.");
													//입력을 위해 포커스를 이동하고..
													form.username.focus();
													//submit 이벤트 핸들러 쪽으로 false를 반환한다.
													return false;
													//그러면 서버로의 전송은 취소(중단)된다.
												}
												/*빈값에 대한 체크는 !(부정연산자)의 아래의 방식 2가지를 모두 사용할 수 있다.  */
												if(form.password.value ==""){
													alert("패스워드를 입력하세요.");
													form.user_pw.focus();
													return false;
												}
											}
											</script>
													<form action = "LoginProcess.jsp" name="loginFrm" method="post" onsubmit = "return validateForm(this);">
														<div class="row gtr-uniform">

														        <div class="col-6 col-12-xsmall">
														            <label for="username">Username(아이디)</label>
														            <input type="text" name="username" id="username" value="" placeholder="Username(아이디)" />
														        </div>
														        <div class="col-6 col-12-xsmall">
														            <label for="password">Password(비밀번호)</label>
														            <input type="password" name="password" id="password" value="" placeholder="Password(비밀번호)" />
														        </div>

														
															<!-- Break -->
															<div class="col-12">
															    <ul class="actions" style="display: flex; justify-content: center; list-style-type: none; padding: 0;">
															        <li><input type="submit" value="로그인" class="primary" /></li>
															        <li><input type="reset" value="비밀번호 찾기" /></li>
															    </ul>
															</div>
														</div>
													</form>
												</div>
											</div>
	<%
	
	} else {//로그인된 상태
	%>
		<h>test111111</h>
		<%=session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br/>
		<a href = Logout.jsp>[로그아웃]</a>
		
	<% 
	}
	%>
								
<!-- Sidebar -->
					<div id="sidebar">
						<div class="inner">

							<!-- Search -->
								<section id="search" class="alt">
									<form method="post" action="#">
										<input type="text" name="query" id="query" placeholder="Search" />
									</form>
								</section>

							<!-- Menu -->
								<nav id="menu">
									<header class="major">
										<h2>Menu</h2>
									</header>
									<ul>
										<li><a href="Main.jsp">Homepage</a></li>
										<li><a href="generic.jsp">Generic</a></li>
										<li><a href="elements.jsp">Elements</a></li>
										<li>
											<span class="opener">Submenu</span>
											<ul>
												<li><a href="#">Lorem Dolor</a></li>
												<li><a href="#">Ipsum Adipiscing</a></li>
												<li><a href="#">Tempus Magna</a></li>
												<li><a href="#">Feugiat Veroeros</a></li>
											</ul>
										</li>
										<li><a href="#">Etiam Dolore</a></li>
										<li><a href="#">Adipiscing</a></li>
										<li>
											<span class="opener">Another Submenu</span>
											<ul>
												<li><a href="#">Lorem Dolor</a></li>
												<li><a href="#">Ipsum Adipiscing</a></li>
												<li><a href="#">Tempus Magna</a></li>
												<li><a href="#">Feugiat Veroeros</a></li>
											</ul>
										</li>
										<li><a href="#">Maximus Erat</a></li>
										<li><a href="#">Sapien Mauris</a></li>
										<li><a href="#">Amet Lacinia</a></li>
									</ul>
								</nav>

							<!-- Footer -->
								<footer id="footer">
									<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
								</footer>

						</div>
					</div>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>