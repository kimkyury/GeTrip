<template>
    <div id="app">
        <nav-bar></nav-bar>
        <router-view></router-view>
    </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
// import HomePage from "@/components/HomePage.vue";

export default {
    name: "App",
    components: {
        NavBar,
    },
};
</script>

<style></style>
