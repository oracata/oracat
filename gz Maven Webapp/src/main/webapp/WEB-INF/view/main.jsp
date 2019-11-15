<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">









<frameset  cols="12%,*"  border="3"  id="main"  onload="javascript:  var frame=window.parent.document.getElementById('rightFrame');     if (frame.attachEvent){        frame.attachEvent('onload', function(){              alert('正在加载页面');  });} else {        frame.onload = function(){                alert('加载页面完成');       };} frame.document.body.appendChild(frame); " >
    <frame src="menu.do" name="topFrame"  id="leftFrame" />
    <frameset rows="100%"   border="1" >
        <frame src="realtime.do"   id="rightFrame"   />
    </frameset>
</frameset>







