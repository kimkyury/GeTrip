import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import board from "./modules/boardStore";
import login from "./modules/loginStore";

export default new Vuex.Store({
    modules: {
        board,
        login,
    },
});
