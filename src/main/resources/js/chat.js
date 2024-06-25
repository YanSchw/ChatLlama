"use strict";

component('chat', (node, state) => {
    createNode('ul', node, ul => {
        createNode('li', ul, li => {
            li.innerText = "Message 1";
        });
        createNode('li', ul, li => {
            li.innerText = "Message 2";
        });
    });
    createNode('chat-input', node, input => {
        
    });
});