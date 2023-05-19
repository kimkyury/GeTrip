<template lang="">
    <div class="container">
        <!-- kakao map start -->
        <div id="map"></div>
        <!-- kakao map end -->
        <div id="sideBar">
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
                        </div>
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
            <!-- <div class="row gy-4 mb-5">
                <div class="col-lg-4">
                  <div class="ratio ratio-4x3 mb-3">
                    <iframe src="//www.youtube.com/embed/upZJpGrppJA" title="YouTube video" allowfullscreen></iframe>
                  </div>
                </div>
                <div class="col-lg-8">
                  <h2 class="h3 text-uppercase mb-3"><a class="text-dark" href="post.html">Who is who - example blog post</a></h2>
                  <div class="row gy-2 mb-4">
                    <div class="col-xl-6">
                      <p class="text-sm text-uppercase mb-0 fw-light text-muted">By <a class="fw-normal" href="#">John Snow</a> in <a class="fw-normal" href="blog.html">Webdesign                  </a></p>
                    </div>
                    <div class="col-xl-6">
                      <p class="text-xl-end text-sm mb-0 text-muted"><a class="text-muted me-3" href="blog-post.html"><i class="far fa-calendar me-1"></i> June 20, 2020</a><a class="text-muted" href="blog-post.html"><i class="far fa-comment me-1"></i> 12 Comments</a></p>
                    </div>
                  </div>
                  <p class="text-sm text-gray-700 mb-3">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
                  <p class="text-end"><a class="btn btn-outline-primary" href="blog-post.html">Continue reading</a></p>
                </div>
            </div> -->
            <div class="row gy-5 mb-5">
                <div v-for="(area, index) in trips" :key="index">
                    <div class="box-image">
                        <div class="mb-4 primary-overlay">
                            <img
                                v-if="area.firstImage == ''"
                                src="@/img/ssafy_logo.png"
                                class="card-img-top"
                                style="height: 15rem; width:100%"
                                alt="..."
                            />
                            <img
                                v-else
                                :src="area.firstImage"
                                class="card-img-top"
                                style="height: 15rem; width:100%;"
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
    </div>
</template>
<script>
import PlaceSection from "@/components/place/PlaceSection";
import { mapState, mapActions } from "vuex";

const placeStore = "placeStore";

export default {
  components: { PlaceSection },
  methods: {
    ...mapActions(placeStore, ["getList", "getArea2List", "mapView"]),
    viewList() {
      this.getList();
    },
    selectArea2List() {
      this.getArea2List();
    },
  },
  computed: {
    ...mapState(placeStore, ["areaList1", "areaList2", "trips"]),
  },
  async mounted() {
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
  width: 420px;
  height: 87vh;
  z-index: -1;
  overflow-y: scroll;
  overflow-x:hidden;
  background-color: lightgray;
  text-align:center;
}
form{
    margin: 10px;
}
h1 {
  font-size: 1rem;
}
</style>
