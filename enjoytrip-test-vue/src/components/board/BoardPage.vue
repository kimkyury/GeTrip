<template>
    <div>
        <section class="bg-pentagon py-4">
            <div class="container py-3">
                <div class="row d-flex align-items-center gy-4">
                    <div class="col-md-7">
                        <h1 class="h2 mb-0 text-uppercase">ENJOYTRIP 자유게시판</h1>
                    </div>
                </div>
            </div>
        </section>
        <div class="container">
            <br />
            <h3 class="h4 lined text-uppercase mb-4">Search</h3>
            <!-- <div class="input-group mb-3">
                <input
                    v-model="$store.state.board.searchWord"
                    @keydown.enter="boardList"
                    type="search"
                    aria-label="search"
                    placeholder="제목을 검색하세요"
                    class="form-control"
                />
                <button @click="boardList" class="btn btn-primary" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div> -->
            <br /><br />
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일시</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        style="cursor: pointer"
                        v-for="(board, index) in listGetters"
                        @click="boardDetail(board.boardId)"
                        v-bind:key="index"
                    >
                        <td>{{ board.boardId }}</td>
                        <td>{{ board.title }}</td>
                        <td>{{ board.userName }}</td>
                        <td>{{ board.regDt.date | makeDateStr(".") }}</td>
                        <td>{{ board.readCount }}</td>
                    </tr>
                </tbody>
            </table>

            <div class="col-lg-12 text-center">
                <board-pagination v-on:call-parent="movePage"></board-pagination>
            </div>

            <button class="btn btn-sm btn-primary" @click="showInsertModal">
                글쓰기
            </button>
            <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
            <detail-modal
                v-on:call-parent-change-to-update="changeToUpdate"
                v-on:call-parent-change-to-delete="changeToDelete"
            ></detail-modal>
            <update-modal v-on:call-parent-update="closeAfterUpdate"></update-modal>
        </div>
    </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
import http from "@/common/axios.js";
import util from "@/common/util.js";
import { Modal } from "bootstrap";

import DetailModal from "./modals/DetailModal";
import InsertModal from "./modals/InsertModal";
import UpdateModal from "./modals/UpdateModal";
import BoardPagination from "./BoardPagination.vue";

import { mapMutations, mapGetters, mapActions, mapState } from "vuex";

Vue.use(VueAlertify);

export default {
    name: "BoardMain",
    components: { InsertModal, DetailModal, BoardPagination, UpdateModal },

    data() {
        return {
            insertModal: null,
            detailModal: null,
            updateModal: null,
        };
    },
    computed: {
        ...mapState("boardStore", ["boardId"]),
        ...mapGetters("boardStore", ["getBoardList"]),
        listGetters() {
            return this.getBoardList; // no getBoardList()
        },
    },

    methods: {
        ...mapActions("boardStore", ["boardList"]),
        ...mapMutations("boardStore", [
            "SET_BOARD_MOVE_PAGE",
            "SET_BOARD_DETAIL",
            // "boardStore", "SET_BOARD_MOVE_PAGE, "pageIndex"
        ]),

        makeDateStr: util.makeDateStr,

        showInsertModal() {
            this.insertModal.show();
        },

        closeAfterInsert() {
            this.insertModal.hide();
            this.boardList();
        },

        changeToUpdate() {
            this.detailModal.hide();
            this.updateModal.show();
        },
        closeAfterUpdate() {
            this.updateModal.hide();
            this.boardList();
        },

        changeToDelete() {
            this.detailModal.hide();

            var $this = this;
            this.$alertify.confirmWithTitle(
                "삭제 확인",
                "이 글을 삭제하시겠습니까?",
                function () {
                    // board.boardId 사용 X
                    $this.boardDelete(); // $this 사용
                },
                function () {
                    console.log("cancel");
                }
            );
        },

        async boardDetail(boardId) {
            console.log("boardId", boardId);
            try {
                let { data } = await http.get("/boards/" + boardId);
                console.log(data);

                if (data.result == "login") {
                    this.doLogout(); // this.$router.push("/login"); 에서 변경
                } else {
                    let { dto } = data;

                    // 여기에서 boardId가 클릭한 것으로 지정됨
                    this.SET_BOARD_DETAIL(dto);
                    this.detailModal.show();
                }
            } catch (error) {
                console.log("BoardMainVue: error : ");
                console.log(error);
            }
        },

        async boardDelete() {
            try {
                let { data } = await http.delete("/boards/" + this.boardId);
                console.log(data);

                if (data.result == "login") {
                    this.doLogout();
                } else {
                    this.$alertify.success("글 삭제 완료");
                    this.boardList();
                }
            } catch (error) {
                console.log("------DELETE ERROR ---------");
                console.log(error);
            }
        },

        movePage(pageIndex) {
            this.SET_BOARD_MOVE_PAGE(pageIndex);
            this.boardList();
        },
    },

    created() {
        this.boardList();
    },

    mounted() {
        this.insertModal = new Modal(document.getElementById("insertModal"));
        this.detailModal = new Modal(document.getElementById("detailModal"));
        this.updateModal = new Modal(document.getElementById("updateModal"));
    },

    filters: {
        makeDateStr: function (date, separator) {
            return (
                date.year +
                separator +
                (date.month < 10 ? "0" + date.month : date.month) +
                separator +
                (date.day < 10 ? "0" + date.day : date.day)
            );
        },
    },
};
</script>
<style scope>
.center-align {
    margin: auto;
}
</style>
