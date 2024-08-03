"use strict";

function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

component('login', (node, state) => {
    createNode('div', node, div => {
        div.addEventListener('click', function() {
            fetch(`/auth/createGuestAccount`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json'
                }
            }).then(response => response.json())
              .then(json => {
                setCookie('ChatLlama-Session-Token', json.sessionToken, 1);
                location.reload();
            });
        });
    });
});