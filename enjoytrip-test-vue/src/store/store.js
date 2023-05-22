import Vue from "vue";
import Vuex from "vuex";

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
});
