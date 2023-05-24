import Vue from "vue";
import Vuex from "vuex";

import placeStore from "@/store/modules/placeStore";
import loginStore from "@/store/modules/loginStore";
import boardStore from "@/store/modules/boardStore";
import favoriteStore from "@/store/modules/favoriteStore";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);
export default new Vuex.Store({
    modules: {
        boardStore,
        placeStore,
        loginStore,
        favoriteStore,
    },
    plugins: [
        createPersistedState({
            storage: window.sessionStorage,
            paths: ["loginStore", "boardStore", "favoriteStore"],
        }),
    ],
});
