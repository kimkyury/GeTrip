<template>
    <div class="modal" tabindex="-1" id="updateModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">글 수정</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <input
                            v-model="storeTitle"
                            type="text"
                            class="form-control"
                            placeholder="제목"
                        />
                    </div>
                    <div class="mb-3">
                        <div id="divEditorUpdate"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button
                        @click="boardUpdate"
                        class="btn btn-sm btn-primary btn-outline"
                        data-dismiss="modal"
                        type="button"
                    >
                        수정
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import VueAlertify from "vue-alertify";

Vue.use(CKEditor).use(VueAlertify);

import http from "@/common/axios.js";
import { mapState, mapMutations } from "vuex";

export default {
    name: "UpdateModal",

    data() {
        return {
            CKEditor: "",
        };
    },

    computed: {
        ...mapState("boardStore", ["title", "content", "boardId"]),
        ...mapMutations("boardStore", ["SET_BOARD_TITLE"]),
        storeTitle: {
            get() {
                return this.title;
            },
            set(title) {
                this.SET_BOARD_TITLE(title);
            },
        },
    },
    methods: {
        // modal 초기화
        initUI() {
            this.CKEditor.setData(this.content);
        },

        async boardUpdate() {
            // post form data
            let formData = new FormData();
            formData.append("boardId", this.boardId);
            formData.append("title", this.title);
            formData.append("content", this.CKEditor.getData());

            let options = {
                headers: { "Content-Type": "multipart/form-data" },
            };

            try {
                let { data } = await http.put(
                    "/boards/" + this.boardId,
                    formData,
                    options
                );

                console.log("UpdateModalVue: data : ");
                console.log(data);

                if (data.result == "login") {
                    this.doLogout();
                } else {
                    this.$alertify.success("글이 수정되었습니다.");
                    this.closeModal();
                }
            } catch (error) {
                console.log("UpdateModalVue: error ");
                console.log(error);
            }
        },
        closeModal() {
            this.$emit("call-parent-update");
        },
        // doLogout() {
        //     this.$store.commit("SET_LOGIN", {
        //         isLogin: false,
        //         userName: "",
        //         userProfileImageUrl: "",
        //     });
        //     this.$router.push("/login");
        // },
    },

    async mounted() {
        try {
            this.CKEditor = await ClassicEditor.create(
                document.querySelector("#divEditorUpdate")
            );
        } catch (error) {
            console.error(error);
        }

        let $this = this;
        this.$el.addEventListener("show.bs.modal", function () {
            $this.initUI();
        });
    },
};
</script>

<style scoped>
.modal >>> .ck-editor__editable {
    min-height: 300px !important;
}

.modal >>> .thumbnail-wrapper {
    margin-top: 5px;
}

.modal >>> .thumbnail-wrapper img {
    width: 100px !important;
    margin-right: 5px;
    max-width: 100%;
}
</style>
