<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>

<script type="text/javascript">
    function validateForm(form) {  // 필수 항목 입력 확인
        if (form.title.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.content.value == "") { 
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
    }
</script>
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

							<!-- Content -->
								<section>
									<!-- Elements -->
										<h2 id="elements">Elements</h2>
										<div class="row gtr-200">
											<div class="col-6 col-12-medium">

												<!-- Form -->
													<h3>Form</h3>

													<form name="writeFrm" method="post" enctype="multipart/form-data"
      															action="write.do" onsubmit="return validateForm(this);">
														<div class="row gtr-uniform">
															<div class="col-6 col-12-xsmall">
																<input type="text" name="title" id="title" value="" placeholder="제목을 적어주세요." />
															</div>
															<div class="col-6 col-12-xsmall">
																<input type="file" name="ofile" id="ofile" value="" placeholder="file" />
															</div>
															<!-- Break -->
															<div class="col-12">
																<textarea name="content" id="content" placeholder="내용을 적어주세요." rows="6"></textarea>
															</div>
															<!-- Break -->
															<div class="col-12">
															<ul class="actions">
															    <li><input type="button" value="목록 바로가기" onclick="location.href='freeList.do';" class="primary" /></li>
															    <li><input type="submit" value="작성 완료" class="primary" /></li>
															    <li><input type="reset" value="RESET" /></li>
															</ul>
															</div>
														</div>
													</form>


											</div>
											<div class="col-6 col-12-medium">

												

											</div>
										</div>

								</section>

						</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">
						<div class="inner">

						

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

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>