<template lang="">
    <!-- <div style="height: 70px"></div> -->
    <div>
        <section class="py-5">
            <!-- 중앙 center content end -->
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>HotPlaces</h1>
                        <p class="lead mb-5">전국의 인기많은 관광지를 알려줄게요</p>
                    </div>
                    <place-section></place-section>
                </div>
            </div>
        </section>
        <section class="py-5">
            <div class="container py-4">
                <div class="row gy-4">
                    <div
                        class="col-lg-4"
                        v-for="(hotplace, index) in hotplaceList"
                        :key="index"
                    >
                        <!-- Portfolio item-->
                        <div
                            class="image-container box-image-text text-center primary-overlay"
                        >
                            <img
                                v-if="hotplace.firstImage == ''"
                                style="height: 250px"
                                class="img-fluid fit-image"
                                src="@/assets/img/enjoytrip/unfind.png"
                                alt="..."
                            />
                            <img
                                v-else
                                style="height: 250px"
                                :src="hotplace.firstImage"
                                class="img-fluid fit-image"
                                alt="..."
                            />
                            <div class="overlay-content p-4">
                                <h4
                                    class="text-uppercase box-image-text-heading"
                                    style="margin-top: 20px"
                                >
                                    {{ hotplace.title }}
                                </h4>
                                <p class="text-white box-image-text-description">
                                    {{ hotplace.addr1 }}
                                </p>
                                <p class="text-white box-image-text-description">
                                    좋아요한 회원 수 : {{ hotplace.favoriteCnt }}
                                </p>

                                <ul class="list-inline mb-0 box-image-text-content">
                                    <li class="list-inline-item">
                                        <a
                                            class="btn btn-outline-light"
                                            href="#"
                                            @click="tripDetail(hotplace.contentId)"
                                            >자세히보기</a
                                        >
                                    </li>
                                    <li class="list-inline-item">
                                        <div v-if="isLogin">
                                            <a
                                                class="btn btn-outline-pink"
                                                v-if="checkIsFavorite(hotplace.contentId)"
                                                @click="
                                                    changeFavoriteState(
                                                        0,
                                                        hotplace.contentId
                                                    )
                                                "
                                            >
                                                🖤 취소
                                            </a>
                                            <a
                                                class="btn btn-outline-dark"
                                                v-else
                                                @click="
                                                    changeFavoriteState(
                                                        1,
                                                        hotplace.contentId
                                                    )
                                                "
                                            >
                                                💗 좋아요
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import PlaceSection from "./PlaceSection.vue";
import { mapMutations, mapState, mapActions } from "vuex";
import http from "@/common/axios.js";
const favoriteStore = "favoriteStore";
const placeStore = "placeStore";
const loginStore = "loginStore";

export default {
    components: { PlaceSection },

    methods: {
        ...mapMutations(favoriteStore, ["SET_USERINFO"]),
        ...mapActions(placeStore, ["getTripDetail"]),
        ...mapActions(favoriteStore, [
            "getHotplaceList",
            "getHotplaceListFromUser",
            "getFavoriteList",
            "postFavorite",
        ]),

        tripDetail(contentId) {
            this.getTripDetail(contentId);
            this.$router.push({ name: "PlaceDetailPage" });
        },
        async changeFavoriteState(curState, contentId) {
            let params = {
                userSeq: this.userSeq,
                contentId: contentId,
            };

            try {
                let { data } = await http.post(
                    `/users/${this.userSeq}/places/favorites`,
                    params
                );

                if (data.result == 1) {
                    if (curState == 1) {
                        this.$alertify.success("좋아요에 담았습니다");
                    } else if (curState == 0) {
                        this.$alertify.error("좋아요를 취소했습니다");
                    }
                }
            } catch (error) {
                console.log(error);
            }

            try {
                await this.getFavoriteList();
            } catch (error) {
                console.log(error);
            }
        },

        checkIsFavorite(contentId) {
            let result = this.isFavorite(contentId);
            return result;
        },
        isFavorite(contentId) {
            return this.favoriteHotplaceList.some(
                (favorite) => favorite.contentId === contentId
            );
        },
    },
    computed: {
        ...mapState(loginStore, [
            "isLogin",
            "userSeq",
            "userName",
            "userSidoName",
            "userSidoCode",
            "userGugunName",
            "userGugunCode",
        ]),
        ...mapState(favoriteStore, [
            "hotplaceList",
            "hotplaceCount",

            "hotplaceListFromUser",
            "hotplaceCountFromUser",

            "favoriteList",
            "favoriteCount",
        ]),

        // 유저의 favoriteList와 Hotplace의 일치하는 배열만 리턴
        favoriteHotplaceList() {
            return this.hotplaceList.filter((hotplace) =>
                this.favoriteList.some(
                    (favorite) => favorite.contentId === hotplace.contentId
                )
            );
        },
    },
    async created() {
        this.SET_USERINFO({
            userSeq: this.userSeq,
            userSidoName: this.userSidoName,
            userSidoCode: this.userSidoCode,
            userGugunName: this.userGugunName,
        });

        if (this.isLogin) {
            await this.getHotplaceListFromUser();
            await this.getFavoriteList();
        }
        await this.getHotplaceList();
    },
    async mounted() {
        if (this.isLogin) {
            await this.getFavoriteList();
        }
        await this.getHotplaceList();
    },
};
</script>
<style scoped>
.image-container {
    width: 350px;
    height: 250px;
    overflow: hidden;
}
.fit-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
}
</style>
