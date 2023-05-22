import Vue from "vue";
import Vuex from "vuex";
import http from "@/common/axios.js";
import util from "@/common/util.js";
import routers from "@/routers/routers";

import placeStore from "@/store/modules/placeStore";
import loginStore from "@/store/modules/loginStore";
import boardStore from "@/store/modules/boardStore";

Vue.use(Vuex);
export default new Vuex.Store({
    modules: {
        boardStore,
        placeStore,
        loginStore,
    },
    state: {
        board: {
            list: [],
            limit: 10,
            offset: 0,
            searchWord: "",

            // for pagination
            listRowCount: 10,
            pageLinkCount: 10,
            currentPageIndex: 1,

            totalListItemCount: 0,

            // for Detail, Update, Delete
            boardId: 0,
            title: "",
            content: "",
            userName: "",
            regDate: "",
            regTime: "",
            readCount: 0,
            sameUser: false,
        },
    },
    mutations: {
        // Board
        SET_BOARD_LIST(state, list) {
            state.board.list = list;
        },
        SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.board.totalListItemCount = count;
        },
        SET_BOARD_MOVE_PAGE(state, pageIndex) {
            state.board.offset = (pageIndex - 1) * state.board.listRowCount;
            state.board.currentPageIndex = pageIndex;
        },
        SET_BOARD_DETAIL(state, payload) {
            state.board.boardId = payload.boardId;
            state.board.title = payload.title;
            state.board.content = payload.content;
            state.board.userName = payload.userName;
            state.board.regDate = util.makeDateStr(
                payload.regDt.date.year,
                payload.regDt.date.month,
                payload.regDt.date.day,
                "."
            );
            state.board.regTime = util.makeTimeStr(
                payload.regDt.time.hour,
                payload.regDt.time.minute,
                payload.regDt.time.second,
                ":"
            );
            state.board.readCount = payload.readCount;
            state.board.sameUser = payload.sameUser;
        },

        SET_BOARD_TITLE(state, title) {
            state.board.title = title;
        },

        SET_BOARD_SEARCH_WORD(state, searchWord) {
            state.board.searchWord = searchWord;
        },
    },
    actions: {
        // Board
        async boardList({ state, commit }) {
            let params = {
                limit: state.board.limit,
                offset: state.board.offset,
                searchWord: state.board.searchWord,
            };

            try {
                let { data } = await http.get("/boards", { params });
                console.log(data);

                if (data.result == "login") {
                    routers.push("/login");
                } else {
                    commit("SET_BOARD_LIST", data.list);
                    commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    getters: {
        getBoardList: function (state) {
            return state.board.list;
        },

        getPageCount: function (state) {
            return Math.ceil(state.board.totalListItemCount / state.board.listRowCount);
        },
        getStartPageIndex: function (state) {
            if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
                //10, 20...맨마지막
                return (
                    (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
                        state.board.pageLinkCount +
                    1
                );
            } else {
                return (
                    Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
                        state.board.pageLinkCount +
                    1
                );
            }
        },
        getEndPageIndex: function (state, getters) {
            let ret = 0;
            if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
                //10, 20...맨마지막
                ret =
                    (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
                        state.board.pageLinkCount +
                    state.board.pageLinkCount;
            } else {
                ret =
                    Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
                        state.board.pageLinkCount +
                    state.board.pageLinkCount;
            }
            // 위 오류나는 코드를 아래와 같이 비교해서 처리
            return ret > getters.getPageCount ? getters.getPageCount : ret;
        },
        getPrev: function (state) {
            if (state.board.currentPageIndex <= state.board.pageLinkCount) {
                return false;
            } else {
                return true;
            }
        },
        getNext: function (state, getters) {
            if (
                Math.floor(getters.getPageCount / state.board.pageLinkCount) *
                    state.board.pageLinkCount <
                state.board.currentPageIndex
            ) {
                return false;
            } else {
                return true;
            }
        },
    },
});
