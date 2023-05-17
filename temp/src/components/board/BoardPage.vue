<template>
    <div class="container">
        <h1 class="text-center">BOARD</h1>
        <div class="input-group mb-3">
            <input
                v-model="$store.state.board.searchWord"
                @keydown.enter="boardList"
                type="text"
                class="form-control"
            />
            <button @click="boardList" class="btn btn-success" type="button">Search</button>
        </div>

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
        <board-pagination></board-pagination>

        <button class="btn btn-sm btn-primary" @click="showInsertModal">글쓰기</button>
        <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
        <detail-modal></detail-modal>
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
import BoardPagination from "./BoardPagination.vue";

Vue.use(VueAlertify);

export default {
    name: "BoardMain",
    components: { InsertModal, DetailModal, BoardPagination },

    data() {
        return {
            insertModal: null,
            detailModal: null,
        };
    },
    computed: {
        listGetters() {
            return this.$store.getters["getBoardList"]; // no getBoardList()
        },
    },

    methods: {
        boardList() {
            this.$store.dispatch("boardList");
        },
        movePage(pageIndex) {
            this.$store.commit("SET_BOARD_MOVE_PAGE", pageIndex);
            this.boardList();
        },

        makeDateStr: util.makeDateStr,

        showInsertModal() {
            this.insertModal.show();
        },

        closeAfterInsert() {
            this.insertModal.hide();
            this.boardList();
        },

        async boardDetail(boardId) {
            try {
                let { data } = await http.get("/boards/" + boardId);
                console.log(data);

                if (data.result == "login") {
                    this.doLogout(); // this.$router.push("/login"); 에서 변경
                } else {
                    let { dto } = data;
                    this.$store.commit("SET_BOARD_DETAIL", dto);

                    this.detailModal.show();
                }
            } catch (error) {
                console.log("BoardMainVue: error : ");
                console.log(error);
            }
        },
    },

    created() {
        this.boardList();
    },

    mounted() {
        this.insertModal = new Modal(document.getElementById("insertModal"));
        this.detailModal = new Modal(document.getElementById("detailModal"));
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
