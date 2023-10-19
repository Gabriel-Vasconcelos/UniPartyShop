function toggleFormDisplay(action) {
    const loginButton = document.getElementById("loginButton");
    const registerButton = document.getElementById("registerButton");
    const loginForm = document.querySelector(".login-register-forms form.login");
    const registerForm = document.querySelector(".login-register-forms form.register");

    if (action === "login") {
        loginButton.classList.add("clicked");
        registerButton.classList.remove("clicked");
        loginForm.style.display = "flex";
        registerForm.style.display = "none";
    } else if (action === "register") {
        loginButton.classList.remove("clicked");
        registerButton.classList.add("clicked");
        loginForm.style.display = "none";
        registerForm.style.display = "flex";
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const action = urlParams.get("action");
    toggleFormDisplay(action);

    loginButton.addEventListener("click", () => {
        updateUrlParameter("action", "login");
        toggleFormDisplay("login");
    });

    registerButton.addEventListener("click", () => {
        updateUrlParameter("action", "register");
        toggleFormDisplay("register");
    });
});

const updateUrlParameter = (key, value) => {
    const url = window.location.href;
    const separator = url.indexOf("?") !== -1 ? "&" : "?";
    const updatedUrl = '?' + url.replace(new RegExp(key + "=.*?(?=&|$)|^.*?$"), key + "=" + value);
    window.history.replaceState({}, document.title, updatedUrl);
}

