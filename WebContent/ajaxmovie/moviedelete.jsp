<?xml version="1.0" encoding="UTF-8"?>
<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//num,pass를 읽는다
	String num=request.getParameter("num");
	String pass=request.getParameter("pass");
	
	//dao선언
	AjaxMovieDao dao=new AjaxMovieDao();
	//비번 체크
	boolean b=dao.isPassCheck(num,pass);
	if(b){
		dao.deleteBoard(num);
	}

%>
<result><%=b?"success":"fail"%></result>