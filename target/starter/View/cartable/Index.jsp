<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head > 
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="../Head.jsp" %>
    <script language="javascript" type="text/javascript">
        var restUrl="<c:url value = '/rest/cartable' />";
        function init() {
            fillTable();
        }
        function fillTable() {
            Loader(true);
            $.getJSON(restUrl+"/process/list", {}, function (entities) {
                $('table.grid tbody tr').remove();
                $('#GridRowTemplate').tmpl(entities).appendTo('#entityBody');
                $('table.grid tbody tr:not([th]):odd').addClass('oddRow');
                $('table.grid tbody tr:not([th]):even').css('backgroundColor', '#DFEBF4');
                createNavigation(10, pageNo, pageSize);
                Loader(false);
            });
        }
        function startProcess(processId) {
            Loader(true);
            $.ajax({
				type:"POST",
				url	:restUrl+"/process/start/" + processId,
				contentType:"application/json;",
				dataType:"json",
				success:function(res){Loader(false);}
            });
        }
    </script>
</head>
<body onload="pageLoad()">
    <form id="FormMain" >
		<%@ include file="Grid.jsp" %>	
    </form>
</body>
</html>
