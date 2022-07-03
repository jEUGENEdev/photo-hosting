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
});