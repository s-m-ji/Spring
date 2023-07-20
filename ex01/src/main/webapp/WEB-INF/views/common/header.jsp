<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
    <meta name="generator" content="Hugo 0.104.2" />
    <title>Fixed top navbar example · Bootstrap v5.2</title>

    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/"
    />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, 0.1);
        border: solid rgba(0, 0, 0, 0.15);
        border-width: 1px 0;
        box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
          inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -0.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      
     /* Show it is fixed to the top */
     body {
	  min-height: 75rem;
	  padding-top: 4.5rem;
	}
     
     /* Custom CSS (MI, 2023/07/05) */
     .kero {
     	width: 75px;
     	border-radius: 50%;
     }
     
     h2{
     	margin-bottom: 50px;
     }
     
     .list-group a:nth-child(odd){
     	background-color:#dfdfdf;
     }
     
     @font-face {
		  font-family: "SUITE-Regular";
		  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
		    format("woff2");
		  font-weight: 400;
		  font-style: normal;
	}
	:not(i) {
	  font-family: "SUITE-Regular" !important;
	} 
		
	th, td {
		vertical-align: middle;
	}
	
	i {
		cursor: pointer;
	    padding: 5px;
	}
	
	i:hover {
		background: #cdff00;
	}
	
	.page-item {
		cursor: pointer;
	}
	
	.point {
            background: #adff2f96;
		    padding: 5px 15px;
		    border-radius: 15px;
		    display: inline-block;
		}
     
    </style>

    <!-- Custom styles for this template -->
    <!-- <link href="navbar-top-fixed.css" rel="stylesheet" /> -->
    
    <!-- Custom js (MI, 2023/07/06) -->
    <script src='/resources/js/custom.js'></script>
    <script src='/resources/js/common.js'></script>
    <!-- <script src='/resources/js/reply.js'></script> -->
    <!-- <script src='/resources/js/file.js'></script> -->
    
    <!-- fontawesome 폰트 -->
    <script src="https://kit.fontawesome.com/bc0f5040fb.js" crossorigin="anonymous"></script>
    
  </head>
  <body>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
			<img class="kero" alt="로고이미지" src="/resources/images/파일명.png">
		</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav me-auto mb-2 mb-md-0">
            <c:if test="${not empty userId}">
	            <li class="nav-item"> <a class="nav-link"> 
            		<font color="#fff"> 🖐 <span class="point">ID : ${userId} <%-- ${member.id} --%></span> 어서옵쇼 ‍🖐 </font> 
	            		</a> </li>
            </c:if>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
            <c:if test="${empty userId}">
              <a class="nav-link" href="../login">Login</a>
              </c:if>
            <c:if test="${not empty userId}">
              <a class="nav-link" href="../logout">Logout</a>
              </c:if>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../login?f=on">Register</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled">Disabled</a>
            </li>
          </ul>
          <!-- <form class="d-flex" role="search">
          <select class="form-select" aria-label="Default select example">
			  <option selected>전체 검색</option>
			  <option value="1">One</option>
			  <option value="2">Two</option>
			  <option value="3">Three</option>
			</select>
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button class="btn btn-outline-success" type="submit">
              Search
            </button>
          </form> -->
        </div>
      </div>
    </nav>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>