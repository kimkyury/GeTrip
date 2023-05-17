<template lang="">
<div>
     <!-- HEADING BREADCRUMB-->
            <section class="bg-pentagon py-4">
              <div class="container py-3">
                <div class="row d-flex align-items-center gy-4">
                  <div class="col-md-7">
                    <h1 class="h2 mb-0 text-uppercase">New account / sign in</h1>
                  </div>
                  <div class="col-md-5">
                    <!-- Breadcrumb-->
                    <ol class="text-sm justify-content-start justify-content-lg-end mb-0 breadcrumb undefined">
                      <li class="breadcrumb-item"><a class="text-uppercase" href="index.html">Home</a></li>
                      <li class="breadcrumb-item text-uppercase active">New account / sign in      </li>
                    </ol>
                  </div>
                </div>
              </div>
            </section>
      <!-- CREDENTIALS SECTION-->
      <section class="py-5">
        <div class="container py-4">
          <div class="row g-5">
            <!-- REGISTER BLOCK-->
            <div class="col-lg-6">
              <header class="mb-5">
                <h2 class="text-uppercase mb-4">New account</h2>
                <p class="lead">Not our registered customer yet?</p>
              </header>
              <p>With registration with us new world of fashion, fantastic discounts and much more opens to you! The whole process will not take you more than a minute!</p>
              <p class="text-muted mb-4">If you have any questions, please feel free to <a href="#">contact us</a>, our customer service center is working for you 24/7.</p>
              <hr class="border-gray-200">
              <!-- REGISTER FORM-->
              <form action="customer-orders.html">
                <div class="form-group mb-3">
                  <label class="form-label" for="username"></label>
                  <input class="form-control" v-model="signupUserName" id="username" type="text" name="username" placeholder="Enter your full name">
                </div>
                <div class="form-group mb-3">
                  <label class="form-label" for="useremail"></label>
                  <input class="form-control" v-model="signupUserEmail" id="useremail" type="text" name="useremail" placeholder="Enter your email address">
                </div>
                <div class="form-group mb-3">
                  <label class="form-label" for="userpassword"></label>
                  <input class="form-control" v-model="signupUserPassword" id="userpassword" type="text" name="userpassword" placeholder="Enter your password">
                </div>
                <div class="form-group mb-3 text-center">
                  <button type="button" @click="signup()" class="btn btn-outline-primary"><i class="fas fa-user me-2"></i>Register</button>
                </div>
              </form>
            </div>
            <!-- LOGIN BLOCK                -->
            <div class="col-lg-6">
              <header class="mb-5">
                <h2 class="text-uppercase mb-4">Login</h2>
                <p class="lead">Already our customer?</p>
              </header>
              <p class="text-muted mb-4">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
              <hr class="border-gray-200">
              <!-- LOGIN FORM-->
              <form action="customer-orders.html">
                <div class="form-group mb-3">
                  <label class="form-label" for="loginemail"></label>
                  <input class="form-control" v-model="loginUserEmail" type="text" id="loginemail" placeholder="Enter your email address">
                </div>
                <div class="form-group mb-3">
                  <label class="form-label" for="loginpassword"></label>
                  <input class="form-control" v-model="loginUserPassword" type="password" id="loginpassword" placeholder="Enter your password">
                </div>
                <div class="form-group mb-3 text-center">
                  <button type="button" class="btn btn-outline-primary" @click="login()"><i class="fas fa-door-open me-2"></i>Log in</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
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

      // 회원가입
      isSignupUserNamelValid: false,
      isSignupUserEmailValid: false,
      isSignupUserPasswordValid: false,
      signupUserName: "",
      signupUserEmail: "",
      signupUserPassword: "",

      // 유효성 검사


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
          this.$store.commit("SET_LOGIN", { isLogin: true, userName: data.userName, userProfileImageUrl: data.userProfileImageUrl });
          this.$router.push("/board");
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
        alert("정보를 입력해 주세요.")
      }
    },
    async signup() {
      let signupObj = {
        userName : this.signupUserName,
        userEmail: this.signupUserEmail,
        userPassword: this.signupUserPassword,
      }
      try{
      let { data } = await http.post("/users", signupObj);
      if(data.result == "success"){
        alert("회원가입 성공");
        this.$router.push('Login');
      }
      }catch(error){
        alert(error);
      }
    },
  },
};
</script>
<style lang="">
</style>