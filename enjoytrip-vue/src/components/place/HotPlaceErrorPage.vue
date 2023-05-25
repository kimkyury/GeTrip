<template>
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
                                    class="custom-item2"
                                    :style="{ animationDelay: nameIndex * 200 + 'ms' }"
                                    v-text="nameChar"
                                />
                                "님의 지역, "

                                <span
                                    v-for="(sidoChar, sidoIndex) in userSidoName"
                                    :key="'sido-' + sidoIndex"
                                    class="custom-item2"
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
                                    class="custom-item2"
                                    :style="{ animationDelay: guideIndex * 200 + 'ms' }"
                                    v-text="guideChar"
                                />
                                을 하시면, 당신 지역의

                                <span
                                    v-for="(guideChar, guideIndex) in guideText2"
                                    :key="'guide2-' + guideIndex"
                                    class="custom-item2"
                                    :style="{ animationDelay: guideIndex * 200 + 'ms' }"
                                    v-text="guideChar"
                                />
                                한 관광지를 알려줄게요
                            </p>
                        </div>
                    </div>
                    <place-section></place-section>
                    <br />
                    <div class="centered-text row gy-5 align-items-stretch">
                        <p class="lead mb-5 centered-text">
                            <span
                                v-for="(t, textIndex) in tungText"
                                :key="'tungText-' + textIndex"
                                class="custom-item"
                                :style="{ animationDelay: textIndex * 200 + 'ms' }"
                                v-text="t"
                            />
                        </p>
                        <p class="centered-text">
                            헉, 아직 조사가 부족한 지역이에요! 잠시만 기다려주세요.
                        </p>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import PlaceSection from "./PlaceSection.vue";
import { mapState } from "vuex";
export default {
    data() {
        return {
            guideText1: "회원가입",
            guideText2: "HOT!",
            tungText: "텅",
        };
    },
    computed: {
        ...mapState("loginStore", ["isLogin", "userName", "userSidoName"]),
    },
    components: { PlaceSection },
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
    font-size: 5rem;
    animation: text-in 0.8s cubic-bezier(0.22, 0.15, 0.25, 1.43) 0s backwards;
}
.centered-text {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
}

.custom-item2 {
    display: inline-block;
    min-width: 0.3em;
    font-size: 1.5rem;
    animation: text-in 0.8s cubic-bezier(0.22, 0.15, 0.25, 1.43) 0s backwards;
}
</style>
