<template>
    <div class="col-lg-3">
        <h3 class="h4 text-uppercase mb-4">Place</h3>
        <nav class="nav flex-column nav-pills">
            <router-link class="nav-link text-sm" :to="{ name: 'PlacePage' }">
                <i class="me-2 fas fa-user"></i> 여행 검색
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlacePage' }">
                <i class="me-2 fas fa-heart"></i> 전국 핫플
            </router-link>
            <router-link class="nav-link text-sm" :to="{ name: 'HotPlaceFromUserPage' }">
                <i class="me-2 fas fa-heart"></i> 내 지역 핫플
            </router-link>
        </nav>
    </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
const favoriteStore = "favoriteStore";
const loginStore = "loginStore";

export default {
    methods: {
        ...mapActions(favoriteStore, [
            "getHotplaceList",
            "getHotplaceListFromUser",
            "getFavoriteList",
        ]),
    },

    computed: {
        ...mapState(loginStore, ["isLogin"]),
        ...mapState(favoriteStore, ["hotplaceList", "hotplaceCount"]),
    },

    async created() {
        if (this.isLogin) {
            await this.getHotplaceListFromUser();
            await this.getFavoriteList();
        }
        await this.getHotplaceList();
    },
};
</script>
