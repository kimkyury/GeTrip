import http from "@/common/axios.js";
import VueAlertify from "vue-alertify";
import Vue from "vue";

Vue.use(VueAlertify);

const favoriteStore = {
    namespaced: true,
    state: {
        hotplaceList: [],
        hotplaceCount: 0,

        hotplaceListFromUser: [],
        hotplaceCountFromUser: 0,

        favoriteList: [],
        favoriteCount: 0,

        userSeq: 0,
        userSidoCode: 0,
        userSidoName: "",
        userGugunCode: 0,
        userGugunName: "",
    },
    getters: {
        getStoreHotplaceList: function (state) {
            return state.hotplacelist;
        },
        getStoreHotplaceCount: function (state) {
            return state.hotplaceCount;
        },

        getStoreHotplaceListFromUser: function (state) {
            return state.hotplaceListFromUser;
        },

        getStoreHotplaceCountFromUser: function (state) {
            return state.hotplaceCountFromUser;
        },

        getStoreFavoriteList: function (state) {
            return state.favoriteList;
        },
        getStoreFavoriteCount: function (state) {
            return state.favoriteCount;
        },
    },
    actions: {
        async getHotplaceList({ commit }) {
            try {
                let { data } = await http.get(`/places/hotplaces`);
                commit("SET_HOTPLACE_LIST", data.hotplaceGetDtoList);
                commit("SET_HOTPLACE_COUNT", data.count);
            } catch (error) {
                console.log(error);
            }
        },

        async getHotplaceListFromUser({ state, commit }) {
            try {
                let { data } = await http.get(`/places/hotplaces/${state.userSeq}`);
                commit("SET_HOTPLACE_FROM_USER", data.hotplaceGetDtoList);
                commit("SET_HOTPLACE_COUNT_FROM_USER", data.count);
            } catch (error) {
                console.log(error);
            }
        },

        async getFavoriteList({ state, commit }) {
            console.log("favorite UserSeq: ", state.userSeq);
            try {
                let { data } = await http.get(`/users/${state.userSeq}/places/favorites`);
                console.log("favoriteList: " + data.favoriteGetDtoList);

                commit("SET_FAVORITE_LIST", data.favoriteGetDtoList);
                commit("SET_FAVORITE_COUNT", data.count);
            } catch (error) {
                console.log(error);
            }
        },

        async postFavorite({ favoriteInfo }) {
            let userSeq = favoriteInfo.userSeq;

            let params = {
                userSeq: favoriteInfo.userSeq,
                contentId: favoriteInfo.contentId,
            };

            try {
                let { data } = await http.post(`/users/${userSeq}/places/favorites`, {
                    params,
                });

                if (data.result == 1) {
                    this.$alertify.success("클릭하였습니다");
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    mutations: {
        SET_USERINFO(state, payload) {
            state.userSeq = payload.userSeq;
            state.userGugunCode = payload.userGugunCode;
            state.userSidoCode = payload.userSidoCode;
            state.userGugunName = payload.userGugunName;
            state.userSidoName = payload.userSidoName;
        },

        SET_HOTPLACE_LIST(state, list) {
            state.hotplaceList = list;
        },
        SET_HOTPLACE_COUNT(state, count) {
            state.hotplaceCount = count;
        },

        SET_HOTPLACE_FROM_USER(state, list) {
            state.hotplaceListFromUser = list;
        },
        SET_HOTPLACE_COUNT_FROM_USER(state, count) {
            state.hotplaceCountFromUser = count;
        },

        SET_FAVORITE_LIST(state, list) {
            state.favoriteList = list;
        },
        SET_FAVORITE_COUNT(state, count) {
            state.favoriteCount = count;
        },
    },
};

export default favoriteStore;
