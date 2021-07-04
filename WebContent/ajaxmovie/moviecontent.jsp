<%@page import="ajaxmovie.db.AjaxMovieDto"%>
<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num=request.getParameter("num");
	AjaxMovieDao dao=new AjaxMovieDao();
	AjaxMovieDto dto=dao.getData(num);
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	JSONObject ob=new JSONObject();
	ob.put("num",dto.getNum());
	ob.put("writer",dto.getWriter());
	ob.put("subject",dto.getSubject());
	ob.put("content",dto.getContent());
	ob.put("rate",dto.getRate());
	ob.put("image",dto.getImage());
	ob.put("writeday",sdf.format(dto.getWriteday()));
	ob.put("likes",dto.getLikes());
%>
<%=ob.toString()%>

