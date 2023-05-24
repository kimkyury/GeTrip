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
<script>
import { mapState, mapActions, mapMutations } from "vuex";
const favoriteStore = "favoriteStore";
const loginStore = "loginStore";

export default {
    methods: {
        ...mapMutations(favoriteStore, ["SET_USERINFO"]),
        ...mapActions(favoriteStore, [
            "getHotplaceList",
            "getHotplaceListFromUser",
            "getFavoriteList",
        ]),
    },

    computed: {
        ...mapState(loginStore, [
            "userSeq",
            "userName",
            "userSidoName",
            "userSidoCode",
            "userGugunName",
            "userGugunCode",
        ]),

        ...mapState(favoriteStore, [
            "hotplaceList",
            "hotplaceListFromUser",

            "hotplaceCount",
            "hotplaceCountFromUser",

            "favoriteList",
            "favoriteListCount",
        ]),
    },

    async created() {
        this.SET_USERINFO({
            userSeq: this.userSeq,
            userSidoName: this.userSidoName,
            userSidoCode: this.userSidoCode,
            userGugunName: this.userGugunName,
        });

        await this.getHotplaceList();
        await this.getHotplaceListFromUser();
        await this.getFavoriteList();
    },
};
</script>
