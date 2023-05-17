<template>
    <div class="container">
        <h1 class="text-center">BOARD</h1>
        <div class="input-group mb-3">
            <input v-model="$store.state.board.searchWord" @keydown.enter="boardList" type="text" class="form-control" />
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
    </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
import util from "@/common/util.js";

Vue.use(VueAlertify);

export default {
    name: "BoardMain",

    computed: {
        listGetters() {
            console.log("listGETTEST:", this.$store.getters["board/getBoardList"]);
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
    },

    created() {
        this.boardList();
    },
};
</script>
