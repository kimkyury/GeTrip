import http from "@/common/axios.js";
import VueAlertify from "vue-alertify";
import Vue from "vue";

Vue.use(VueAlertify);

const favoriteStore = {
    namespaced: true,
    state: {
        hotplaceList: [],
        hotplaceListFromUser: [],
        favoriteList: [],

        hotplaceCount: 0,
        hotplaceCountFromUser: 0,
        favoriteCount: 0,
    },
    getters: {
        getHotplaceList: function (state) {
            return state.hotplacelist;
        },

        getHotplaceListFromUser: function (state) {
            return state.hotplaceListFromUser;
        },

        getFavoriteList: function (state) {
            return state.favoriteList;
        },

        getHotplaceCount: function (state) {
            return state.hotplaceCount;
        },

        getHotplaceCountFromUser: function (state) {
            return state.hotplaceCountFromUser;
        },

        getFavoriteCount: function (state) {
            return state.favoriteCount;
        },
    },
    actions: {
        async getHotplaceList({ commit }) {
            try {
                let { data } = await http.get(`/places/hotplaces`);
                console.log("GET HOTPLACE: " + data);

                commit("SET_HOTPLACE_LIST", data.hotplaceGetDtoList);
                commit("SET_HOTPLACE_COUNT", data.count);
            } catch (error) {
                console.log(error);
            }
        },

        async getHotplaceListFromUser({ userSeq, commit }) {
            try {
                let { data } = await http.get(`/places/hotplaces/${userSeq}`);
                console.log("GET HOTPLACE FROM USER: " + data);

                commit("SET_HOTPLACE_FROM_USER", data.hotplaceGetDtoList);
                commit("SET_HOTPLACE_COUNT_FROM_USER", data.count);
            } catch (error) {
                console.log(error);
            }
        },

        async getFavoriteList({ userSeq, commit }) {
            try {
                let { data } = await http.get(`/users/${userSeq}/places/favorites`);
                console.log("GET HOTPLACE FROM USER: " + data);

                commit("SET_FAVORITE_LIST", data.hotplaceGetDtoList);
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
