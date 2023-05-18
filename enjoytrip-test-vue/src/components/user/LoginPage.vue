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
            </div>
        </form>
    </div>
</template>
<script>
import http from "@/common/axios";

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
                    this.$store.commit("SET_LOGIN", {
                        isLogin: true,
                        userName: data.userName,
                        userProfileImageUrl: data.userProfileImageUrl,
                    });
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
    },
};
</script>
