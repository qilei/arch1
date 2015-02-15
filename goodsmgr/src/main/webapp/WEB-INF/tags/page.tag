<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="pagedList" type="me.lei.pagination.dto.PageMyBatis " required="true" description="分页对象" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    int total = (int)pagedList.getTotal();
    int current =pagedList.getPageable().getPageNumber();
    int begin = 1;
    int end = (int) Math.ceil((double)pagedList.getTotal() / pagedList.getPageable().getDisplaySize());
    String queryString = request.getQueryString();
    if(queryString != null){
        queryString = queryString.replaceAll("&p=\\d","");
    }

    request.setAttribute("current", current);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
    request.setAttribute("total", total);
    request.setAttribute("queryString",queryString);
%>
<table width="100%" align="center">
    <tr>
        <td>
            <span class="page-info">[共有记录${total}条  / 共分${end}页  ， 当前是第 ${current} 页]</span >
        </td>
    </tr>
    <tr>
        <td>
            <c:choose>
                <c:when test="${current==1}">
                    首页 &nbsp;&nbsp;
                    上一页&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="?${queryString}&p=1" title="首页">首页</a>&nbsp;&nbsp;
                    <a href="?${queryString}&p=${current - 1}" title="上一页">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${current==end}">
                    下一页&nbsp;&nbsp;
                    尾页&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="?${queryString}&p=${current + 1}"  title="下一页">下一页</a>&nbsp;&nbsp;
                    <a href="?${queryString}&p=${end}" title="尾页">尾页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>

        </td>
    </tr>
</table>