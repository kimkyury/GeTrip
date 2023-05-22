<template>
    <section class="py-5">
        <div class="container py-4">
            <div class="row g-5">
                <div class="col-lg-9">
                    <h1>My Favorite Attraction</h1>
                    <p class="lead mb-5">당신이 찜한 여행지 목록입니다.</p>
                    <div class="row gy-5 align-items-stretch">
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product3.jpg"
                                            alt="Black Blouse Versace"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html"
                                            >Black Blouse Versace</a
                                        >
                                    </h3>
                                    <p class="mb-0">$143.00</p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0"></ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product4.jpg"
                                            alt="Black Blouse Versace"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html"
                                            >Black Blouse Versace</a
                                        >
                                    </h3>
                                    <p class="mb-0">$143.00</p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0"></ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product3.jpg"
                                            alt=" White Blouse Armani"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html">
                                            White Blouse Armani</a
                                        >
                                    </h3>
                                    <p class="mb-0">
                                        <del class="text-gray-500 me-2">$200.00</del>$143.00
                                    </p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0">
                                    <li class="mb-1">
                                        <div class="ribbon sale ribbon-primary">SALE</div>
                                    </li>
                                    <li class="mb-1">
                                        <div class="ribbon new ribbon-info">NEW</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product4.jpg"
                                            alt="White Blouse Versace"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html"
                                            >White Blouse Versace</a
                                        >
                                    </h3>
                                    <p class="mb-0">$143.00</p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0">
                                    <li class="mb-1">
                                        <div class="ribbon new ribbon-info">NEW</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product2.jpg"
                                            alt="White Blouse Versace"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html"
                                            >White Blouse Versace</a
                                        >
                                    </h3>
                                    <p class="mb-0">$143.00</p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0">
                                    <li class="mb-1">
                                        <div class="ribbon new ribbon-info">NEW</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!-- Product-->
                            <div class="product h-100">
                                <div class="product-image">
                                    <a href="shop-detail.html"
                                        ><img
                                            class="img-fluid"
                                            src="img/product1.jpg"
                                            alt="Fur coat"
                                    /></a>
                                </div>
                                <div class="py-4 px-3 text-center">
                                    <h3 class="h5 text-uppercase mb-3">
                                        <a class="reset-link" href="shop-detail.html">Fur coat</a>
                                    </h3>
                                    <p class="mb-0">$143.00</p>
                                </div>
                                <ul class="list-unstyled p-0 ribbon-holder mb-0"></ul>
                            </div>
                        </div>
                    </div>
                </div>

                <member-section></member-section>
            </div>
        </div>
    </section>
</template>
<script>
import MemberSection from "./MemberSection.vue";

export default {
    components: { MemberSection },
};
</script>
