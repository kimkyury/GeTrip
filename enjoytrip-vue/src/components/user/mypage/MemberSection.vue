<template>
    <div class="col-lg-3">
        <h3 class="h4 text-uppercase lined mb-4">My Page</h3>
        <nav class="nav flex-column nav-pills">
            <router-link class="nav-link text-sm" :to="{ name: 'UserWishList' }">
                <i class="me-2 fas fa-heart"></i> 나의 여행 좋아요 목록</router-link
            >
            <router-link class="nav-link text-sm" :to="{ name: 'UserInfoChange' }">
                <i class="me-2 fas fa-user"></i> 계정 정보 조회 및 변경
            </router-link>

            <a class="nav-link text-sm" href="index.html">
                <i class="me-2 fas fa-door-open"></i><span>로그아웃</span></a
            >
        </nav>
    </div>
</template>
