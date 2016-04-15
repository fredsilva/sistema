
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isFullyAuthenticated()">
    <c:redirect url="/public/home.jsf"/>
</sec:authorize>

<sec:authorize access="!isFullyAuthenticated()">
    <c:redirect url="/public/login.jsf"/>
</sec:authorize>
