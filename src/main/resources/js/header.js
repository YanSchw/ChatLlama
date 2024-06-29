"use strict";


component('header', (node, state) => {
    createNode('span', node, span => {
        span.addEventListener("click", () => {
            alert('hi');
        });
        span.innerHTML = '&#128218;';
    });
});
