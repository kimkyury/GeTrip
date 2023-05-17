<template>
    <div id="app">
        <nav-bar></nav-bar>
        <nav-bar-page-move></nav-bar-page-move>
        <router-view></router-view>
    </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import NavBarPageMove from "@/components/NavBarPageMove.vue";

// import HomePage from "@/components/HomePage.vue";

export default {
    name: "App",
    components: {
        NavBar,
        NavBarPageMove,
    },
};
</script>

<style></style>
