<template>
    <section class="py-5">
        <div class="container py-4">
            <div class="row g-5">
                <div class="col-lg-9">
                    <h1>My Favorite Attraction</h1>
                    <p class="lead mb-5">당신이 좋아요한 여행지 목록입니다.</p>
                    <div class="row gy-5 align-items-stretch">
                        <h3>텅~</h3>
                    </div>
                </div>

                <member-section></member-section>
            </div>
        </div>
    </section>
</template>
<script>
import MemberSection from "./MemberSection.vue";
export default {
    components: { MemberSection },
};
</script>
<style scoped></style>
