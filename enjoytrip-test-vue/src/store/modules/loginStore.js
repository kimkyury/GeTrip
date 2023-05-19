const loginStore = {
    namespaced: true,
    state: {
        isLogin: false,

        userSeq: "",
        userName: "",
        userProfileImageUrl: "",
        userEmail: "",
        userRegisterDate: "",

        userGenderCode: "",
        userGenderName: "",

        userBirthYear: "",
        userBirthMonth: "",
        userBirthDay: "",

        // 아래는 공통코드에서 fk로 이름을 가져와야함
        userSidoCode: "", // sido Table
        userSidoName: "",

        userGugunCode: "",
        userGugunName: "",

        userClsfCode: "",
        userClsfName: "", // group_code table
    },
    getters: {
        IsLogin: function (state) {
            return state.isLogin;
        },
    },
    actions: {},
    mutations: {
        SET_LOGIN(state, payload) {
            state.isLogin = payload.isLogin;

            state.userSeq = payload.userSeq;
            state.userName = payload.userName;
            state.userProfileImageUrl = payload.userProfileImageUrl;
            state.userEmail = payload.userEmail;

            state.userRegisterDate = payload.userRegisterDate;

            state.userGenderCode = payload.userGenderCode;
            state.userGenderName = payload.userGenderName;
            state.userBirthYear = payload.userBirthYear;
            state.userBirthMonth = payload.userBirthMonth;
            state.userBirthDay = payload.userBirthDay;

            state.userSidoCode = payload.userSidoCode;
            state.userSidoName = payload.userSidoName;
            state.userGugunCode = payload.userGugunCode;
            state.userGugunName = payload.userGugunName;
            state.userClsfCode = payload.userClsfCode;
            state.userClsfName = payload.userClsfName;
        },
        SET_LOGOUT(state) {
            state.isLogin = false;
            state.userSeq = "";
            state.userName = "";
            state.userProfileImageUrl = "";
            state.userEmail = "";
            state.userRegisterDate = "";
            state.userGenderCode = "";
            state.userGenderName = "";
            state.userBirthYear = "";
            state.userBirthMonth = "";
            state.userBirthDay = "";
            state.userSidoCode = "";
            state.userSidoName = "";
            state.userGugunCode = "";
            state.userGugunName = "";
            state.userClsfCode = "";
            state.userClsfName = "";
        },
    },
};

export default loginStore;
