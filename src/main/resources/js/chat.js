"use strict";

function fetchChatMessages(chatid) {
    fetch(`/api/fetchChat/${chatid}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).then(response => response.json())
      .then(json => {
        let container = select('div.message-container');
        container.innerHTML = '';
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
        
        if (json.isPending) {
            setTimeout(() => fetchChatMessages(chatid), 100);
        }
    });
}

component('chat', (node, state) => {
    createNode('innerHTML', node, inner => {});
    createNode('div', node, div => {
        div.classList.add('message-container');
        createNode('message', div, message => {
            message.setAttribute('msg', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic incidunt nihil quam');
            
        });
        createNode('message', div, message => {
            message.setAttribute('msg', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic incidunt nihil quam');
        });
    });
    createNode('chat-input', node, input => {
        
    });
});

fetchChatMessages(1);