import Vue from "vue";
import App from "@/App.vue";
import VueMeta from "vue-meta";
import router from "@/routers/routers.js";
import store from "@/store/store.js";

// from bootstrap 5@/store/modules/boardStore.js
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import "swiper/swiper-bundle.css";

Vue.use(VueMeta, {
    // optional pluginOptions
    refreshOnceOnNavigation: true,
});

Vue.config.productionTip = false;

new Vue({
    render: (h) => h(App),
    router,
    store,
}).$mount("#app");
