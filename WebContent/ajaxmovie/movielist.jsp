<?xml version="1.0" encoding="UTF-8"?>
<%@page import="ajaxmovie.db.AjaxMovieDto"%>
<%@page import="ajaxmovie.db.AjaxMovieDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" 
    pageEncoding="UTF-8"%>  
<list>
<%
	//start를 읽는다
	int start=0;
	try{
		start=Integer.parseInt(request.getParameter("start"));
	}catch(NumberFormatException e){
		start=1;
		System.out.println("페이지start 정수로 변환시 오류:"+e.getMessage());
	}
	//dao 선언
	AjaxMovieDao dao=new AjaxMovieDao();

    //목록 10개씩만가져오기
    List<AjaxMovieDto> list=dao.getPagingDatas(start);
    //전체갯수 구하기
    int total=dao.getTotalCount();
    %>
    <total><%=total%></total>
    <%SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
    for(AjaxMovieDto dto:list)
    {%>    <!-- 속성띄어쓰기.. -->
    	<movie num="<%=dto.getNum()%>" likes="<%=dto.getLikes()%> "> 
    		<writer><%=dto.getWriter()%></writer>
    		<subject><%=dto.getSubject()%></subject>
    		<content><%=dto.getContent()%></content>
    		<image><%=dto.getImage()%></image>
    		<rate><%=dto.getRate()%></rate>
    		<longday><%=sdf1.format(dto.getWriteday())%></longday>
    		<shortday><%=sdf2.format(dto.getWriteday())%></shortday>
    	</movie>
    <%}

%>
</list>