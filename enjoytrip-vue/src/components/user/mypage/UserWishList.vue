<template>
    <section class="py-5">
        <div class="container py-4">
            <div class="row g-5">
                <div class="col-lg-9">
                    <h1>My Favorite Attraction</h1>
                    <p class="lead mb-5">당신이 찜한 여행지 목록입니다.</p>
                    <div class="row gy-5 align-items-stretch">
                        <div
                            class="col-lg-3 col-md-6"
                            v-for="(favorite, index) in favoriteList"
                            :key="index"
                        >
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a @click="tripDetail(favorite.contentId)">
                                        <img
                                            style="height: 250px"
                                            v-if="favorite.firstImage == ''"
                                            class="img-fluid"
                                            src="@/assets/img/enjoytrip/unfind.png"
                                            alt="..."
                                        />
                                        <img
                                            style="height: 250px"
                                            v-else
                                            :src="favorite.firstImage"
                                            class="img-fluid"
                                            alt="..."
                                        />
                                    </a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        {{ favorite.title }}
                                    </h3>
                                    <p class="mb-0">{{ favorite.addr1 }}</p>
                                    <ul class="list-inline mb-0">
                                        <li class="list-inline-item">
                                            <a
                                                class="btn btn-outline-dark"
                                                v-if="checkIsFavorite(favorite.contentId)"
                                                @click="
                                                    changeFavoriteState(
                                                        1,
                                                        favorite.contentId
                                                    )
                                                "
                                                >🖤 좋아요 취소</a
                                            >
                                        </li>
                                    </ul>
                                </div>

                                <ul class="list-unstyled p-0 ribbon-holder mb-0"></ul>
                            </div>
                        </div>
                    </div>
                </div>

                <member-section></member-section>
            </div>
        </div>
    </section>
</template>
<script>
import MemberSection from "./MemberSection.vue";
import { mapState, mapActions } from "vuex";
import http from "@/common/axios.js";
const favoriteStore = "favoriteStore";
const placeStore = "placeStore";
const loginStore = "loginStore";

export default {
    components: { MemberSection },
    methods: {
        ...mapActions(placeStore, ["getTripDetail"]),
        ...mapActions(favoriteStore, ["getFavoriteList", "postFavorite"]),

        tripDetail(contentId) {
            this.getTripDetail(contentId);
            this.$router.push({ name: "PlaceDetailPage" });
        },
        async changeFavoriteState(curState, contentId) {
            let params = {
                userSeq: this.userSeq,
                contentId: contentId,
            };

            // 비동기로 작동하는 alertify를 동기로 작동하도록 바꿈
            const select = await new Promise((resolve) => {
                this.$alertify.confirm(
                    "좋아요 목록에서 제거됩니다. 제거하시겠습니까?",
                    () => {
                        // this.$alertify.success("좋아요를 취소했습니다.");
                        resolve(1);
                    },
                    () => {
                        this.$alertify.success("휴, 살아남았네요!");
                        resolve(0);
                    }
                );
            });

            if (select == 1) {
                try {
                    let { data } = await http.post(
                        `/users/${this.userSeq}/places/favorites`,
                        params
                    );

                    if (data.result == 1) {
                        if (curState == 0) {
                            this.$alertify.success("좋아요에 다시 담았습니다");
                        } else if (curState == 1) {
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
            }
        },

        checkIsFavorite(contentId) {
            let result = this.isFavorite(contentId);
            // console.log(contentId + "의 FH 존재결과: ", result);
            return result;
        },
        isFavorite(contentId) {
            return this.favoriteList.some((favorite) => favorite.contentId === contentId);
        },
    },
    computed: {
        ...mapState(loginStore, ["userSeq", "userName", "userSidoName"]),
        ...mapState(favoriteStore, ["favoriteList", "favoriteListCount"]),
    },

    created() {},

    async mounted() {
        await this.getFavoriteList();
        console.log("f List: ", this.favoriteList);
    },
};
</script>
