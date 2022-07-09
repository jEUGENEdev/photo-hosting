let lastId = 0;

function generatePhoto(like_count, path, id, liked) {
    let photo = document.createElement("div");
    photo.className = "photo";
    photo.innerHTML = `<img src='${path}' width='410' height='251' alt onclick='window.location.href = this.src;' style='cursor: pointer;'>` +
        "<div style='display:flex; background: #641FE8; width: 414px; border-radius: 5px; padding: 5px; justify-content: space-between; align-items: center;'>" +
        `<div style='display:flex; align-items: center;'><a style='font-size: 18px; padding-top: 5px; margin-right: 5px;'>${like_count}</a><img onclick='likePhoto(this)' src='/static/images/${liked === 0 ? "like.png" : "shaded_like.png"}' alt style='cursor: pointer;'><input type="hidden" name="id" value="${id}"></div>` +
        "<div onclick='sharePhoto(this)'><a style='font-size: 18px; padding-top: 5px; margin-right: 5px; cursor: pointer;'>Поделиться</a><img src='/static/images/share.png' alt></div>" +
        "</div>";
    return photo;
}

function likePhoto(photo) {
    if(document.getElementById("account").innerText === "Выход") {
        let like_count = photo.parentNode.getElementsByTagName("a")[0];
        let id = photo.parentNode.getElementsByTagName("input")[0].value;
        fetch(`/photos/n/like?id=${id}`).then(value => value.json()).then(data => {
            if (data.type === "like") {
                like_count.innerText = Number(like_count.innerText) + 1;
                photo.src = "/static/images/shaded_like.png";
            }
            else {
                like_count.innerText = Number(like_count.innerText) - 1;
                photo.src = "/static/images/like.png";
            }
        });
    }
    else
        window.location.href = "/login";
}

function sharePhoto(photo) {
    navigator.clipboard.writeText(photo.parentNode.parentNode.getElementsByTagName("img")[0].src)
        .then(() => alert("Ссылка на фотографию была скопирована."), () => alert("Не удалось скопировать ссылку на фото."));
}

function loadPhoto() {
    let photos = document.getElementById("photos");
    fetch(`/photos/get?offset=${lastId}&count=10`, {method: "POST"}).then(value => value.json()).then(data => {
        if(data.status === 'ok') {
            let items = data.items;
            for (let i = 0; i < items.length; i++) {
                photos.append(generatePhoto(items[i].like_count, `${window.location.origin}/upload/photo/${items[i].name}`, items[i].id, Number(items[i].liked)));
                lastId = items[i].id;
            }
        }
        else
            alert("Не удалось загрузить фотографии.");
    });
}

document.getElementsByClassName("top-panel absolute")[0].addEventListener("click", () => {
    if(document.getElementById("account").innerText === "Вход")
        window.location.href = "/login";
    else
        document.getElementById("logout").submit();
});

loadPhoto();