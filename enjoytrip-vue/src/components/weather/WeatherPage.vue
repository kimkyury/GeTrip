<template lang="">
    <!-- <div style="height: 70px"></div> -->
    <div>
        <section class="py-5">
            <!-- 중앙 center content end -->
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>Weather Info</h1>
                        <p class="lead mb-5">Weather정보를 알려줄게요</p>
                    </div>
                    <weather-section></weather-section>
                </div>
            </div>
        </section>
        <section class="py-5">
            <div class="container py-4">
                <div class="row gy-4"></div>
            </div>
        </section>
    </div>
</template>
<script>
import axios from "axios";

export default {
    methods: {
        async callApi() {
            let apiKey =
                "qc89BNAu4C3d9bNk1FPnjeT3lBTmEUCL94fFDctEQ7S52gtr8NM4n%2FvafW84%2BTVun8hAjAl0QArat%2FovlxUPww%3D%3D";
            let dataType = "json";
            let date = "20230525";
            let time = "0700";
            let nx = "55";
            let ny = "127";

            let url = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst
                        ?serviceKey=${apiKey}
                        $dataType=${dataType}
                        &numOfRows=10
                        &pageNo=1
                        &base_date=${date}
                        &base_time=${time}
                        &nx=${nx}&ny=${ny}
                    `;

            let { data } = axios.get(url + this.boardId);
            console.log(data);
        },
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
