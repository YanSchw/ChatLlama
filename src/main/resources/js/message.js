"use strict";

component('message', (node, state) => {
    createNode('div', node, div => {
        createNode('p', div, p => {
            p.innerText = state.msg;
        });
    });
});