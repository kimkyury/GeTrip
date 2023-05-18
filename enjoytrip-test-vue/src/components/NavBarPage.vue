<template>
    <header class="nav-holder make-sticky">
        <div class="navbar navbar-light bg-white navbar-expand-lg py-0" id="navbar">
            <div class="container py-3 py-lg-0 px-lg-0">
                <!-- Navbar brand--><a class="navbar-brand" href="index.html"
                    ><img
                        class="d-none d-md-inline-block"
                        src="img/logo.png"
                        alt="Universal logo"
                    /><img
                        class="d-inline-block d-md-none"
                        src="img/logo-small.png"
                        alt="Universal logo"
                    /><span class="sr-only">Universal - go to homepage</span></a
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
                        <!-- homepage dropdown-->
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle active"
                                id="hpDropdown"
                                href="#"
                                role="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                >Home</a
                            >
                        </li>

                        <!-- dropdown menu [contact]-->
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle"
                                id="contactMegamenu"
                                href="#"
                                role="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                >Contact</a
                            >
                        </li>
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle"
                                id="contactMegamenu"
                                href="#"
                                role="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                >Contact</a
                            >
                        </li>
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle"
                                id="contactMegamenu"
                                href="#"
                                role="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                >Contact</a
                            >
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
</template>
