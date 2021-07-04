<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@page import="ajaxmovie.db.AjaxMovieDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String num=request.getParameter("unum");
	String writer=request.getParameter("uwriter");
	String subject=request.getParameter("usubject");
	String content=request.getParameter("ucontent");
	String rate=request.getParameter("urate");
	String image=request.getParameter("uimage");
	String likes=request.getParameter("ulikes");
	
	

	AjaxMovieDto dto=new AjaxMovieDto();
	dto.setNum(num);
	dto.setWriter(writer);
	dto.setSubject(subject);
	dto.setContent(content);
	dto.setRate(rate);
	dto.setImage(image);
	if ("".equals(likes)) likes = "0";
	if (likes==null) likes = "0";
	int nLikes = Integer.parseInt(likes);
	dto.setLikes(nLikes);

	AjaxMovieDao dao=new AjaxMovieDao();

	dao.updateMovie(dto);
%>