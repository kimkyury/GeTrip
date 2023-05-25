<template>
    <div class="col-lg-3">
        <h3 class="h4 text-uppercase mb-4">Place</h3>
        <nav class="nav flex-column nav-pills">
            <router-link class="nav-link text-sm" :to="{ name: 'PlacePage' }">
                <i class="me-2 fas fa-user"></i> test
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlacePage' }">
                <i class="me-2 fas fa-heart"></i> test
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlaceFromUserPage' }">
                <i class="me-2 fas fa-heart"></i> test
            </router-link>
        </nav>
    </div>
</template>
<script></script>
