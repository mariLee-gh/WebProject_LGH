<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
									<a href="index.jsp" class="logo"><strong>Editorial</strong> by HTML5 UP</a>
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
								</header>

						<section>
				
												<!-- Table -->
													<h3>자유게시판</h3>
																			
													<div class="table-wrapper">
														<table>
															<thead>
																<tr>
																	<th>번호</th>
																	<th>제목</th>
																	<th>작성자</th>
																	<th>작성일</th>
																	<th>조회수</th>
																	<th>좋아요</th>
																	
																</tr>
															</thead>
															<tbody>
																<c:choose>
										<c:when test="${empty boardLists }">
										<!--List에 저장된 값이 없다면 등록된 게시물이 없거나, 검색죈 게시물이 없는 경우.  -->
											<tr>
												<td colspan="6" align="center">
													등록된 게시물이 없습니다 ^^*	
												</td>
											</tr>
										</c:when>
										<c:otherwise>
										<!-- 
										list에 저장된 데이터가 있다면, 크기만큼 반복하여 출력한다.
										해당루프의 데이터르 인출하여 변수 row에 할당한다.
										 -->
											<c:forEach items="${boardLists }" var="row" varStatus="loop">
											<tr align="center">
												<td>
													${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
												</td>
												<td align="left">
												<!-- 제목 클릭시 '열람'페이지로 이동해야 하므로 게시물의 일련번호를 파라미터로 전달한다.  -->
													<a href="/view.do?board_id=${row.board_id}">${row.title }</a>
												</td>
												<!--현재 루프에서 row는 MVCBoardDTO를 의미하므로 각 멤버변수의 getter()를 통해 저장된값을 출력한다.  -->
												<td>${row.user_id}</td>
												<td>${row.postdate }</td>
												<td>${row.visitcount }</td>
												<td>${row.like_count }</td>
												
								
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
															</tbody>
														
														</table>
														<form method="get">
															<table border="1" width="90%" style="border-collapse: collapse;">
															    <tr>
															        <td align="center" style="padding: 10px; white-space: nowrap;">
															            <!-- 검색 기준 선택 -->
															            <select name="searchField" style="width: 150px; display: inline-block;">
															                <option value="title">제목</option>
															                <option value="content">내용</option>
															                <option value="user_id">작성자</option>
															            </select>
															            
															            <!-- 검색어 입력 -->
															            <input type="text" name="searchWord" id="search" placeholder="Search" style="width: 350px; display: inline-block; " />
															            
															            <!-- 검색 버튼 -->
															            <input type="submit" value="검색하기" style="width: 100px; display: inline-block;" />
															        </td>
															    </tr>
															</table>
															</form>
														
											
													</div>
													
												<table border="1" width="90%">
												        <tr align="center">
												        	<td>
												        	 <ul class="pagination">
												        		${ map.pagingImg }
												        		</ul>
												        	</td>
												            <td><!--현재 페이지번호 없음  --></td>
												            <td width="100"><button type="button"
												                onclick="/write.do;">글쓰기</button></td>
												        </tr>
												    </table>

							

											</div>
											
											
											
										</div>
<!-- Sidebar -->
					<div id="sidebar">
						<div class="inner">

							<!-- Search -->
			

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

							<!-- Section -->
								<section>
									<header class="major">
										<h2>Ante interdum</h2>
									</header>
									<div class="mini-posts">
										<article>
											<a href="#" class="image"><img src="images/pic07.jpg" alt="" /></a>
											<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
										</article>
										<article>
											<a href="#" class="image"><img src="images/pic08.jpg" alt="" /></a>
											<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
										</article>
										<article>
											<a href="#" class="image"><img src="images/pic09.jpg" alt="" /></a>
											<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
										</article>
									</div>
									<ul class="actions">
										<li><a href="#" class="button">More</a></li>
									</ul>
								</section>

							<!-- Section -->
								<section>
									<header class="major">
										<h2>Get in touch</h2>
									</header>
									<p>Sed varius enim lorem ullamcorper dolore aliquam aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore. Proin sed aliquam facilisis ante interdum. Sed nulla amet lorem feugiat tempus aliquam.</p>
									<ul class="contact">
										<li class="icon solid fa-envelope"><a href="#">information@untitled.tld</a></li>
										<li class="icon solid fa-phone">(000) 000-0000</li>
										<li class="icon solid fa-home">1234 Somewhere Road #8254<br />
										Nashville, TN 00000-0000</li>
									</ul>
								</section>

							<!-- Footer -->
								<footer id="footer">
									<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
								</footer>

						</div>
					</div>

								</section>
								

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