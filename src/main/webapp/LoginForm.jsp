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

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="home.jsp" class="logo"><strong>Editorial</strong> by HTML5 UP</a>
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
								</header>
								<span style="color:red;">
           <%= request.getAttribute("LoginErrMsg")==null? "" : request.getAttribute("LoginErrMsg") %>
          </span>

							<!-- Content -->
								<section>
									<header class="main">
										<h1>Elements</h1>
									</header>

									<!-- Content -->
										<h2 id="content">Sample Content</h2>
								            <form name="loginForm" method="post" action="login.do" onsubmit="return validateForm(this);">
							                <div class="row gtr-uniform">
							                    <div class="col-6 col-12-xsmall">
							                        <label for="username">Username(아이디)</label>
							                        <input type="text" name="username" id="username" placeholder="Username(아이디)" />
							                    </div>
							                    <div class="col-6 col-12-xsmall">
							                        <label for="password">Password(비밀번호)</label>
							                        <input type="password" name="password" id="password" placeholder="Password(비밀번호)" />
							                    </div>
							
							                    <!-- Break -->
							                    <div class="col-12">
							                        <ul class="actions">
							                            <li><input type="submit" value="로그인" class="primary" /></li>
							                            <li><input type="reset" value="Reset" /></li>
							                        </ul>
							                    </div>
							                </div>
							            </form>

									<hr class="major" />

											
										</div>

								</section>

						</div>
						
						
						<script>
    function validateForm(form) {
        // 아이디 확인
        if (form.username.value.trim() === "") {
            alert("아이디를 입력하세요.");
            form.username.focus();
            return false;
        }

        // 비밀번호 확인
        if (form.password.value.trim() === "") {
            alert("비밀번호를 입력하세요.");
            form.password.focus();
            return false;
        }

        return true;
    }
</script>
						
						
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
										<li><a href="index.jsp">Homepage</a></li>
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



			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>