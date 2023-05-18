import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
// import store from "@/store/store.js";
Vue.use(VueRouter);

import HomePage from "@/components/HomePage";
import BoardPage from "@/components/board/BoardPage";
import PlacePage from "@/components/place/PlacePage";
import LoginPage from "@/components/user/LoginPage";
import SignUpPage from "@/components/user/SignUpPage";

import UserInfoChange from "@/components/user/mypage/UserInfoChange";
import UserWishList from "@/components/user/mypage/UserWishList";
import MyPage from "@/components/user/mypage/MyPage";

export default new VueRouter({
    routes: [
        {
            name: "Home",
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
            path: "/member/mypage",
            component: MyPage,
        },
        {
            name: "UserInfoChange",
            path: "/member/userinfochange",
            component: UserInfoChange,
        },
        {
            name: "UserWishList",
            path: "/member/wishlist",
            component: UserWishList,
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
