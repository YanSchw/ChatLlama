"use strict";

function prompt(chatid, value) {
    fetch(`/api/prompt/${chatid}/${encodeURI(value)}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    });
}

component('chat-input', (node, state) => {
    createNode('span', node, span => {
        span.innerHTML = '&#x1F999';
    });
    createNode('input', node, input => {
        input.setAttribute('type', 'text');
        input.setAttribute('placeholder', 'Ask ChatLlama anything...');
        input.onkeydown = function(e) {
            if(e.keyCode == 13){
                prompt(1, input.value);
                setTimeout(() => fetchChatMessages(1), 200);
                setTimeout(() => window.scrollTo(0, document.body.scrollHeight), 300);
                input.value = '';
            }
         };
    });
    createNode('span', node, span => {
        span.innerHTML = '&#9999;';
    });
});