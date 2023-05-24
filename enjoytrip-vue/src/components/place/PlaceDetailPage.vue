<template>
  <div>
    <!-- BRIEF SECTION-->
    <section>
      <div class="container py-4">
        <header class="mb-5">
          <h2 class="text-uppercase lined mb-4">
            {{ $store.state.placeStore.title }}
          </h2>
        </header>
        <div class="row gy-4">
          <div class="col-8">
            <div class="swiper-container portfolio-slider">
              <div class="swiper-wrapper">
                <div class="swiper-slide mb-5">
                  <img
                    v-if="$store.state.placeStore.firstImage == ''"
                    src="@/img/ssafy_logo.png"
                    alt="..."
                  />
                  <img
                    v-else
                    :src="$store.state.placeStore.firstImage"
                    alt="..."
                  />
                </div>
              </div>
              <div class="swiper-pagination"></div>
            </div>
          </div>
          <div class="col-4">
            <h4 class="h5 text-uppercase">주소</h4>
            <p class="text-muted text-sm">
              {{ $store.state.placeStore.addr1 }}
            </p>
            <h4 class="h5 text-uppercase">Services</h4>
            <p class="text-muted text-sm">Consulting, Webdesign, Print</p>
            <h4 class="h5 text-uppercase">Technologies</h4>
            <p class="text-muted text-sm">PHP, HipHop, Break-dance</p>
            <h4 class="h5 text-uppercase">Dates</h4>
            <p class="text-muted text-sm">10/2013 - 06/2014</p>
          </div>
        </div>
      </div>
    </section>
    <!-- DESCRIPTION SECTION        -->
    <section class="pb-5">
      <div class="container">
        <h3 class="text-uppercase lined mb-4">상세설명</h3>
        <p class="text-muted">
          {{ $store.state.placeStore.overview }}
        </p>
      </div>
    </section>
  </div>
</template>
<script>
export default {};
</script>
<style scoped>
</style>