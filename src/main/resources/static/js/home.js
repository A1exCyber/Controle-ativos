function logout() {
        localStorage.removeItem("token");
        window.location.href = "/login";}

document.getElementById("btnCadastro").addEventListener("click", () => {
    window.location.href = "/cadastroProduto";
});

