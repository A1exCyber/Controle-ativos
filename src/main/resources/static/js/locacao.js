const params = new URLSearchParams(window.location.search);
const id = Number(params.get("id"));

const produtos = JSON.parse(localStorage.getItem("produtos")) || [];
const produto = produtos.find(p => p.id === id);
