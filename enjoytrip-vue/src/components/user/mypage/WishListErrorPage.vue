<template>
    <section class="py-5">
        <div class="container py-4">
            <div class="row g-5">
                <div class="col-lg-9">
                    <h1>My Favorite Attraction</h1>
                    <p class="lead mb-5">당신이 좋아요한 여행지 목록입니다.</p>
                </div>
                <member-section></member-section>
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
                    <p class="centered-text">좋아요한 장소가 없네요! 추가해볼까요?</p>
                </div>
            </div>
        </div>
    </section>
</template>
<script>
import MemberSection from "./MemberSection.vue";
export default {
    data() {
        return {
            tungText: "텅",
        };
    },
    components: { MemberSection },
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
</style>
