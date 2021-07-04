<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@page import="ajaxmovie.db.AjaxMovieDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	
  String writer=request.getParameter("writer");
  String pass=request.getParameter("pass");
  String subject=request.getParameter("subject");
  String content=request.getParameter("content");
  String rate=request.getParameter("rate");
  String image=request.getParameter("image");

	AjaxMovieDto dto=new AjaxMovieDto();
	dto.setWriter(writer);
	dto.setPass(pass);
	dto.setSubject(subject);   
	dto.setContent(content);
	dto.setRate(rate);
	dto.setImage(image);
	
	AjaxMovieDao dao=new AjaxMovieDao();
	dao.insertMovie(dto);


%>