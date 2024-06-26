"use strict";

function prompt(value) {
    fetch(`/api/prompt/0/${encodeURI(value)}`, {
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
                prompt(input.value);
                input.value = '';
            }
         };
    });
    createNode('span', node, span => {
        span.innerHTML = '&#9999;';
    });
});