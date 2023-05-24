function loadNavbar() {
  fetch("navbar.html")
    .then((response) => {
      if (response.status === 200) {
        return response.text();
      } else {
        throw new Error("Unable to load the navbar");
      }
    })
    .then((html) => {
      document.getElementById("navbar-placeholder").innerHTML = html;
    })
    .catch((error) => {
      console.error("Error fetching navbar:", error);
    });
}
function loadFooter() {
  fetch("footer.html")
    .then((response) => {
      if (response.status === 200) {
        return response.text();
      } else {
        throw new Error("Unable to load the footer");
      }
    })
    .then((html) => {
      document.getElementById("footer-placeholder").innerHTML = html;
    })
    .catch((error) => {
      console.error("Error fetching footer:", error);
    });
}

window.onload = function () {
  let before = document.querySelector("#beforeLogin");
  let after = document.querySelector("#afterLogin");
  let logoutBtn = document.querySelector("#logoutBtn");

  // logoutBtn.onclick = function () {
  //   after.classList.add("d-none");
  //   before.classList.remove("d-none");
  // };
};
