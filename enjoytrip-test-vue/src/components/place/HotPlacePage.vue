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
                        <div class="box-image-text text-center primary-overlay">
                            <img
                                v-if="hotplace.firstImage == ''"
                                style="height: 250px"
                                class="img-fluid"
                                src="@/assets/img/enjoytrip/unfind.png"
                                alt="..."
                            />
                            <img
                                v-else
                                style="height: 250px"
                                :src="hotplace.firstImage"
                                class="img-fluid"
                                alt="..."
                            />
                            <div
                                class="overlay-content d-flex flex-column justify-content-center p-4"
                            >
                                <h4 class="text-uppercase box-image-text-heading">
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
                                        <a
                                            class="btn btn-outline-dark"
                                            href="#"
                                            v-if="isFavorite(hotplace.contentId)"
                                            @click="
                                                changeFavoriteState(1, hotplace.contentId)
                                            "
                                        >
                                            💗 -> 🖤
                                        </a>
                                        <a
                                            class="btn btn-outline-dark"
                                            href="#"
                                            v-else
                                            @click="
                                                changeFavoriteState(0, hotplace.contentId)
                                            "
                                        >
                                            🖤 -> 💗
                                        </a>
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
import { mapState, mapActions } from "vuex";
import http from "@/common/axios.js";
const favoriteStore = "favoriteStore";
const placeStore = "placeStore";

export default {
    components: { PlaceSection },
    methods: {
        ...mapActions(favoriteStore, ["getFavoriteCount", "postFavorite"]),
        ...mapActions(placeStore, ["getTripDetail"]),

        tripDetail(contentId) {
            this.getTripDetail(contentId);
            this.$router.push({ name: "PlaceDetailPage" });
        },
        async changeFavoriteState(curState, contentId) {
            console.log(curState);
            console.log("userSeq: " + this.userSeq);

            let params = {
                userSeq: this.userSeq,
                contentId: contentId,
            };

            console.log(params);

            try {
                let { data } = await http.post(
                    `/users/${this.userSeq}/places/favorites`,

                    params
                );

                if (data.result == 1) {
                    this.$alertify.success("클릭하였습니다");
                }
            } catch (error) {
                console.log(error);
            }
            // 1. 이미 유저가 좋아요를 누른 것인지 확인
            // 2. 유저가 좋아요를 누른 목록은 하트 취소
        },
        isFavorite(contentId) {
            console.log(this.favoriteHotplaceList);

            return this.favoriteHotplaceList.some(
                (favorite) => favorite.content_id === contentId
            );
        },
    },
    computed: {
        ...mapState(favoriteStore, ["hotplaceList", "hotplaceCount", "favoriteList"]),

        ...mapState("loginStore", ["userSeq"]),
        // 유저의 favoriteList와 Hotplace의 일치하는 배열만 리턴
        favoriteHotplaceList() {
            return this.hotplaceList.filter((hotplace) =>
                this.favoriteList.some(
                    (favorite) => favorite.content_id === hotplace.content_id
                )
            );
        },
    },
    mounted() {},
};
</script>
