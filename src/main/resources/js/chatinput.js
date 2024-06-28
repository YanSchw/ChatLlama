"use strict";

function prompt(chatid, value) {
    fetch(`/api/prompt/${chatid}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'prompt': `${value}`
        }
    }).then(response => response.json())
      .then(json => {
        fetchChatMessages(json.chatid);
        setTimeout(() => window.scrollTo(0, document.body.scrollHeight), 300);
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
                prompt(currentChatID, input.value);
                input.value = '';
            }
         };
    });
    createNode('span', node, span => {
        span.innerHTML = '&#9999;';
    });
});