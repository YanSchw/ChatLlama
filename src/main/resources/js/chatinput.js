"use strict";

function prompt(value) {
    alert(value);
}

component('chat-input', (node, state) => {
    createNode('div', node, div => {
        createNode('input', div, input => {
            input.setAttribute('type', 'text');
            input.setAttribute('placeholder', 'Enter your prompt here');
            input.onkeydown = function(e) {
                if(e.keyCode == 13){
                    prompt(input.value);
                    input.value = '';
                }
             };
        });
    });
});