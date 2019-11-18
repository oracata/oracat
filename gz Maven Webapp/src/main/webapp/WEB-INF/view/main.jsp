<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

</head>


<frameset  cols="12%,*"  border="3"    id="mainFrame"     onload="javascript:      var frame=window.parent.document.getElementById('rightFrame');          frame.onload = function(){             window.parent.document.getElementById('rightFrame').contentWindow.completeLoading();     }; frame.document.body.appendChild(frame); " >


        <frame name="topFrame"  id="leftFrame" src="menu.do"  />

              <frameset rows="100%"   border="1" >
                       <frame   id="rightFrame"  src="realtime.do"   /> >
              </frameset>
</frameset>








</html>
















