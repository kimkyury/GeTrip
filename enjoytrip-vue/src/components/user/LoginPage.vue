<template>
    <div class="col-lg-6">
        <header class="mb-5">
            <h2 class="text-uppercase mb-4">로그인</h2>
            <p class="lead">Already our customer?</p>
        </header>
        <p class="text-muted mb-4">로그인 하러 오세요!</p>
        <hr class="border-gray-200" />
        <!-- LOGIN FORM-->
        <form action="customer-orders.html">
            <div class="form-group mb-3">
                <label class="form-label" for="loginemail"></label>
                <input
                    class="form-control"
                    v-model="loginUserEmail"
                    type="text"
                    id="loginemail"
                    placeholder="Enter your email address"
                />
            </div>
            <div class="form-group mb-3">
                <label class="form-label" for="loginpassword"></label>
                <input
                    class="form-control"
                    v-model="loginUserPassword"
                    type="password"
                    id="loginpassword"
                    placeholder="Enter your password"
                />
            </div>
            <div class="form-group mb-3 text-center">
                <button type="button" class="btn btn-outline-primary" @click="login()">
                    <i class="fas fa-door-open me-2"></i>Log in
                </button>
                <img
                    height="38px"
                    src="@/assets/kakao_login_button.png"
                    @click="kakaoLogin()"
                />
            </div>
        </form>
    </div>
</template>
<script>
import http from "@/common/axios";
import { mapMutations, mapActions } from "vuex";
const loginStore = "loginStore";
const favoriteStore = "favoriteStore";

export default {
    data() {
        return {
            // 로그인
            isLoginUserEmailValid: false,
            isLoginUserPasswordValid: false,

            loginUserEmail: "",
            loginUserPassword: "",
        };
    },
    methods: {
        ...mapActions(favoriteStore, ["getHotplaceListFromUser", "getFavoriteList"]),
        ...mapMutations(favoriteStore, ["RESET_FAVORITE", "SET_USERINFO"]),
        ...mapMutations(loginStore, { setLogin: "SET_LOGIN" }),
        async validate() {
            if (this.loginUserEmail.length > 0) this.isLoginUserEmailValid = true;
            if (this.loginUserPassword.length > 0) this.isLoginUserPasswordValid = true;
            if (this.isLoginUserEmailValid && this.isLoginUserPasswordValid) return true;
            return false;
        },
        async login() {
            if (await this.validate()) {
                let loginObj = {
                    userEmail: this.loginUserEmail,
                    userPassword: this.loginUserPassword,
                };
                try {
                    // logincontroller에 CORS처리 및 @ReqeustBody
                    let { data } = await http.post("/login", loginObj);
                    console.log(data);
                    console.log(data.userSeq);
                    // this 가 다른 url
                    this.setLogin({
                        isLogin: true,

                        userSeq: data.userSeq,
                        userName: data.userName,
                        userEmail: data.userEmail,
                        userProfileImageUrl: data.userProfileImageUrl,
                        userRegisterDate: data.userRegisterDate,
                        userGenderCode: data.genderCode,
                        userGenderName: data.genderName,
                        userBirthYear: data.userBirthYear,
                        userBirthMonth: data.userBirthMonth,
                        userBirthDay: data.userBirthDay,
                        userSidoCode: data.userSidoCode,
                        userSidoName: data.userSidoName,
                        userGugunCode: data.userGugunCode,
                        userGugunName: data.userGugunName,
                        userClsfCode: data.userClsfCode,
                        userClsfName: data.userClsfName,
                    });

                    await this.setFavoriteInfo();
                    this.$router.push("/");
                } catch (error) {
                    console.log("LoginVue: error : ");
                    console.log(error);
                    if (error.response.status == "404") {
                        alert("이메일 또는 비밀번호를 확인하세요.");
                    } else {
                        alert("Opps!! 서버에 문제가 발생했습니다.");
                    }
                }
            } else {
                alert("정보를 입력해 주세요.");
            }
        },

        async setFavoriteInfo() {
            // Favorite init
            this.SET_USERINFO({
                userSeq: this.userSeq,
                userSidoName: this.userSidoName,
                userSidoCode: this.userSidoCode,
                userGugunName: this.userGugunName,
            });

            await this.getHotplaceListFromUser;
            await this.getFavoriteList;
        },
        // session에 정보를 저장하고 로그인 관리하자(NavBar, logout)
        // 로그인시 가입되어 있지 않을 경우 가입시키고 그렇지 않을 경우 로그인
        // 필수 정보는 default로 준다.
        async kakaoLogin() {
            window.Kakao.Auth.login({
                success: async (response) => {
                    await window.Kakao.API.request({
                        url: "/v2/user/me",
                        data: {
                            property_keys: [
                                "kakao_account.email",
                                "kakao_account.gender",
                                "kakao_account.birthday",
                                "kakao_account.profile_nickname",
                            ],
                        },
                        success: async (response) => {
                            console.log("id(코드)");
                            console.log(
                                response.kakao_account.birthday,
                                response.kakao_account.email,
                                response.kakao_account.gender
                            );

                            let { data } = await http.get(
                                "/login/kakao/" + response.kakao_account.email
                            );
                            console.log(data.result);
                            console.log(this);
                            if (data.result == "success") {
                                // 아이디가 존재할 경우 가져온 값으로 로그인
                                this.setLogin({
                                    isLogin: true,

                                    userSeq: data.userSeq,
                                    userName: data.userName,
                                    userEmail: data.userEmail,
                                    userProfileImageUrl: data.userProfileImageUrl,
                                    userRegisterDate: data.userRegisterDate,
                                    userGenderCode: data.genderCode,
                                    userGenderName: data.genderName,
                                    userBirthYear: data.userBirthYear,
                                    userBirthMonth: data.userBirthMonth,
                                    userBirthDay: data.userBirthDay,
                                    userSidoCode: data.userSidoCode,
                                    userSidoName: data.userSidoName,
                                    userGugunCode: data.userGugunCode,
                                    userGugunName: data.userGugunName,
                                    userClsfCode: data.userClsfCode,
                                    userClsfName: data.userClsfName,
                                });
                                alert("로그인 성공!");
                                this.$router.push("/");
                            } else {
                                // 아이디가 존재하지 않을 경우 받은 값으로 회원가입.
                                let email = response.kakao_account.email.split("@")[0];
                                let birthMonth = response.kakao_account.birthday.substr(
                                    0,
                                    2
                                );
                                let birthDay = response.kakao_account.birthday.substr(
                                    2,
                                    2
                                );
                                let gender = "";
                                if (response.kakao_account.gender == "male") {
                                    gender = "1";
                                } else {
                                    gender = "2";
                                }
                                let signupObj = {
                                    userName: "카카오 name",
                                    userEmail: response.kakao_account.email,
                                    userPassword: email + response.kakao_account.birthday,
                                    userProfileImageUrl: "@/assets/img/animal/bat.png",
                                    gender: gender,
                                    birthYear: "2000",
                                    birthMonth: birthMonth,
                                    birthDay: birthDay,
                                    sidoCode: "1",
                                    gugunCode: "1",
                                };

                                console.log(signupObj);

                                try {
                                    let { data } = await http.post("/users", signupObj);
                                    console.log("data: ", data);
                                    console.log("data.result: ", data.result);
                                    if (data.result == 1) {
                                        alert("회원가입 성공!");
                                        let { data } = await http.get(
                                            "/login/kakao/" + response.kakao_account.email
                                        );
                                        this.setLogin({
                                            isLogin: true,

                                            userSeq: data.userSeq,
                                            userName: data.userName,
                                            userEmail: data.userEmail,
                                            userProfileImageUrl: data.userProfileImageUrl,
                                            userRegisterDate: data.userRegisterDate,
                                            userGenderCode: data.genderCode,
                                            userGenderName: data.genderName,
                                            userBirthYear: data.userBirthYear,
                                            userBirthMonth: data.userBirthMonth,
                                            userBirthDay: data.userBirthDay,
                                            userSidoCode: data.userSidoCode,
                                            userSidoName: data.userSidoName,
                                            userGugunCode: data.userGugunCode,
                                            userGugunName: data.userGugunName,
                                            userClsfCode: data.userClsfCode,
                                            userClsfName: data.userClsfName,
                                        });
                                        alert("로그인 성공!");
                                        this.$router.push("/");
                                    }
                                } catch (error) {
                                    alert(error);
                                }
                            }
                        },
                        fail: function (error) {
                            console.log(error);
                        },
                    });
                    console.log("access_token");
                    console.log(response);
                },
                fail: function (error) {
                    console.log(error);
                },
            });
        },
        // kakaoLogout() {
        // if ( window.Kakao.Auth.getAccessToken()) {
        //    window.Kakao.API.request({
        //     url: '/v1/user/unlink',
        //     success: function (response) {
        //         console.log(response)
        //     },
        //     fail: function (error) {
        //       console.log(error)
        //     },
        //   })
        //    window.Kakao.Auth.setAccessToken(undefined)
        // }
        // },
    },
    mounted() {
        window.Kakao.init("20d8b1fb60682e2256b9d4e10ea6a783");
        console.log(window.Kakao.isInitialized());
    },
};
</script>
