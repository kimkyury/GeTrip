<template>
    <div id="app">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" />
        <!-- Theme stylesheet-->
        <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet" />
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="css/custom.css" />

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
    methods: {
        injectSvgSprite(path) {
            var ajax = new XMLHttpRequest();
            ajax.open("GET", path, true);
            ajax.send();

            // eslint-disable-next-line no-unused-vars
            ajax.onload = function (e) {
                var div = document.createElement("div");
                div.className = "d-none";
                div.innerHTML = ajax.responseText;
                document.body.insertBefore(div, document.body.childNodes[0]);
            };
        },
    },

    mounted() {
        this.injectSvgSprite("https://bootstraptemple.com/files/icons/orion-svg-sprite.svg");
    },

    metaInfo: {
        link: [
            {
                rel: "stylesheet",
                href: "https://fonts.googleapis.com/css?family=Roboto:300,400,700",
            },
            {
                rel: "stylesheet",
                href: "https://use.fontawesome.com/releases/v5.7.1/css/all.css",
                integrity:
                    "sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr",
                crossorigin: "anonymous",
            },
        ],
    },
};
</script>

<style></style>
