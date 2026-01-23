function logout() {
        localStorage.removeItem("token");
        window.location.href = "/index";}

document.getElementById("btnCadastro").addEventListener("click", () => {
    window.location.href = "/cadastroProduto";
});

