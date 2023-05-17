<template>
  <div class="top-bar py-2" id="topBar" style="background: #555">
    <div class="container px-lg-0 text-light py-1">
      <div class="row d-flex align-items-center">
        <div class="col-md-6 d-md-block d-none">
          <p class="mb-0 text-xs">
            Contact us on +420 777 555 333 or hello@universal.com.
          </p>
        </div>
        <div class="col-md-6">
          <div class="d-flex justify-content-md-end justify-content-between">
            <ul class="list-inline d-block d-md-none mb-0">
              <li class="list-inline-item">
                <a class="text-xs" href="#"><i class="fa fa-phone"></i></a>
              </li>
              <li class="list-inline-item">
                <a class="text-xs" href="#"><i class="fa fa-envelope"></i></a>
              </li>
            </ul>

            <ul class="list-inline mb-0" v-if="isLogin">
                {{ $store.state.login.isLogin }}
              <li class="list-inline-item">
                <a
                  class="text-xs text-uppercase fw-bold text-reset"
                  @click="logout()"
                  ><i class="fas fa-door-open me-2"></i
                  ><span class="d-none d-md-inline-block">LogOut</span></a
                >
              </li>
            </ul>
            <ul class="list-inline mb-0" v-else>
              <li class="list-inline-item">
                <a class="text-xs text-uppercase fw-bold text-reset" href="#"
                  ><i class="fas fa-door-open me-2"></i
                  ><span class="d-none d-md-inline-block">Sign In</span></a
                >
              </li>
              <li class="list-inline-item">
                <a
                  class="text-xs text-uppercase fw-bold text-reset"
                  href="#/signup"
                  ><i class="fas fa-user me-2"></i
                  ><span class="d-none d-md-inline-block">Sign Up</span></a
                >
              </li>
            </ul>
            <ul class="list-inline mb-0 ms-lg-4">
              <li class="list-inline-item text-gray-600 m-0">
                <a class="text-xs social-link-hover" href="#" title="Facebook"
                  ><i class="fab fa-facebook-f"></i
                ></a>
              </li>
              <li class="list-inline-item text-gray-600 m-0">
                <a class="text-xs social-link-hover" href="#" title="Twitter"
                  ><i class="fab fa-twitter"></i
                ></a>
              </li>
              <li class="list-inline-item text-gray-600 m-0">
                <a class="text-xs social-link-hover" href="#" title="Linkedin"
                  ><i class="fab fa-linkedin-in"></i
                ></a>
              </li>
              <li class="list-inline-item text-gray-600 m-0">
                <a class="text-xs social-link-hover" href="#" title="Email"
                  ><i class="fas fa-envelope"></i
                ></a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
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
  methods: {
    logout() {
      this.$store.commit("login/SET_LOGOUT");
      this.$router.push("/signup");
    },
  },
  computed:{
    isLogin(){
        return this.$store.state.login.isLogin;
    }
  }
};
</script>
<style scoped></style>
