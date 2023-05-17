import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
// import store from "@/store/store.js";
Vue.use(VueRouter);

import HomePage from "@/components/HomePage";
import BoardPage from "@/components/board/BoardPage";
import PlacePage from "@/components/place/PlacePage";
import MyPage from "@/components/user/MyPage";
import LoginPage from "@/components/user/LoginPage";
import SignUpPage from "@/components/user/SignUpPage";

export default new VueRouter({
    routes: [
        {
            path: "/",
            component: HomePage,
        },

        {
            name: "BoardMain",
            path: "/boards",
            component: BoardPage,
            // beforeEnter: (to, from, next) => {
            //     console.log(store.state.login.isLogin);
            //     if (!store.state.login.isLogin) {
            //         next("/login");
            //     } else {
            //         return next();
            //     }
            // },
        },
        {
            name: "PlacePage",
            path: "/places",
            component: PlacePage,
        },
        {
            name: "MyPage",
            path: "/mypage",
            component: MyPage,
        },

        {
            name: "Login",
            path: "/login",
            component: LoginPage,
        },

        {
            name: "SignUp",
            path: "/signup",
            component: SignUpPage,
        },
    ],
});
