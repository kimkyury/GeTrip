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
                                            v-if="checkIsFavorite(hotplace.contentId)"
                                            @click="
                                                changeFavoriteState(1, hotplace.contentId)
                                            "
                                        >
                                            💗 -> 🖤
                                        </a>
                                        <a
                                            class="btn btn-success"
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
const loginStore = "loginStore";

export default {
    components: { PlaceSection },
    methods: {
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
                    if (curState == 0) {
                        this.$alertify.success("좋아요에 담았습니다");
                    } else if (curState == 1) {
                        this.$alertify.error("좋아요를 취소했습니다");
                    }
                }
            } catch (error) {
                console.log(error);
            }

            // 담고 난 후, 리스트를 다시 계산해야 함
            // console.log("Favorite place 계산");
            try {
                await this.getFavoriteList();
            } catch (error) {
                console.log(error);
            }
        },

        checkIsFavorite(contentId) {
            let result = this.isFavorite(contentId);
            // console.log(contentId + "의 FH 존재결과: ", result);
            return result;
        },
        isFavorite(contentId) {
            return this.favoriteHotplaceList.some(
                (favorite) => favorite.contentId === contentId
            );
        },
    },
    computed: {
        ...mapState(loginStore, ["userSeq"]),
        ...mapState(favoriteStore, [
            "hotplaceList",
            "hotplaceListFromUser",

            "hotplaceCount",
            "hotplaceCountFromUser",

            "favoriteList",
            "favoriteListCount",
        ]),

        // 유저의 favoriteList와 Hotplace의 일치하는 배열만 리턴
        favoriteHotplaceList() {
            // console.log("FavoriteHot place 계산");
            return this.hotplaceList.filter((hotplace) =>
                this.favoriteList.some(
                    (favorite) => favorite.contentId === hotplace.contentId
                )
            );
        },
    },
    async mounted() {
        await this.getHotplaceList();
        await this.getFavoriteList();
        // console.log("hotplaceList: ", this.hotplaceList);
        // console.log("favoriteList: ", this.favoriteList);
        // console.log("FH list: ", this.favoriteHotplaceList);
    },
};
</script>
