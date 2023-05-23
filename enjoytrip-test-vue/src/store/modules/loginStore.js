import http from "@/common/axios"
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

        // update
        updateUserEmail: "",
        updateUserName: "",
        updateUserProfileImageUrl: "",
        updateUserGenderCode: "",
        updateUserBirthYear: "",
        updateUserBirthMonth: "",
        updateUserBirthDay: "",
        updateUserSidoCode: "",
        updateUserGugunCode: "",
        gugunList: [],
        // path로 이미지 표시할 때 require(path) 식으로 src에 줘야함.
        // vue파일에서는 실행 안됨..
        // profileImage 선언시 처음부터 require하면 이미지 표시되지만 주소가 바뀜
        // require해서 표시하는 방법 알아봐야함.
        profileImage:[
            {
                name : "박쥐",
                path : "@/assets/img/animal/bat.png",
                imageView : require('@/assets/img/animal/bat.png'),
            },
            {
                name : "곰",
                path : "@/assets/img/animal/bear.png",
                imageView : require("@/assets/img/animal/bear.png"),
            },
            {
                name : "벌",
                path : "@/assets/img/animal/bee.png",
                imageView : require("@/assets/img/animal/bee.png"),
            },
            {
                name : "새",
                path : "@/assets/img/animal/bird.png",
                imageView : require("@/assets/img/animal/bird.png"),
            },
            {
                name : "벌레",
                path : "@/assets/img/animal/bug.png",
                imageView : require("@/assets/img/animal/bug.png"),
            },
            {
                name : "나비",
                path : "@/assets/img/animal/butterfly.png",
                imageView : require("@/assets/img/animal/butterfly.png"),
            },
            {
                name : "낙타",
                path : "@/assets/img/animal/camel.png",
                imageView : require("@/assets/img/animal/camel.png"),
            },
            {
                name : "고양이",
                path : "@/assets/img/animal/cat.png",
                imageView : require("@/assets/img/animal/cat.png"),
            },
            {
                name : "치타",
                path : "@/assets/img/animal/cheetah.png",
                imageView : require("@/assets/img/animal/cheetah.png"),
            },
            {
                name : "닭",
                path : "@/assets/img/animal/chicken.png",
                imageView : require("@/assets/img/animal/chicken.png"),
            },
            {
                name : "코알라",
                path : "@/assets/img/animal/coala.png",
                imageView : require("@/assets/img/animal/coala.png"),
            },
            {
                name : "소",
                path : "@/assets/img/animal/cow.png",
                imageView : require("@/assets/img/animal/cow.png"),
            },
            {
                name : "악어",
                path : "@/assets/img/animal/crocodile.png",
                imageView : require("@/assets/img/animal/crocodile.png"),
            },
            {
                name : "공룡",
                path : "@/assets/img/animal/dinosaur.png",
                imageView : require("@/assets/img/animal/dinosaur.png"),
            },
            {
                name : "개",
                path : "@/assets/img/animal/dog.png",
                imageView : require("@/assets/img/animal/dog.png"),
            },
            {
                name : "돌고래",
                path : "@/assets/img/animal/dolphin.png",
                imageView : require("@/assets/img/animal/dolphin.png"),
            },
            {
                name : "비둘기",
                path : "@/assets/img/animal/dove.png",
                imageView : require("@/assets/img/animal/dove.png"),
            },
            {
                name : "오리",
                path : "@/assets/img/animal/duck.png",
                imageView : require("@/assets/img/animal/duck.png"),
            },
            {
                name : "독수리",
                path : "@/assets/img/animal/eagle.png",
                imageView : require("@/assets/img/animal/eagle.png"),
            },
            {
                name : "코끼리",
                path : "@/assets/img/animal/elephant.png",
                imageView : require("@/assets/img/animal/elephant.png"),
            },
            {
                name : "물고기",
                path : "@/assets/img/animal/fish.png",
                imageView : require("@/assets/img/animal/fish.png"),
            },
            {
                name : "플라밍고",
                path : "@/assets/img/animal/flamingo.png",
                imageView : require("@/assets/img/animal/flamingo.png"),
            },
            {
                name : "여우",
                path : "@/assets/img/animal/fox.png",
                imageView : require("@/assets/img/animal/fox.png"),
            },
            {
                name : "개구리",
                path : "@/assets/img/animal/frog.png",
                imageView : require("@/assets/img/animal/frog.png"),
            },
            {
                name : "고릴라",
                path : "@/assets/img/animal/gorilla.png",
                imageView : require("@/assets/img/animal/gorilla.png"),
            },
            {
                name : "말",
                path : "@/assets/img/animal/horse.png",
                imageView : require("@/assets/img/animal/horse.png"),
            },
        ]
    },
    getters: {
        IsLogin: function (state) {
            return state.isLogin;
        },
    },
    actions: {
    async getUpdateArea2List({ state }){
        let { data } = await http.get("/codes/" + state.updateUserSidoCode);
        state.gugunList = data.list;
      },
    },
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

            // update
            state.updateUserName = payload.userName;
            state.updateUserProfileImageUrl = payload.userProfileImageUrl;
            state.updateUserEmail = payload.userEmail;
            state.updateUserGenderCode = payload.userGenderCode;
            state.updateUserBirthYear = payload.userBirthYear;
            state.updateUserBirthMonth = payload.userBirthMonth;
            state.updateUserBirthDay = payload.userBirthDay;
            state.updateUserSidoCode = payload.userSidoCode;
            state.updateUserGugunCode = payload.userGugunCode;


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
