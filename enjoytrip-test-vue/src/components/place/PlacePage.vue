<template lang="">
    <div class="container">
        <!-- <div style="height: 70px"></div> -->
        <section class="py-5">
            <!-- 중앙 center content end -->
            <div class="col-md-12">
                <div class="container py-4">
                    <div class="row g-5">
                        <div class="col-lg-9">
                            <h1>Search Attraction</h1>
                            <p class="lead">관광지를 검색해보아요</p>
                        </div>
                        <place-section></place-section>
                        <br />
                    </div>
                </div>

                <!--  java이용 api 가져오기 start -->
                <div class="col-md-12">
                    <form class="d-flex my-3">
                        <select
                            id="area1List"
                            class="form-select me-2"
                            v-model="sidoCode"
                            @change="getArea2List()"
                        >
                            <option value="">시도를 선택하세요</option>
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
                            v-model="gugunCode"
                            @change="getCat1List()"
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
                        <input
                            id="search-keyword"
                            class="form-control me-2"
                            type="search"
                            name="search"
                            placeholder="검색어"
                            aria-label="검색어"
                        />
                        <button
                            id="btnSearch"
                            class="btn btn-outline-success"
                            type="button"
                            @click="searchButton()"
                        >
                            검색
                        </button>
                    </form>
                </div>
                <!-- kakao map start -->
                <div id="map" class="my-3" style="width: 100%; height: 400px"></div>
                <!-- kakao map end -->
            </div>
        </section>
        <!-- 카드 추가-->
        <div class="row gy-5 mb-5">
            <div class="col-lg-4 col-md-6" v-for="(area, index) in trips" :key="index">
                <div class="box-image">
                    <div class="mb-4 primary-overlay">
                        <img
                            v-if="area.firstImage == ''"
                            src="@/img/ssafy_logo.png"
                            class="card-img-top"
                            style="height: 20rem"
                            alt="..."
                        />
                        <img
                            v-else
                            :src="area.firstImage"
                            class="card-img-top"
                            style="height: 20rem"
                            alt="..."
                        />
                        <div
                            class="overlay-content d-flex flex-column justify-content-center p-4"
                        ></div>
                    </div>
                    <div class="text-center">
                        <h3 class="h4 text-uppercase text-primary">
                            <a class="text-reset" href="blog-post.html">{{ area.title }} </a>
                        </h3>
                        <p class="small text-uppercase text-muted">
                            {{ area.addr1 }}
                        </p>
                        <a href="#" class="btn btn-primary">상세보기</a>
                        <a class="btn btn-outline-primary" href="blog-post.html"> 자세히 보기 </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import http from "@/common/axios.js";
import PlaceSection from "@/components/place/PlaceSection";

export default {
    components: { PlaceSection },
    data() {
        return {
            limit: 10,
            offset: 0,
            sidoCode: "",
            gugunCode: "0",
            searchWord: "",
            areaList1: [],
            areaList2: [],
            trips: [],
            positions: [],
            markers: [],
        };
    },
    methods: {
        makeCard(data) {
            this.positions = [];
            this.trips = data.list;
            this.trips.forEach((area) => {
                let markerInfo = {
                    title: area.title,
                    latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude),
                };
                this.positions.push(markerInfo);
            });
            this.displayMarker();
        },
        async displayMarker() {
            for (let i = 0; i < this.markers.length; i++) {
                this.markers[i].setMap(null);
            }
            this.markers = [];
            console.log(this.markers);
            // 마커 이미지의 이미지 주소입니다
            let imageSrc =
                "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            for (let i = 0; i < this.positions.length; i++) {
                // 마커 이미지의 이미지 크기 입니다
                let imageSize = new window.kakao.maps.Size(24, 35);
                // 마커 이미지를 생성합니다
                let markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

                // 마커를 생성합니다
                let marker = new window.kakao.maps.Marker({
                    map: this.map, // 마커를 표시할 지도
                    position: this.positions[i].latlng, // 마커를 표시할 위치
                    title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image: markerImage, // 마커 이미지
                });
                this.markers.push(marker);
            }
            // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다

            this.map.setCenter(this.positions[0].latlng);
        },
        moveCenter(lat, lng) {
            this.map.setCenter(new window.kakao.maps.LatLng(lat, lng));
        },
        searchButton() {
            this.sigungCode = document.querySelector("#area2List").value;
            this.searchWord = document.querySelector("#search-keyword").value;
            this.getList();
        },
        async getList() {
            let $this = this;
            let params = {
                limit: this.limit,
                offset: this.offset,
                sidoCode: this.sidoCode,
                gugunCode: this.gugunCode,
                searchWord: this.searchWord,
            };

            let response = await http.get("/trips", { params });

            let { data } = response;
            console.log(data);

            $this.makeCard(data);
        },
        async getArea1List() {
            let { data } = await http.get("/codes");
            this.areaList1 = data.list;
        },
        async getArea2List() {
            this.gugunCode = "0";
            let response = await http.get("/codes/" + this.sidoCode);
            let { data } = response;
            this.areaList2 = data.list;
        },
    },
    mounted() {
        const script = document.createElement("script");
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=20d8b1fb60682e2256b9d4e10ea6a783&libraries=services,clusterer,drawing`;

        /*global kakao*/
        script.addEventListener("load", () => {
            // 스크립트 로딩이 완료되었을 때 실행되는 코드
            // Kakao 지도 API를 사용하는 코드를 이곳에 작성합니다.
            kakao.maps.load(async () => {
                // 카카오맵 API 로드 완료 후 실행할 코드
                // 지도 생성, 마커 추가 등을 이곳에 작성하면 됩니다.
                console.log("loaded", kakao);

                let mapContainer = document.getElementById("map"), // 지도를 표시할 div
                    mapOption = {
                        center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
                        level: 5, // 지도의 확대 레벨
                    };

                // // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
                this.map = new window.kakao.maps.Map(mapContainer, mapOption);

                await this.getArea1List();
                await this.getList();
            });
        });
        document.head.appendChild(script);
    },
};
</script>
<style lang=""></style>
