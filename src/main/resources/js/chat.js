"use strict";

let currentChatID = 'new';

function hasScrolledToBottom() {
    return (window.innerHeight + window.scrollY) >= document.body.scrollHeight - 64;
}

function createNewEmptyChat() {
    let container = select('div.message-container');
    container.innerHTML = '';
}

function fetchChatMessages(chatid) {
    currentChatID = chatid;

    if (chatid == 'new') {
        createNewEmptyChat();
        return;
    }

    fetch(`/api/fetchChat/${chatid}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).then(response => response.json())
      .then(json => {
        let container = select('div.message-container');
        container.innerHTML = '';
        createNode('br', container, br => {});
        createNode('br', container, br => {});
        for (let msg of json.messages) {
            createNode('message', container, message => {
                message.setAttribute('msg', msg.message);
                let float = !msg.isModelMessage ? 'right' : 'left';
                initializeAllComponents();
                message.querySelector('div').style.float = float;
            });
        }
        // Padding
        createNode('br', container, br => {});
        createNode('br', container, br => {});
        createNode('br', container, br => {});
        createNode('br', container, br => {});
        createNode('br', container, br => {});

        if (hasScrolledToBottom()) {
            window.scrollTo(0, document.body.scrollHeight);
        }

        if (json.isPending) {
            setTimeout(() => fetchChatMessages(chatid), 100);
        }
    });
}

component('chat', (node, state) => {
    createNode('innerHTML', node, inner => {});
    createNode('div', node, div => {
        div.classList.add('message-container');
    });
    createNode('chat-input', node, input => {
        
    });
});

fetchChatMessages(currentChatID);
setTimeout(() => window.scrollTo(0, document.body.scrollHeight), 300);