"use strict";

component('message', (node, state) => {
    createNode('div', node, div => {
        div.style.float = Math.random() > 0.5 ? 'right' : 'left';
        createNode('p', div, p => {
            p.innerText = state.msg;
        });
    });
});