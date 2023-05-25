<template>
    <div>
        <!-- BRIEF SECTION-->
        <section class="py-4">
            <div class="container py-4">
                <header class="mb-5">
                    <h2
                        class="text-uppercase lined mb-4"
                        style="display: inline; margin-right: 10px"
                    >
                        {{ $store.state.placeStore.title }}
                    </h2>
                    <p
                        v-if="
                            favoriteList.some(
                                (favorite) =>
                                    favorite.contentId ===
                                    $store.state.placeStore.contentId
                            )
                        "
                        class="btn btn-outline-dark"
                        @click="changeFavoriteState(0, $store.state.placeStore.contentId)"
                    >
                        🖤 취소
                    </p>
                    <p
                        v-else
                        class="btn btn-outline-pink"
                        @click="changeFavoriteState(1, $store.state.placeStore.contentId)"
                    >
                        💗 좋아요
                    </p>
                    <p class="text-muted text-sm">
                        {{ $store.state.placeStore.addr1 }}
                    </p>
                </header>
                <div class="row gy-4">
                    <div class="col-8">
                        <div class="swiper-container portfolio-slider">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide" style="padding: 0px">
                                    <img
                                        style="width: 100%"
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
                        </div>
                    </div>
                    <div class="col-4" style="margin: 0px">
                        <h3 class="h5 text-uppercase lined">상세설명</h3>
                        <p class="text-muted">
                            {{ $store.state.placeStore.overview }}
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <!-- DESCRIPTION SECTION        -->
    </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import http from "@/common/axios.js";

const favoriteStore = "favoriteStore";
const loginStore = "loginStore";

export default {
    methods: {
        ...mapActions(favoriteStore, ["getFavoriteList"]),
        async changeFavoriteState(curState, contentId) {
            let params = {
                userSeq: this.userSeq,
                contentId: contentId,
            };

            try {
                let { data } = await http.post(
                    `/users/${this.userSeq}/places/favorites`,
                    params
                );

                if (data.result == 1) {
                    if (curState == 1) {
                        this.$alertify.success("좋아요에 담았습니다");
                    } else if (curState == 0) {
                        this.$alertify.error("좋아요를 취소했습니다");
                    }
                }
            } catch (error) {
                console.log(error);
            }

            // 담고 난 후, 리스트를 다시 계산해야 함
            // console.log("Favorite place 계산");
            try {
                await this.getFavoriteList();
            } catch (error) {
                console.log(error);
            }
        },
    },
    computed: {
        ...mapState(loginStore, ["userSeq"]),
        ...mapState(favoriteStore, ["favoriteList"]),
    },
};
</script>
<style scoped></style>
