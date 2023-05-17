<template>
    <div>
        <section class="py-5">
            <div class="container py-4">
                <div class="row gy-5">
                    <div class="col-lg-9">
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
                                    />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 mb-3">
                                    <label class="form-label" for="password_1">새 비밀번호: </label>
                                    <input
                                        class="form-control"
                                        id="password_1"
                                        type="password"
                                        name="password_1"
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
                                    />
                                </div>
                                <div class="col-lg-12 text-center">
                                    <button class="btn btn-outline-primary" type="button">
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
                                    <label class="form-label" for="fullName">이름</label>
                                    <input
                                        class="form-control"
                                        id="fullName"
                                        type="text"
                                        name="fullName"
                                    />
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label class="form-label" for="fullName"> 이메일 </label>
                                    <input
                                        class="form-control"
                                        id="fullName"
                                        type="text"
                                        name="fullName"
                                    />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3 col-lg-3">
                                    <label class="form-label" for="age"> 나이 </label>
                                    <input class="form-control" id="age" type="text" name="age" />
                                </div>

                                <div class="col-lg-12 text-center">
                                    <button class="btn btn-outline-primary" type="button">
                                        <i class="fas fa-save me-2"></i> 저장
                                    </button>
                                </div>
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

export default {
    components: { MemberSection },
};
</script>
