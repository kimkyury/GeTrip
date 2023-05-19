import http from "@/common/axios.js";
import util from "@/common/util.js";
import routers from "@/routers/routers";

const boardStore = {
    namespaced : true,
    state: {
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
    getters: {
        getBoardList: function (state) {
            return state.list;
        },

        getPageCount: function (state) {
            return Math.ceil(state.totalListItemCount / state.listRowCount);
        },
        getStartPageIndex: function (state) {
            if (state.currentPageIndex % state.pageLinkCount == 0) {
                //10, 20...맨마지막
                return (
                    (state.currentPageIndex / state.pageLinkCount - 1) *
                    state.pageLinkCount +
                    1
                );
            } else {
                return (
                    Math.floor(state.currentPageIndex / state.pageLinkCount) *
                    state.pageLinkCount +
                    1
                );
            }
        },
        getEndPageIndex: function (state, getters) {
            let ret = 0;
            if (state.currentPageIndex % state.pageLinkCount == 0) {
                //10, 20...맨마지막
                ret =
                    (state.currentPageIndex / state.pageLinkCount - 1) *
                    state.pageLinkCount +
                    state.pageLinkCount;
            } else {
                ret =
                    Math.floor(state.currentPageIndex / state.pageLinkCount) *
                    state.pageLinkCount +
                    state.pageLinkCount;
            }
            // 위 오류나는 코드를 아래와 같이 비교해서 처리
            return ret > getters.getPageCount ? getters.getPageCount : ret;
        },
        getPrev: function (state) {
            if (state.currentPageIndex <= state.pageLinkCount) {
                return false;
            } else {
                return true;
            }
        },
        getNext: function (state, getters) {
            if (
                Math.floor(getters.getPageCount / state.pageLinkCount) *
                state.pageLinkCount <
                state.currentPageIndex
            ) {
                return false;
            } else {
                return true;
            }
        },
    },
    actions: {
        async boardList({ state, commit }) {
            let params = {
                limit: state.limit,
                offset: state.offset,
                searchWord: state.searchWord,
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
    mutations: {
        SET_BOARD_LIST(state, list) {
            state.list = list;
        },
        SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.totalListItemCount = count;
        },
        SET_BOARD_MOVE_PAGE(state, pageIndex) {
            state.offset = (pageIndex - 1) * state.listRowCount;
            state.currentPageIndex = pageIndex;
        },
        SET_BOARD_DETAIL(state, payload) {
            state.boardId = payload.boardId;
            state.title = payload.title;
            state.content = payload.content;
            state.userName = payload.userName;
            state.regDate = util.makeDateStr(
                payload.regDt.date.year,
                payload.regDt.date.month,
                payload.regDt.date.day,
                "."
            );
            state.regTime = util.makeTimeStr(
                payload.regDt.time.hour,
                payload.regDt.time.minute,
                payload.regDt.time.second,
                ":"
            );
            state.readCount = payload.readCount;
            state.sameUser = payload.sameUser;
        },

        SET_BOARD_TITLE(state, title) {
            state.title = title;
        },

        SET_BOARD_SEARCH_WORD(state, searchWord) {
            state.searchWord = searchWord;
        },
    }
}

export default boardStore;