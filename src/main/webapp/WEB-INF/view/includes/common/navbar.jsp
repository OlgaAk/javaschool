<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="navbar">
    <div class="nav-left">
        <div class="logo">
            <a href="/">
                <%@include file="train.svg" %>
            </a>
        </div>
        <div class="brand"><span>S</span>BB</div>
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
            <sec:authorize access="!hasRole('ADMIN')">
                <div class="login_container">
                    <a href="/user/profile">
                        <span class="login">Profile</span>
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <div class="admin_menu_items_container">
                    <div class="admin_menu_item">
                        <a href="/admin/station">
                            <span class="active" id="admin_menu_item_stations">Stations</span>
                        </a>
                    </div>
                    <div class="admin_menu_item">
                        <a href="/admin/train">
                            <span id="admin_menu_item_trains">Trains</span>
                        </a>
                    </div>
                </div>
            </sec:authorize>
            <div class="login_container logout_container">
                <a href="/user/logout">
                    <span class="logout">Log out</span>
                </a>
            </div>
        </sec:authorize>

    </div>
</header>