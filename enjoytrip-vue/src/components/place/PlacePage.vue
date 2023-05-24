<template lang="">
    <div class="container">
        <!-- kakao map start -->
        <div id="map"></div>
        <!-- kakao map end -->
        <div id="sideBar" class="p-2">
            <!-- <div style="height: 70px"></div> -->
            <section>
                <!-- 중앙 center content end -->
                <div class="col-md-12">
                    <div class="container py-4">
                        <place-section class="col-lg-12"></place-section>
                    </div>

                    <!--  java이용 api 가져오기 start -->
                    <div class="col-md-12">
                        <form class="d-flex my-3">
                            <select
                                id="area1List"
                                class="form-select me-2"
                                v-model="$store.state.placeStore.sidoCode"
                                @change="selectArea2List()"
                            >
                                <option value="0">시도를 선택하세요</option>
                                <option
                                    v-for="(area, index) in areaList1"
                                    :key="index"
                                    v-bind:value="area.sidoCode"
                                >
                                    {{ area.sidoName }}
                                </option>
                            </select>
                            <select
                                id="area2List"
                                class="form-select me-2"
                                v-model="$store.state.placeStore.gugunCode"
                            >
                                <option value="0">구군을 선택하세요</option>
                                <option
                                    v-for="(area, index) in areaList2"
                                    :key="index"
                                    v-bind:value="area.gugunCode"
                                >
                                    {{ area.gugunName }}
                                </option>
                            </select>
                        </form>
                        <form class="d-flex my-3">
                            <input
                                id="search-keyword"
                                class="form-control me-2"
                                v-model="$store.state.placeStore.searchWord"
                                type="search"
                                name="search"
                                placeholder="검색어"
                                aria-label="검색어"
                            />
                            <button
                                id="btnSearch"
                                class="btn btn-primary"
                                type="button"
                                @click="viewList()"
                            >
                                검색
                            </button>
                        </form>
                    </div>
                </div>
            </section>
            <!-- 카드 추가-->
            <div class="row" id="cardFream" v-for="(area, index) in trips" :key="index">
                <div class="col-4">
                    <div class="ratio ratio-4x3 mb-3">
                        <img
                            v-if="area.firstImage == ''"
                            src="@/img/ssafy_logo.png"
                            class="card-img-top"
                            alt="..."
                            style="border-radius: 20px; margin: 5px"
                        />
                        <img
                            v-else
                            :src="area.firstImage"
                            class="card-img-top"
                            alt="..."
                            style="border-radius: 20px; margin: 5px"
                        />
                    </div>
                </div>
                <div class="col-8" id="sideText">
                    <h2
                        class="h4 text-uppercase mb-3"
                        @click="moveMap(area.latitude, area.longitude)"
                    >
                        {{ area.title }}
                    </h2>
                    <div class="row gy-2 mb-4">
                        <div class="col-12">
                            <p class="text-sm text-uppercase mb-0">{{ area.addr1 }}</p>
                        </div>
                        <div class="col-12">
                            <p
                                class="btn btn-outline-primary m-1"
                                @click="tripDetail(area.contentId)"
                            >
                                자세히 보기
                            </p>
                            <p
                                class="btn btn-success m-1"
                                @click="tripNaverSearch(area.title)"
                            >
                                Naver
                            </p>
                            <p
                                class="btn btn-outline-dark btn-sm"
                                v-if="checkIsFavorite(area.contentId)"
                                @click="changeFavoriteState(1, area.contentId)"
                            >
                                💗 👉 🖤
                            </p>
                            <p
                                class="btn btn-outline-light btn-sm"
                                v-else
                                @click="changeFavoriteState(0, area.contentId)"
                            >
                                🖤 👉 💗
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import PlaceSection from "@/components/place/PlaceSection";
import { mapState, mapActions } from "vuex";
import http from "@/common/axios.js";
const placeStore = "placeStore";
const favoriteStore = "favoriteStore";
const loginStore = "loginStore";

export default {
    components: { PlaceSection },
    methods: {
        ...mapActions(favoriteStore, [
            "getHotplaceList",
            "getHotplaceListFromUser",
            "getFavoriteList",
            "postFavorite",
        ]),
        ...mapActions(placeStore, [
            "getList",
            "getArea2List",
            "getTripDetail",
            "moveCenter",
            "mapView",
        ]),
        viewList() {
            this.getList();
        },
        selectArea2List() {
            this.getArea2List();
        },
        moveMap(lat, lng) {
            this.moveCenter({ lat, lng });
        },
        tripDetail(contentId) {
            this.getTripDetail(contentId);
            this.$router.push({ name: "PlaceDetailPage" });
        },
        tripNaverSearch(title) {
            window.open(
                "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" +
                    title
            );
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

                // $nextTick() 사용
                this.$nextTick(async () => {
                    // 담고 난 후, 리스트를 다시 계산해야 함
                    try {
                        await this.getFavoriteList();
                        console.log(this.favoriteList);
                    } catch (error) {
                        console.log(error);
                    }
                });
            } catch (error) {
                console.log(error);
            }
        },

        checkIsFavorite(contentId) {
            let result = this.isFavorite(contentId);
            console.log(contentId + "의 FH 존재결과: ", result);
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
        ...mapState(placeStore, ["areaList1", "areaList2", "trips"]),
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
            console.log("FavoriteHot place 계산");
            return this.trips.filter((trip) =>
                this.favoriteList.some(
                    (favorite) => favorite.contentId === trip.contentId
                )
            );
        },
    },
    async mounted() {
        await this.getHotplaceList();
        await this.getFavoriteList();
        await this.mapView();
    },
};
</script>
<style scoped>
.container {
    width: 100%;
    height: 100%;
    max-width: 100%;
}
#map {
    position: fixed;
    top: 120px;
    width: 100vw;
    height: 87vh;
    z-index: -2;
}
#sideBar {
    position: fixed;
    left: 0px;
    top: 120px;
    width: 430px;
    height: 87vh;
    z-index: -1;
    overflow-y: scroll;
    overflow-x: hidden;
    background-color: lightgray;
    background-color: rgba(255, 255, 255, 0.7);
    text-align: center;
}
form {
    margin: 10px;
}
h1 {
    font-size: 1rem;
}
</style>
