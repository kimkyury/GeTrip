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
                    <div class="col-lg-4">
                        <!-- Portfolio item-->
                        <div class="box-image-text text-center primary-overlay">
                            <img class="img-fluid" src="img/portfolio-1.jpg" alt="..." />
                            <div
                                class="overlay-content d-flex flex-column justify-content-center p-4"
                            >
                                <h3 class="text-uppercase box-image-text-heading">
                                    Portfolio item
                                </h3>
                                <p class="text-white box-image-text-description">
                                    Pellentesque habitant morbi tristique senectus et netus et
                                    malesuada
                                </p>
                                <ul class="list-inline mb-0 box-image-text-content">
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">View</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">Website</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <!-- Portfolio item-->
                        <div class="box-image-text text-center primary-overlay">
                            <img class="img-fluid" src="img/portfolio-2.jpg" alt="..." />
                            <div
                                class="overlay-content d-flex flex-column justify-content-center p-4"
                            >
                                <h3 class="text-uppercase box-image-text-heading">
                                    Portfolio item
                                </h3>
                                <p class="text-white box-image-text-description">
                                    Pellentesque habitant morbi tristique senectus et netus et
                                    malesuada
                                </p>
                                <ul class="list-inline mb-0 box-image-text-content">
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">View</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">Website</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <!-- Portfolio item-->
                        <div class="box-image-text text-center primary-overlay">
                            <img class="img-fluid" src="img/portfolio-3.jpg" alt="..." />
                            <div
                                class="overlay-content d-flex flex-column justify-content-center p-4"
                            >
                                <h3 class="text-uppercase box-image-text-heading">
                                    Portfolio item
                                </h3>
                                <p class="text-white box-image-text-description">
                                    Pellentesque habitant morbi tristique senectus et netus et
                                    malesuada
                                </p>
                                <ul class="list-inline mb-0 box-image-text-content">
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">View</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="btn btn-outline-light" href="#">Website</a>
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

export default {
    components: { PlaceSection },
};
</script>
