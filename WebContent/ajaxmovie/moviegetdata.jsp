<?xml version="1.0" encoding="UTF-8"?>
<%@page import="ajaxmovie.db.AjaxMovieDto"%>
<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<data>
<%

	AjaxMovieDao dao=new AjaxMovieDao();	

	String num=request.getParameter("num");

	AjaxMovieDto dto=dao.getData(num);
	
%>
	<num><%=dto.getNum()%></num>
	<writer><%=dto.getWriter()%></writer>
	<subject><%=dto.getSubject()%></subject>
	<content><%=dto.getContent()%></content>
	<rate><%=dto.getRate()%></rate>
	<image><%=dto.getImage()%></image>
	<likes><%=dto.getLikes()%></likes>

</data>    