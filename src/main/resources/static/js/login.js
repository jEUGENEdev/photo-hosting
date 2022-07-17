let header = document.getElementById("header");
header.addEventListener("click", () => {
    window.location.href = "/";
});

function vkDef(el) {
    alert("Да!!!");
    el.onclick = null;
    el.style.color = "blue";
    setTimeout(() => el.href = "/help/bezopasno-ili-neochen", 500);
}