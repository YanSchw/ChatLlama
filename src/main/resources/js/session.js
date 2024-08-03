"use strict";

function getCookieByName(name) {
    let cookieString = document.cookie;
    let cookiesArray = cookieString.split('; ');

    for (let cookie of cookiesArray) {
        let [cookieName, cookieValue] = cookie.split('=');

        if (cookieName === name) {
            return cookieValue;
        }
    }

    return undefined;
}

var sessionToken = getCookieByName('ChatLlama-Session-Token');

fetch(`/auth/isvalid/`, {
    method: 'GET',
    headers: {
        'Accept': 'application/json',
        'Session-Token': `${sessionToken}`
    }
}).then(response => response.json())
  .then(json => {
    if (json.isvalid === true) {
        fetchAllChats();
    } else {
        select('chat').remove();
        select('side-bar').remove();
        select('header').remove();
    }
});