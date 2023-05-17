import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import board from "./modules/boardStore";

export default new Vuex.Store({
    modules: {
        board,
    },
});
