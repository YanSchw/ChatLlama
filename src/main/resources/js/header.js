"use strict";


component('header', (node, state) => {
    createNode('img', node, img => {
        img.addEventListener("click", () => {
            select("side-bar").classList.toggle("active");
        });
        img.setAttribute('src', 'img/burger.png');
    });
});
