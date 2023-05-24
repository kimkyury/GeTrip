<template>
  <div>
    <section class="py-5">
      <div class="container py-4">
        <div class="row gy-5">
          <div class="col-lg-9">
            <h1>Change My Info</h1>
            <p class="lead mb-4">회원정보 수정 페이지입니다.</p>
            <p class="text-muted mb-5">
              본인의 개인정보 혹은 비밀번호의 수정이 가능합니다.
            </p>
            <!-- CHANGE PASSWORD FORM-->
            <form class="py-4 border-top border-bottom mb-5" action="#">
              <div class="row">
                <div class="col-12 mb-4">
                  <h3 class="text-uppercase lined">비밀번호 교체</h3>
                </div>
                <div class="col-lg-6 mb-3">
                  <label class="form-label" for="password_old">
                    이전 비밀번호:
                  </label>
                  <input
                    class="form-control"
                    id="password_old"
                    type="password"
                    name="password_old"
                    v-model="userPassword"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-lg-6 mb-3">
                  <label class="form-label" for="password_1"
                    >새 비밀번호:
                  </label>
                  <input
                    class="form-control"
                    id="password_1"
                    type="password"
                    name="password_1"
                    v-model="userUpdatePassword"
                  />
                </div>
                <div class="col-lg-6 mb-3">
                  <label class="form-label" for="password_2"
                    >새 비밀번호 재확인:
                  </label>
                  <input
                    class="form-control"
                    id="password_2"
                    type="password"
                    name="password_2"
                    v-model="userUpdatePasswordConfirm"
                  />
                </div>
                <div class="col-lg-12 text-center">
                  <button
                    class="btn btn-outline-primary"
                    type="button"
                    @click="passwordUpdate()"
                  >
                    <i class="fas fa-save me-2"></i> 새 비밀번호 저장
                  </button>
                </div>
              </div>
            </form>
            <!-- PROFILE DETAIL FORM-->
            <form class="py-4" action="#">
              <div class="row">
                <div class="col-12 mb-4">
                  <h3 class="text-uppercase lined">Profile detail</h3>
                </div>
                <div class="col-md-12 mb-3">
                  <label class="form-label" for="fullName"> 이메일 </label>
                  <input
                    class="form-control"
                    id="fullName"
                    type="text"
                    name="fullName"
                    v-model="$store.state.loginStore.updateUserEmail"
                    readonly
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 mb-4 col-lg-4">
                  <label class="form-label" for="fullName">이름</label>
                  <input
                    class="form-control"
                    id="fullName"
                    type="text"
                    name="fullName"
                    v-model="$store.state.loginStore.updateUserName"
                  />
                </div>
                <div class="col-md-4 mb-4 col-lg-4">
                  <label class="form-label" for="area1List">8도</label>
                  <select
                    id="area1List"
                    class="form-select"
                    v-model="$store.state.loginStore.updateUserSidoCode"
                    @change="updateArea2List()"
                  >
                    <option value="0">시도를 선택하세요</option>
                    <option
                      v-for="(area, index) in areaList1"
                      :key="index"
                      v-bind:value="area.sidoCode"
                    >
                      {{ area.sidoName }}
                    </option>
                  </select>
                </div>
                <div class="col-md-4 mb-4 col-lg-4">
                  <label class="form-label" for="area2List">도시</label>
                  <select
                    id="area2List"
                    class="form-select"
                    v-model="$store.state.loginStore.updateUserGugunCode"
                  >
                    <option value="0">구군을 선택하세요</option>
                    <option
                      v-for="(area, index) in gugunList"
                      :key="index"
                      v-bind:value="area.gugunCode"
                    >
                      {{ area.gugunName }}
                    </option>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 mb-3 col-lg-3">
                  <label class="form-label" for="gender"> 성별 </label>
                  <select
                    class="form-select"
                    v-model="$store.state.loginStore.updateUserGenderCode"
                  >
                    <option value="1">남자</option>
                    <option value="2">여자</option>
                  </select>
                </div>
                <div class="col-md-3 mb-3 col-lg-3">
                  <label class="form-label" for="birthYear">태어난 년도</label>
                  <input
                    class="form-control"
                    id="birthYear"
                    type="text"
                    name="birthYear"
                    v-model="$store.state.loginStore.updateUserBirthYear"
                  />
                </div>
                <div class="col-md-3 mb-3 col-lg-3">
                  <label class="form-label" for="birthMonth">월</label>
                  <input
                    class="form-control"
                    id="birthMonth"
                    type="text"
                    name="birthMonth"
                    v-model="$store.state.loginStore.updateUserBirthMonth"
                  />
                </div>
                <div class="col-md-3 mb-3 col-lg-3">
                  <label class="form-label" for="birthDay">일</label>
                  <input
                    class="form-control"
                    id="birthDay"
                    type="text"
                    name="birthDay"
                    v-model="$store.state.loginStore.updateUserBirthDay"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 mb-3 col-lg-3">
                  <label class="form-label" for="profile">Profile Image</label>
                  <select
                    class="form-select"
                    id="profile"
                    v-model="$store.state.loginStore.updateUserProfileImageUrl"
                  >
                    <option
                      v-for="(image, index) in $store.state.loginStore
                        .profileImage"
                      :key="index"
                      v-bind:value="image.path"
                    >
                      {{ image.name }}
                    </option>
                  </select>
                </div>
                <div
                  v-for="(image, index) in $store.state.loginStore.profileImage"
                  :key="index"
                  style="padding: 0px; width: 0px"
                  id="imageRoop"
                >
                  <img
                    v-if="
                      image.path ==
                      $store.state.loginStore.updateUserProfileImageUrl
                    "
                    :src="image.imageView"
                    style="width: 100px; height: 100px"
                  />
                </div>
              </div>
              <div class="col-lg-12 text-center">
                <button
                  class="btn btn-outline-primary"
                  type="button"
                  @click="userUpdate()"
                >
                  <i class="fas fa-save me-2"></i> 저장
                </button>
              </div>
            </form>
          </div>
          <member-section></member-section>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import MemberSection from "./MemberSection.vue";
import { mapActions, mapState } from "vuex";
import http from "@/common/axios";
const placeStore = "placeStore";
const loginStore = "loginStore";

export default {
  components: { MemberSection },
  data() {
    return {
      // password Change
      userPassword: "",
      userUpdatePassword: "",
      userUpdatePasswordConfirm: "",
    };
  },
  methods: {
    ...mapActions(placeStore, ["getArea1List"]),
    ...mapActions(loginStore, ["getUpdateArea2List"]),
    // area가 place의 state sido_code와 연결되어있음.
    updateArea2List() {
      this.$store.state.loginStore.updateUserGugunCode = "0";
      this.getUpdateArea2List();
    },
    async passwordUpdate() {
      if (this.userUpdatePasswordConfirm != this.userUpdatePassword) {
        alert("새로운 비밀번호 재확인 해주세요.");
        return;
      }

      let formData = new FormData();
      formData.append("userUpdatePassword", this.userUpdatePassword);
      formData.append("userPassword", this.userPassword);
      formData.append(
        "userEmail",
        this.$store.state.loginStore.updateUserEmail
      );
      let options = {
        headers: { "Content-Type": "multipart/form-data" },
      };

      try {
        let { data } = await http.put(
          "/users/" + this.$store.state.loginStore.userSeq + "/password",
          formData,
          options
        );
        console.log(data);
        alert("비밀번호 변경 완료!");
      } catch (error) {
        alert("비밀번호를 확인해 주세요.");
        console.log(error);
      }
    },
    async userUpdate() {
      let formData = new FormData();
      formData.append(
        "userEmail",
        this.$store.state.loginStore.updateUserEmail
      );
      formData.append("userName", this.$store.state.loginStore.updateUserName);
      formData.append(
        "userProfileImageUrl",
        this.$store.state.loginStore.updateUserProfileImageUrl
      );
      formData.append(
        "birthYear",
        this.$store.state.loginStore.updateUserBirthYear
      );
      formData.append(
        "birthMonth",
        this.$store.state.loginStore.updateUserBirthMonth
      );
      formData.append(
        "birthDay",
        this.$store.state.loginStore.updateUserBirthDay
      );
      formData.append(
        "gender",
        this.$store.state.loginStore.updateUserGenderCode
      );
      formData.append(
        "sidoCode",
        this.$store.state.loginStore.updateUserSidoCode
      );
      formData.append(
        "gugunCode",
        this.$store.state.loginStore.updateUserGugunCode
      );
      console.log(this.$store.state.loginStore.userSeq);
      let options = {
        headers: { "Content-Type": "multipart/form-data" },
      };

      try {
        let { data } = await http.put(
          "/users/" + this.$store.state.loginStore.userSeq + "/profile",
          formData,
          options
        );

        this.$store.state.loginStore.userName =
          this.$store.state.loginStore.updateUserName;
        this.$store.state.loginStore.userProfileImageUrl =
          this.$store.state.loginStore.updateUserProfileImageUrl;
        this.$store.state.loginStore.userGenderCode =
          this.$store.state.loginStore.updateUserGenderCode;
        this.$store.state.loginStore.userBirthYear =
          this.$store.state.loginStore.updateUserBirthYear;
        this.$store.state.loginStore.userBirthMonth =
          this.$store.state.loginStore.updateUserBirthMonth;
        this.$store.state.loginStore.userBirthDay =
          this.$store.state.loginStore.updateUserBirthDay;
        this.$store.state.loginStore.userSidoCode =
          this.$store.state.loginStore.updateUserSidoCode;
        this.$store.state.loginStore.userGugunCode =
          this.$store.state.loginStore.updateUserGugunCode;

        // ssesionStorage 처리
        sessionStorage.removeItem("vuex");
        let loginStore = this.$store.state.loginStore;
        let boardStore = this.$store.state.boardStore;
        let store = { loginStore, boardStore };
        const objString = JSON.stringify(store);
        sessionStorage.setItem("vuex", objString);
        sessionStorage.getItem("vuex");

        console.log(data);
        alert("업데이트 햇슴다.");
        // if (data.result == "login") {
        //   this.doLogout();
        // } else {
        //   this.$alertify.success("글이 수정되었습니다.");
        //   this.closeModal();
        // }
      } catch (error) {
        console.log("UpdateModalVue: error ");
        console.log(error);
      }
    },
  },
  computed: {
    ...mapState(placeStore, ["areaList1"]),
    ...mapState(loginStore, ["gugunList"]),
  },
  mounted() {
    this.getArea1List();
    this.getUpdateArea2List();
  },
};
</script>
<style scoped>
</style>