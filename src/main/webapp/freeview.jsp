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
		<title>Generic - Editorial by HTML5 UP</title>
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
									
							<!-- Content -->
								<section>
								<header class="main">
										<h1>자유게시판</h1>
									</header>
									<header class="main">
				    					<table border="1">
									    <tr>
									        <td>Title</td>
									        <td colspan="3">${ dto.title }</td>
									    </tr>
									  <tbody>
									    <tr>
									      <td>번호</td>
									      <td>${ dto.board_id }</td>
									      <td>작성자</td>
									      <td>${ dto.username }</td>
									    </tr>
									    <tr>
									      <td>작성일</td>
									      <td>${ dto.postdate }</td>
									      <td>조회수</td>
									      <td>${ dto.visitcount }</td>
									    </tr>
									  </tbody>
									</table>
									</header>

				<table>					<tr>
				<th>내용</th>
                <td colspan="3" class="content">
                   ${ dto.content }
                    <c:if test="${ not empty dto.ofile }">
                    <br />
              <c:choose>
                 <c:when test = "${ mimeType eq 'img' }" >
                 <!-- 이미지를 출력한다.  -->
                    <div class="media-container">
                    <br><img src="./Uploads/${ dto.sfile }" style="max-width:600%;"/>
                    </div>
                 </c:when>
                 <c:when test = "${ mimeType eq 'audio' }" >
                    <div class="media-container">
                    <br><audio controls="controls">
                       <source src="./Uploads/${ dto.sfile }" type="audio/mp3"/>
                    </audio> 
                    </div>
                 </c:when>
                 <c:when test = "${ mimeType eq 'video' }" >
                    <div class="media-container">
                    <br><video controls>
                       <source src="./Uploads/${ dto.sfile }" type="video/mp4"/>
                    </video>
                    </div> 
                 </c:when>
                 <c:otherwise>
                    <a href="./download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&board_id=${ dto.board_id }">
                    [다운로드]  
                    </a>
                 </c:otherwise>
              </c:choose>
           </c:if>   
                </td>
    </tr> 
    
    <!--  첨부파일  -->
            <tr>
                <th>첨부파일</th>
                <td>
                   <!-- 첨부한 파일이 있다면 다운로드 링크를 출력한다.  -->
                 <c:if test="${ not empty dto.ofile }">
                 ${ dto.ofile }
                 <!-- 링크는 반드시 공백없이 한줄로 작성해야 한다. -->
                 <a href="./download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&board_id=${ dto.board_id }">
                 [다운로드]  
                 </a>
                 </c:if>
                </td>
                <th>다운로드수</th>
                <td>${ dto.downcount }</td>
            </tr>
    <tr>
        <td colspan="4" align="center">
        
        <!-- 사용자 ID와 게시물 ID 비교하여 수정/삭제 버튼 보여주기 -->
        <c:if test="${username eq dto.username}">
            <button type="button" onclick="location.href='freeEdit.do?board_id=${param.board_id}';">수정하기</button>
            <button type="button" onclick="deleteConfirm('${param.board_id}');">삭제하기</button>
        </c:if>

            <button type="button" onclick="location.href='./freeList.do';">목록 바로가기</button>
        </td>
    </tr>

</table>

									
								</section>

						</div>
					</div>

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

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>

<script>
function deleteConfirm(board_id){
    // 삭제 확인을 위한 confirm 창
    let c = confirm("게시물을 삭제할까요?");
    if(c == true){
        // 삭제 확인 후 해당 URL로 이동
        location.href = "./delete.do?board_id=" + board_id;
    }
}
</script>