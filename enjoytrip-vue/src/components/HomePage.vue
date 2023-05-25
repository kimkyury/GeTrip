<template>
    <section
        class="text-white bg-cover bg-center primary-overlay overlay-dense"
        style="background: url('img/photogrid.jpg') repeat"
    >
        <div class="overlay-content py-5" style="height: 80vh">
            <div class="container py-4">
                <!-- Hero slider-->
                <div class="swiper-container homepage-slider">
                    <br />
                    <div class="swiper-wrapper">
                        <!-- Hero Slide-->
                        <div class="swiper-slide h-auto mb-5">
                            <div class="row gy-5 h-100 align-items-center">
                                <div class="col-lg-5 text-lg-end">
                                    <h1 class="">STARTrip !</h1>
                                </div>
                                <div class="col-lg-7">
                                    <img
                                        class="ml-auto img-fluid"
                                        src="@/assets/img/enjoytrip/trip.png"
                                        alt=""
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide h-auto mb-5">
                            <div class="row gy-5 h-100 align-items-center">
                                <div class="col-lg-5 text-lg-end">
                                    <h1 class="">STARTrip Developer</h1>

                                    <ul class="list-unstyled text-uppercase fw-bold mb-0">
                                        <li>PM. 이상훈</li>
                                        <li class="mb-2">PL. 김규리</li>
                                        <li></li>
                                        <li>SSAFY. 부울경. 3반. 10조.</li>
                                    </ul>
                                </div>
                                <div class="col-lg-7">
                                    <img
                                        class="img-fluid"
                                        src="img/template-homepage.png"
                                        alt=""
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
<script>
import Swiper from "swiper";
import "swiper/swiper-bundle.css";

export default {
    mounted() {
        new Swiper(".swiper-container", {
            // Add your options here
            // For example, enable navigation and pagination
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
        });
    },
};
</script>

<style>
.swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
