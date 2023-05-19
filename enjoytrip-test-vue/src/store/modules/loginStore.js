
const loginStore ={
    namespaced : true,
    state:{
        isLogin: false,

            userName: "",
            userProfileImageUrl: "",
            userEmail: "",
            userPassword: "",
            userRegisterDate: "",

            /* 0518 Add */
            birthYear: "",
            birthMonth: "",
            birthDay: "",

            // 아래는 공통코드에서 fk로 이름을 가져와야함
            gender: "", // gender table
            sido: "", // sido Table
            gugun: "", // gugun Table
            userClsfName: "", // group_code table
    },
    getters:{
        IsLogin: function (state) {
            return state.isLogin;
        },
    },
    actions:{},
    mutations:{
        SET_LOGIN(state, payload) {
            state.isLogin = payload.isLogin;
            state.userName = payload.userName;
            state.userProfileImageUrl = payload.userProfileImageUrl;

            // 0518 ADD
            state.birthYear = payload.birthYear;
            state.birthYear = payload.birthYear;
            state.birthMonth = payload.birthMonth;
            state.birthDay = payload.birthDay;

            state.gender = payload.gender;

            state.sido = payload.sido;
            state.gugun = payload.gugun;
            state.userClsfName = payload.userClsfName;
        },
        SET_LOGOUT(state) {
            state.isLogin = false;
            state.userName = "";
            state.userProfileImageUrl = "";

            // 0518 ADD
            state.birthYear = "";
            state.birthYear = "";
            state.birthMonth = "";
            state.birthDay = "";

            state.gender = "";

            state.sido = "";
            state.gugun = "";
            state.userClsfName = "";
        },
    }
}

export default loginStore;