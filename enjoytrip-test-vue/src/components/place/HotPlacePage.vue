<template lang="">
    <!-- <div style="height: 70px"></div> -->
    <div>
        <section class="py-5">
            <!-- 중앙 center content end -->
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>HotPlaces</h1>
                        <p class="lead mb-5">인기많은 관광지를 알려줄게요</p>
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
                                class="img-fluid"
                                v-if="hotplace.firstImage == ''"
                                src="@/img/ssafy_logo.png"
                                alt="..."
                            />
                            <img
                                v-else
                                :src="hotplace.firstImage"
                                class="img-fluid"
                                alt="..."
                            />
                            <div
                                class="overlay-content d-flex flex-column justify-content-center p-4"
                            >
                                <h3 class="text-uppercase box-image-text-heading">
                                    {{ hotplace.title }}
                                </h3>
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
                                        <a class="btn btn-outline-light" href="#"
                                            >좋아요!</a
                                        >
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
const favoriteStore = "favoriteStore";
const placeStore = "placeStore";
export default {
    components: { PlaceSection },
    methods: {
        ...mapActions(favoriteStore, ["getHotplaceList", "getFavoriteCount"]),
        ...mapActions(placeStore, ["getTripDetail"]),

        tripDetail(contentId) {
            this.getTripDetail(contentId);
            this.$router.push({ name: "PlaceDetailPage" });
        },
    },
    computed: {
        ...mapState(favoriteStore, ["hotplaceList", "hotplaceCount"]),
    },

    async created() {
        await this.getHotplaceList();
        console.log("hotplaceList", this.hotplaceList);
        console.log("hotplaceCount", this.hotplaceCount);
    },
};
</script>
