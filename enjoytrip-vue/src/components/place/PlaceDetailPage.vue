<template>
    <div>
        <!-- BRIEF SECTION-->
        <section>
            <div class="container py-4">
                <header class="mb-5">
                    <h2 class="text-uppercase lined mb-4">
                        {{ $store.state.placeStore.title }}
                    </h2>
                </header>
                <div class="row gy-4">
                    <div class="col-8">
                        <div class="swiper-container portfolio-slider">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide mb-5">
                                    <img
                                        v-if="$store.state.placeStore.firstImage == ''"
                                        src="@/img/ssafy_logo.png"
                                        alt="..."
                                    />
                                    <img
                                        v-else
                                        :src="$store.state.placeStore.firstImage"
                                        alt="..."
                                    />
                                </div>
                            </div>
                            <div class="swiper-pagination"></div>
                        </div>
                    </div>
                    <div class="col-4">
                        <h4 class="h5 text-uppercase">주소</h4>
                        <p class="text-muted text-sm">
                            {{ $store.state.placeStore.addr1 }}
                        </p>
                        <h4 class="h5 text-uppercase">Services</h4>
                        <p class="text-muted text-sm">Consulting, Webdesign, Print</p>
                        <h4 class="h5 text-uppercase">Technologies</h4>
                        <p class="text-muted text-sm">PHP, HipHop, Break-dance</p>
                        <h4 class="h5 text-uppercase">Dates</h4>
                        <p class="text-muted text-sm">10/2013 - 06/2014</p>

                        <div>
                            <a id="kakao-link-btn">
                                <img
                                    style="height: 50"
                                    src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
                                />
                                <p
                                    class="btn btn-outline-pink btn-sm"
                                    @click="sendMessage($store.state.placeStore.title)"
                                >
                                    카카오 메시지 보내기
                                </p>
                            </a>
                        </div>

                        <div>
                            <a id="kakaotalk-sharing-btn" @click="shareKakao" href="#">
                                <img
                                    src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
                                    alt="카카오톡 공유 보내기 버튼"
                                />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- DESCRIPTION SECTION        -->
        <section class="pb-5">
            <div class="container">
                <h3 class="text-uppercase lined mb-4">상세설명</h3>
                <p class="text-muted">
                    {{ $store.state.placeStore.overview }}
                </p>
            </div>
        </section>
    </div>
</template>

<script>
export default {
    methods: {
        shareKakao() {
            /* global Kakao */
            if (Kakao.isInitialized()) {
                Kakao.Link.sendDefault({
                    objectType: "feed",
                    content: {
                        title: "딸기 치즈 케익",
                        description: "#케익 #딸기 #삼평동 #카페 #분위기 #소개팅",
                        imageUrl:
                            "http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
                        link: {
                            mobileWebUrl: "https://developers.kakao.com",
                            webUrl: "https://developers.kakao.com",
                        },
                    },
                    social: {
                        likeCount: 286,
                        commentCount: 45,
                        sharedCount: 845,
                    },
                    buttons: [
                        {
                            title: "웹으로 보기",
                            link: {
                                mobileWebUrl: "https://developers.kakao.com",
                                webUrl: "https://developers.kakao.com",
                            },
                        },
                        {
                            title: "앱으로 보기",
                            link: {
                                mobileWebUrl: "https://developers.kakao.com",
                                webUrl: "https://developers.kakao.com",
                            },
                        },
                    ],
                });
            }
        },
    },

    mounted() {
        let script = document.createElement("script");
        script.src = "https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js";
        script.onload = () => {
            Kakao.init("7a4a089032b66490e861f5838b2fa8d8");
        };
        document.head.appendChild(script);
    },
};
</script>
<style scoped></style>
