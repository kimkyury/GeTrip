<template lang="">
    <!-- <div style="height: 70px"></div> -->
    <div>
        <section class="py-4">
            <!-- 중앙 center content end -->
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>HotPlaces around You</h1>

                        <div v-if="isLogin">
                            <p class="lead mb-5">
                                "
                                <span
                                    v-for="(nameChar, nameIndex) in userName"
                                    :key="'userName-' + nameIndex"
                                    class="custom-item"
                                    :style="{ animationDelay: nameIndex * 200 + 'ms' }"
                                    v-text="nameChar"
                                />
                                "님의 지역, "

                                <span
                                    v-for="(sidoChar, sidoIndex) in userSidoName"
                                    :key="'sido-' + sidoIndex"
                                    class="custom-item"
                                    :style="{ animationDelay: sidoIndex * 200 + 'ms' }"
                                    v-text="sidoChar"
                                />
                                "의 인기많은 관광지를 알려줄게요
                            </p>
                        </div>
                        <div v-else>
                            <p class="lead mb-5">
                                <span
                                    v-for="(guideChar, guideIndex) in guideText1"
                                    :key="'guide1-' + guideIndex"
                                    class="custom-item"
                                    :style="{ animationDelay: guideIndex * 200 + 'ms' }"
                                    v-text="guideChar"
                                />
                                을 하시면, 당신 지역의

                                <span
                                    v-for="(guideChar, guideIndex) in guideText2"
                                    :key="'guide2-' + guideIndex"
                                    class="custom-item"
                                    :style="{ animationDelay: guideIndex * 200 + 'ms' }"
                                    v-text="guideChar"
                                />
                                한 관광지를 알려줄게요
                            </p>
                        </div>
                    </div>
                    <place-section></place-section>
                </div>
            </div>
        </section>
        <div v-if="isLogin">
            <section class="py-5">
                <div class="container py-4">
                    <div class="row gy-4">
                        <div
                            class="col-lg-4"
                            v-for="(hotplace, index) in hotplaceListFromUser"
                            :key="index"
                        >
                            <!-- Portfolio item-->
                            <div
                                class="image-container box-image-text text-center primary-overlay"
                            >
                                <img
                                    style="height: 250px"
                                    v-if="hotplace.firstImage == ''"
                                    class="img-fluid fit-image"
                                    src="@/assets/img/enjoytrip/unfind.png"
                                    alt="..."
                                />
                                <img
                                    style="height: 250px"
                                    v-else
                                    :src="hotplace.firstImage"
                                    class="img-fluid fit-image"
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
                                            <div v-if="isLogin">
                                                <a
                                                    class="btn btn-outline-dark"
                                                    v-if="
                                                        checkIsFavorite(
                                                            hotplace.contentId
                                                        )
                                                    "
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
                                                    class="btn btn-outline-pink"
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
        <div class="container py-4" v-else>로그인이 필요한 서비스입니다.</div>
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
    data() {
        return {
            guideText1: "회원가입",
            guideText2: "HOT!",
        };
    },

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
            let result = false;
            if (this.favoriteCount > 0) {
                result = this.isFavorite(contentId);
            }
            return result;
        },
        isFavorite(contentId) {
            return this.favoriteHotplaceList.some(
                (favorite) => favorite.contentId === contentId
            );
        },
    },
    computed: {
        ...mapState(loginStore, ["userSeq", "userName", "userSidoName", "isLogin"]),
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
            return this.hotplaceListFromUser.filter((hotplace) =>
                this.favoriteList.some(
                    (favorite) => favorite.contentId === hotplace.contentId
                )
            );
        },
    },

    created() {
        console.log("hotplaceCountFromUser: ", this.hotplaceCountFromUser);

        if (this.hotplaceCountFromUser == 0) {
            this.$router.push({ name: "HotPlaceErrorPage" });
        }
    },

    async mounted() {
        // await this.getHotplaceList();
        // await this.getFavoriteList();
    },
};
</script>
<style scoped>
@keyframes text-in {
    0% {
        transform: translate(0, -20px);
        opacity: 0;
    }
}
.custom-item {
    display: inline-block;
    min-width: 0.3em;
    font-size: 1.5rem;
    animation: text-in 0.8s cubic-bezier(0.22, 0.15, 0.25, 1.43) 0s backwards;
}
</style>
