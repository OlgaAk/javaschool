<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="navbar">
    <div class="nav-left">
        <div class="logo">
            <a href="/">
                <%@include file="train.svg" %>
            </a>
        </div>
    </div>
    <div class="nav-right">
        <div class="login_container">
            <sec:authorize access="isAnonymous()">
            <a href="/admin">
                <span class="login">Log in</span>
            </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="/logout">
                    <span class="login">Log out</span>
                </a>
            </sec:authorize>
        </div>

    </div>
</header>