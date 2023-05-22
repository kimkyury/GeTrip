<template>
    <nav class="mb-5" aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <!-- <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous"
                    ><span aria-hidden="true">«</span></a
                >
            </li> -->

            <li v-if="prev" class="page-item">
                <a
                    class="page-link"
                    href="#"
                    aria-label="Previous"
                    @click="paginationChanged(startPageIndex - 1)"
                >
                    <span aria-hidden="true">«</span>
                </a>
            </li>

            <!-- v-bind:class의 의미: 해당 조건에 성립할떄 'active'라는 class 적용-->
            <li
                v-for="index in endPageIndex - startPageIndex + 1"
                :key="index"
                v-bind:class="{
                    active: startPageIndex + index - 1 == currentPageIndex,
                }"
                class="page-item"
            >
                <a
                    @click="paginationChanged(startPageIndex + index - 1)"
                    class="page-link"
                    href="#"
                >
                    {{ startPageIndex + index - 1 }}</a
                >
            </li>
            <li v-if="next" class="page-item">
                <a
                    class="page-link"
                    href="#"
                    aria-label="Next"
                    @click="paginationChanged(endPageIndex + 1)"
                >
                    <span aria-hidden="true">»</span>
                </a>
            </li>
        </ul>
    </nav>
</template>

<script>
import { mapState } from "vuex";
import { mapGetters } from "vuex";

export default {
    computed: {
        ...mapGetters("boardStore", {
            pageCount: "getPageCount",
            startPageIndex: "getStartPageIndex",
            endPageIndex: "getEndPageIndex",
            prev: "getPrev",
            next: "getNext",
        }),

        ...mapState("boardStore", ["currentPageIndex"]),
    },

    mounted() {
        console.log("pCur", this.currentPageIndex);
        console.log("pCnt", this.pageCount);
        console.log("start", this.startPageIndex);
        console.log("end", this.endPageIndex);

        console.log("prev", this.prev);
        console.log("next", this.next);
    },
    methods: {
        paginationChanged(pageIndex) {
            console.log("paginationChanged: " + pageIndex);
            this.$emit("call-parent", pageIndex);
        },
    },
};
</script>
