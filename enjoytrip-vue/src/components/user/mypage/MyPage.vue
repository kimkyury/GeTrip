<template>
    <div>
        <section class="py-4">
            <div class="container py-4">
                <div class="row g-5">
                    <div class="col-lg-9">
                        <h1>Confirm My Info</h1>
                        <p class="lead">당신의 정보를 열람할 수 있습니다.</p>
                    </div>
                    <member-section></member-section>
                </div>
                <div class="row">
                    <div class="col-lg-7">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-12 mb-4">
                                    <h3 class="text-uppercase lined">당신의 계정정보</h3>
                                </div>
                                <label class="form-label" for="firstname"> 이름</label>
                                <label class="form-control" id="firstname" type="text">
                                    {{ $store.state.loginStore.userName }}</label
                                >
                            </div>
                            <div class="col-md-12 mb-3">
                                <label class="form-label" for="firstname"> 이메일 </label>
                                <label class="form-control" id="firstname" type="text">
                                    {{ $store.state.loginStore.userEmail }}</label
                                >
                            </div>

                            <div class="col-12 mb-4 mt-5">
                                <h3 class="text-uppercase lined">당신의 프로필정보</h3>
                            </div>

                            <div class="row">
                                <div
                                    v-for="(image, index) in $store.state.loginStore
                                        .profileImage"
                                    :key="index"
                                    style="padding: 0px; width: 0px, margin:10px;"
                                    id="firstname"
                                >
                                    <img
                                        v-if="
                                            image.path ==
                                            $store.state.loginStore.userProfileImageUrl
                                        "
                                        :src="image.imageView"
                                        style="width: 100px; height: 100px"
                                    />
                                </div>
                            </div>
                            <!-- <div class="col-md-6 mb-3">
                <label class="form-label" for="firstname"> 이미지 </label>
                <label class="form-control" id="firstname" type="text">
                  <img :src="getProfileImageUrl" />
                </label>
              </div> -->

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label" for="firstname">
                                        성별
                                    </label>
                                    <label
                                        class="form-control"
                                        id="firstname"
                                        type="text"
                                    >
                                        {{
                                            $store.state.loginStore.userGenderName
                                        }}</label
                                    >
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="form-label" for="firstname">
                                        생일
                                    </label>
                                    <label
                                        class="form-control"
                                        id="firstname"
                                        type="text"
                                    >
                                        {{ $store.state.loginStore.userBirthYear }}.
                                        {{ $store.state.loginStore.userBirthMonth }}.
                                        {{ $store.state.loginStore.userBirthDay }}.
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-12 mb-3">
                                <label class="form-label" for="firstname">
                                    사는 곳
                                </label>
                                <label class="form-control" id="firstname" type="text">
                                    {{ $store.state.loginStore.userSidoName }}
                                    {{ $store.state.loginStore.userGugunName }}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import MemberSection from "./MemberSection.vue";

export default {
    data() {
        return {
            profileImageUrl: "/img/animal/dog.png",
        };
    },
    components: { MemberSection },

    computed: {
        getProfileImageUrl() {
            let profilePath = this.$store.state.loginStore.userProfileImageUrl;
            if (this.$store.state.loginStore.userProfileImageUrl == "") {
                profilePath = this.profileImageUrl;
            } else {
                profilePath = this.$store.state.loginStore.userProfileImageUrl;
            }

            console.log(profilePath);

            return profilePath;
        },
    },
};
</script>
