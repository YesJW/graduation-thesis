document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const response = await fetch(`/login_web?username=${username}&password=${password}`);
        if (response.redirected) {
            window.location.href = response.url;
        } else {
            alert('Login failed');
        }
    });
});
