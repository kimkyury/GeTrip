<template lang="">
    <div>
        <!-- HEADING BREADCRUMB-->
        <section class="py-4">
            <div class="container py-3">
                <div class="row d-flex align-items-center gy-4">
                    <div class="col-lg-9">
                        <h1>New account / sign in</h1>
                        <p class="lead mb-4">같이 STARTrip!</p>
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
                            <h2 class="text-uppercase mb-4">계정생성</h2>
                            <p class="lead">Not our registered customer yet?</p>
                        </header>
                        <p class="text-muted mb-4">
                            EnjoyTrip에 아직 회원이 아니신가요? 회원가입을 통해 당신의
                            여행을 즐겨보세요!
                        </p>

                        <hr class="border-gray-200" />
                        <!-- REGISTER FORM-->
                        <form action="customer-orders.html">
                            <div class="form-group mb-3">
                                <label class="form-label" for="username"></label>
                                <input
                                    class="form-control"
                                    v-model="signupUserName"
                                    id="username"
                                    type="text"
                                    name="username"
                                    placeholder="Enter your full name"
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label class="form-label" for="useremail"></label>
                                <input
                                    class="form-control"
                                    v-model="signupUserEmail"
                                    id="useremail"
                                    type="text"
                                    name="useremail"
                                    placeholder="Enter your email address"
                                />
                            </div>
                            <div class="form-group mb-3">
                                <label class="form-label" for="userpassword"></label>
                                <input
                                    class="form-control"
                                    v-model="signupUserPassword"
                                    id="userpassword"
                                    type="password"
                                    name="userpassword"
                                    placeholder="Enter your password"
                                />
                            </div>

                            <!-- 0518 ADDED -->
                            <div class="form-group mb-3">
                                <label
                                    class="form-label"
                                    for="userpasswordConfirm"
                                ></label>
                                <input
                                    class="form-control"
                                    v-model="signupUserPasswordConfirm"
                                    id="userpasswordConfirm"
                                    type="password"
                                    name="userpasswordConfirm"
                                    placeholder="Enter your password"
                                />
                            </div>
                            <div class="row">
                                <div class="col-md-3 mb-3 col-lg-3">
                                    <label class="form-label" for="profile"
                                        >Profile Image</label
                                    >
                                    <select
                                        class="form-select"
                                        id="profile"
                                        v-model="signupUserProfileImageUrl"
                                    >
                                        <option
                                            v-for="(image, index) in $store.state
                                                .loginStore.profileImage"
                                            :key="index"
                                            v-bind:value="image.path"
                                        >
                                            {{ image.name }}
                                        </option>
                                    </select>
                                </div>
                                <div
                                    v-for="(image, index) in $store.state.loginStore
                                        .profileImage"
                                    :key="index"
                                    style="padding: 0px; width: 0px"
                                    id="imageRoop"
                                >
                                    <img
                                        v-if="image.path == signupUserProfileImageUrl"
                                        :src="image.imageView"
                                        style="width: 100px; height: 100px"
                                    />
                                </div>
                            </div>
                            <!-- birth_year, birth_day, birth_month -->
                            <div class="row">
                                <div class="col-md-3 mb-3 col-lg-3">
                                    <label class="form-label" for="gender">성별</label>
                                    <select
                                        v-model="signupUserGender"
                                        class="form-select js-states"
                                        id="gender"
                                        name="gender"
                                        data-customclass="form-control py-1"
                                    >
                                        <option value="01">남성</option>
                                        <option value="02">여성</option>
                                    </select>
                                </div>

                                <div class="col-md-4 mb-3 col-lg-3">
                                    <label class="form-label" for="birthYear"
                                        >태어난 년도</label
                                    >
                                    <input
                                        v-model="signupUserBirthYear"
                                        class="form-control"
                                        id="birthYear"
                                        type="text"
                                        name="birthYear"
                                    />
                                </div>
                                <div class="col-md-4 mb-3 col-lg-3">
                                    <label class="form-label" for="birthMonth">월 </label>
                                    <input
                                        v-model="signupUserBirthMonth"
                                        class="form-control"
                                        id="birthMonth"
                                        type="text"
                                        name="birthMonth"
                                    />
                                </div>
                                <div class="col-md-4 mb-3 col-lg-3">
                                    <label class="form-label" for="birthDay">일</label>
                                    <input
                                        v-model="signupUserBirthDay"
                                        class="form-control"
                                        id="birthDay"
                                        type="text"
                                        name="birthDay"
                                    />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3 col-lg-6">
                                    <label class="form-label" for="sido">
                                        8도 선택
                                    </label>
                                    <!-- Using a select element to create a dropdown menu -->
                                    <select
                                        v-model="signupSidoCode"
                                        class="form-control"
                                        id="sido"
                                        name="sido"
                                        @change="getGuguns()"
                                    >
                                        <!-- Loop through sidos array with v-for directive -->

                                        <option disabled value="">
                                            8도 중 하나를 선택하세요.
                                        </option>
                                        <option
                                            v-for="sido in areaList1"
                                            :key="sido.sidoCode"
                                            :value="sido.sidoCode"
                                        >
                                            {{ sido.sidoName }}
                                        </option>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3 col-lg-6">
                                    <label class="form-label" for="gugun">
                                        도시 선택
                                    </label>
                                    <!-- Using a select element to create a dropdown menu -->
                                    <select
                                        v-model="signupGugunCode"
                                        class="form-control"
                                        id="gugun"
                                        name="gugun"
                                    >
                                        <!-- Loop through sidos array with v-for directive -->

                                        <option disabled value="">
                                            도시 중 하나를 선택하세요.
                                        </option>
                                        <option
                                            v-for="gugun in guguns"
                                            :key="gugun.gugunCode"
                                            :value="gugun.gugunCode"
                                        >
                                            {{ gugun.gugunName }}
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group mb-3 text-center">
                                <button
                                    type="button"
                                    @click="signup()"
                                    class="btn btn-outline-primary"
                                >
                                    <i class="fas fa-user me-2"></i>Register
                                </button>
                            </div>
                        </form>
                    </div>
                    <!-- LOGIN BLOCK                -->
                    <login-page></login-page>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import http from "@/common/axios";
import LoginPage from "@/components/user/LoginPage";
import { mapState, mapActions } from "vuex";

const SUCCESS = 1;

export default {
    components: { LoginPage },
    data() {
        return {
            guguns: [],
            // 회원가입
            isSignupUserNamelValid: false,
            isSignupUserEmailValid: false,
            isSignupUserPasswordValid: false,

            signupUserProfileImageUrl: "",

            // TODO: 공란으로 비울 것
            signupUserName: "",
            signupUserEmail: "",
            signupUserPassword: "",
            signupUserPasswordConfirm: "",

            signupUserGender: "",

            signupUserBirthYear: "",
            signupUserBirthMonth: "",
            signupUserBirthDay: "",

            signupSidoCode: "",
            signupGugunCode: "",

            // 유효성 검사
        };
    },
    methods: {
        ...mapActions("placeStore", ["getArea1List", "getArea2List"]),

        getSidos() {
            console.log("getSidos 수행");
            this.getArea1List();
        },

        async getGuguns() {
            console.log(this.signupSidoCode);
            this.signupGugunCode = "";
            let { data } = await http.get("/codes/" + this.signupSidoCode);
            console.log(data);
            this.guguns = data.list;

            console.log(this.guguns);
        },

        async signup() {
            let signupObj = {
                userName: this.signupUserName,
                userEmail: this.signupUserEmail,
                userPassword: this.signupUserPassword,
                userProfileImageUrl: this.signupUserProfileImageUrl,

                gender: this.signupUserGender,

                birthYear: this.signupUserBirthYear,
                birthMonth: this.signupUserBirthMonth,
                birthDay: this.signupUserBirthDay,

                sidoCode: this.signupSidoCode,
                gugunCode: this.signupGugunCode,
            };

            console.log(signupObj);

            try {
                let { data } = await http.post("/users", signupObj);
                console.log("data: ", data);
                console.log("data.result: ", data.result);
                if (data.result == SUCCESS) {
                    alert("회원가입 성공");
                    this.$router.push("/login");
                }
            } catch (error) {
                alert(error);
            }
        },
    },

    computed: {
        ...mapState("placeStore", ["areaList1", "areaList2"]),
    },

    created() {
        this.getSidos();
    },
};
</script>
<style scoped></style>
