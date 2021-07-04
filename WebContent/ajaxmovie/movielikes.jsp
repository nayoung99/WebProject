<?xml version="1.0" encoding="UTF-8"?>
<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num=request.getParameter("num");
	AjaxMovieDao dao=new AjaxMovieDao();
	int likes=dao.updateLikes(num);
%>
<likes><%=likes%></likes>
