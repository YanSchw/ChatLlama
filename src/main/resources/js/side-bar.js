"use strict";

function fetchAllChats() {
    
    fetch(`/api/allChats`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).then(response => response.json())
      .then(json => {
        let container = select('div.chats-container');
        container.innerHTML = '';
        for (let chat of json.chats) {
            createNode('nav-chat', container, nav => {
                nav.innerHTML = chat.title;
                nav.addEventListener("click", () => {
                    select("side-bar").classList.toggle("active");
                    fetchChatMessages(chat.chatid);
                    window.scrollTo(0, 0);
                });
            });
        }

    });
}

fetchAllChats();