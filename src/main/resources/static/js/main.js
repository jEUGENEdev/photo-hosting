function showDialog() {
    let body = document.getElementsByTagName("body")[0];
    let dialog = document.createElement("div");
    dialog.id = "dialog";
    dialog.style = "opacity: 0;";
    dialog.innerHTML = "<a style='align-self: end; font-size: 30px; cursor: pointer;' onclick='dismissDialog()'>☓</a>" +
                        "<p id='p1'>Поздравляем! Вы только что успешно загрузили фотогорафию.</p>" +
                        "<p id='p2'>Вы можете перейти в <a href='/archive' style='color: white;'><b>архив</b></a>, чтобы посмотреть все загруженные фотографии</p>";
    body.append(dialog);
    let anim = dialog.animate({"opacity": 1}, 1000);
    anim.onfinish = () => dialog.style = "opacity: 1;";
}

function dismissDialog() {
    let body = document.getElementsByTagName("body")[0];
    let dialog = document.getElementById("dialog");
    body.removeChild(dialog);
}

$(document).ready(function() {
    let $element = $('#bubble-text');
    let phrases = [
        'I NEED A NEW PHOTO',
        'FIND ME A PROFILE PICTURE',
        'HELP ME CHOOSE A PHOTO',
    ];
    let index = -1;
    (function loopAnimation() {
        index = (index + 1) % phrases.length;
        bubbleText({
            element: $element,
            newText: phrases[index],
            letterSpeed: 70,
            callback: function() {
                setTimeout(loopAnimation, 3000);
            },
        });
    })();
    let $upPhoto = $('#upload_photo');
    $($upPhoto).change(() => {
        $upPhoto.submit();
    });
    let login = document.getElementById("login");
    if(login != null)
        login.addEventListener("click", () => window.location.href = "/login");
    let params = new URLSearchParams(window.location.search);
    let path = params.get("path");
    if(path != null)
        showDialog();
});