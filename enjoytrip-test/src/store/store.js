// import http from "@/common/axios.js";
import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);
export default new Vuex.Store({
    state:{
        login:{
            isLogin: false,

            userName : "",
            userProfileImageUrl: "",

            userEmail: "",
            userPassword:"",
        }
    },
    mutations :{
        SET_LOGIN(state, payload){
            state.login.isLogin = payload.isLogin;
            state.login.userName = payload.userName;
            state.login.userProfileImageUrl = payload.userProfileImageUrl;
        },
        SET_LOGOUT(state){
            state.login.isLogin = false;
            state.login.userName ="";
            state.login.userProfileImageUrl = "";
        }
    },
    actions: {
    },
    getters:{
        isLogin: function (state) {
            return state.login.isLogin;
         },
    }
});