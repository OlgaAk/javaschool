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
        <sec:authorize access="isAnonymous()">
            <div class="login_container">
                <a href="/user/profile">
                    <span class="login">Log in</span>
                </a>
            </div>
            <div class="login_container signup_container">
                <a href="/user/signup">
                    <span class="signup">Sign up</span>
                </a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="login_container">
                <a href="/user/profile">
                    <span class="login">Profile</span>
                </a>
            </div>
            <div class="login_container logout_container">
                <a href="/user/logout">
                    <span class="logout">Log out</span>
                </a>
            </div>
        </sec:authorize>

    </div>
</header>