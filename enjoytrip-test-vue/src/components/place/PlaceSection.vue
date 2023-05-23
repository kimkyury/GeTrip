<template>
    <div class="col-lg-3">
        <h3 class="h4 text-uppercase mb-4">Place</h3>
        <nav class="nav flex-column nav-pills">
            <router-link class="nav-link text-sm" :to="{ name: 'PlacePage' }">
                <i class="me-2 fas fa-user"></i> 여행 검색
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlacePage' }">
                <i class="me-2 fas fa-heart"></i> 핫 플레이스
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlaceFromUserPage' }">
                <i class="me-2 fas fa-heart"></i> 내 지역 핫플레이스
            </router-link>
        </nav>
    </div>
</template>
