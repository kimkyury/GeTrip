<template>
    <header class="nav-holder make-sticky ex01">
        <div class="navbar navbar-light bg-white navbar-expand-lg py-0" id="navbar">
            <div class="container py-3 py-lg-0 px-lg-0">
                <!-- Navbar brand--><a class="navbar-brand" href="index.html"
                    ><div
                        v-for="(image, index) in $store.state.loginStore.profileImage"
                        :key="index"
                    >
                        <img
                            v-if="
                                image.path == $store.state.loginStore.userProfileImageUrl
                            "
                            class="d-none d-md-inline-block"
                            :src="image.imageView"
                            alt="Universal logo"
                            style="width: 60px; height: 60px"
                        />
                    </div>
                    <span class="sr-only">Universal - go to homepage</span></a
                >
                <!-- Navbar toggler-->
                <button
                    class="navbar-toggler text-primary border-primary"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navigationCollapse"
                    aria-controls="navigationCollapse"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span class="sr-only">Toggle navigation</span
                    ><i class="fas fa-align-justify"></i>
                </button>
                <!-- Collapsed Navigation    -->
                <div class="collapse navbar-collapse" id="navigationCollapse">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <!-- megamenu [features]-->
                        <router-link class="nav-link" :to="{ name: 'Home' }">
                            HOME</router-link
                        >

                        <router-link class="nav-link" :to="{ name: 'BoardMain' }">
                            Board</router-link
                        >
                        <router-link class="nav-link" :to="{ name: 'PlacePage' }">
                            Place</router-link
                        >
                        <router-link class="nav-link" :to="{ name: 'MyPage' }">
                            MyPage</router-link
                        >
                    </ul>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
export default {};
</script>

<style scoped>
.image-container {
    width: 350px;
    height: 250px;
    overflow: hidden;
}
.fit-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
}

.ex01 {
    box-shadow: 0px 7px 10px -4px rgb(161, 161, 161);
}
</style>
