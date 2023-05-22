<template>
    <div class="modal" tabindex="-1" id="detailModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">글 상세</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>
                <div class="modal-body">
                    <table class="table table-hover">
                        <tbody>
                            <tr>
                                <td>글번호</td>
                                <td>{{ boardId }}</td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td>{{ title }}</td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td v-html="content"></td>
                            </tr>
                            <tr>
                                <td>작성자</td>
                                <td>{{ userName }}</td>
                            </tr>

                            <tr>
                                <td>작성일시</td>
                                <td>
                                    {{ regDate }}
                                    {{ regTime }}
                                </td>
                            </tr>
                            <tr>
                                <td>조회수</td>
                                <td>{{ readCount }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button
                        v-show="sameUser"
                        @click="changeToUpdate"
                        class="btn btn-sm btn-primary btn-outline"
                        data-dismiss="modal"
                        type="button"
                    >
                        글 수정하기
                    </button>
                    <button
                        v-show="sameUser"
                        @click="changeToDelete"
                        class="btn btn-sm btn-warning btn-outline"
                        data-dismiss="modal"
                        type="button"
                    >
                        글 삭제하기
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    computed: {
        ...mapState("boardStore", [
            "sameUser",
            "boardId",
            "title",
            "content",
            "userName",
            "regDate",
            "regTime",
            "readCount",
        ]),
    },

    methods: {
        changeToUpdate() {
            this.$emit("call-parent-change-to-update");
        },
        changeToDelete() {
            this.$emit("call-parent-change-to-delete");
        },
    },
};
</script>

<style></style>
