<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My super project!</title>
</head>
<body>
<!-- header -->
<div>
    <h1>Super app!</h1>
</div>

<div>       <!-- content -->
    <div>    <!-- buttons holder -->


        <form action="hm" method="POST" style="display: inline;">
            <input type="hidden" name="action" value="increment">
            <button <c:if test="${disabled}">disabled</c:if> type="submit">
                Increment (+)
            </button>
        </form>

        <form action="hm" method="POST" style="display: inline;">
            <input type="hidden" name="action" value="decrement">
            <button type="submit" class="btn btn-inc">Decrement (-)</button>
        </form>
        <div>${count}</div>
    </div>
</div>
</body>
</html>