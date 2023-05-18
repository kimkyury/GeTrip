<template>
    <div>
        <section class="py-5">
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>Confirm My Info</h1>
                        <p class="lead mb-5">당신의 정보를 열람할 수 있습니다.</p>
                    </div>
                    <member-section></member-section>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import MemberSection from "./MemberSection.vue";

export default {
    components: { MemberSection },
};
</script>
