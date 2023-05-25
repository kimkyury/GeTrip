import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
// import store from "@/store/store.js";
Vue.use(VueRouter);

import HomePage from "@/components/HomePage";
import BoardPage from "@/components/board/BoardPage";

import PlacePage from "@/components/place/PlacePage";
import HotPlacePage from "@/components/place/HotPlacePage";
import PlaceDetailPage from "@/components/place/PlaceDetailPage";
import HotPlaceFromUserPage from "@/components/place/HotPlaceFromUserPage";

import LoginPage from "@/components/user/LoginPage";
import SignUpPage from "@/components/user/SignUpPage";

import UserInfoChange from "@/components/user/mypage/UserInfoChange";
import UserWishList from "@/components/user/mypage/UserWishList";
import MyPage from "@/components/user/mypage/MyPage";
import WishListErrorPage from "@/components/user/mypage/WishListErrorPage";

import store from "@/store/store";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

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
            name: "PlaceDetailPage",
            path: "/places",
            component: PlaceDetailPage,
        },
        {
            name: "HotPlacePage",
            path: "/places/hotplaces",
            component: HotPlacePage,
        },
        {
            name: "HotPlaceFromUserPage",
            path: "/places/hotplaces/user",
            component: HotPlaceFromUserPage,
        },

        {
            name: "MyPage",
            path: "/member/mypage",
            component: MyPage,
            beforeEnter: (to, from, next) => {
                if (store.state.loginStore.isLogin != false) {
                    return next();
                }
                Vue.prototype.$alertify.error("로그인이 필요한 서비스입니다. ");

                next("/signup");
            },
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
            name: "WishListErrorPage",
            path: "/member/wishlist",
            component: WishListErrorPage,
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
