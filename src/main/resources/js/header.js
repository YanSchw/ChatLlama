"use strict";


component('header', (node, state) => {
    createNode('span', node, span => {
        span.addEventListener("click", () => {
            select("side-bar").classList.toggle("active");
        });
        span.innerHTML = '&#128218;';
    });
});
