"use strict";

function processMarkdown(markdown) {
    // Convert newlines to <br>
    let html = markdown.replace(/\n/g, '<br>');

    // Convert **bold** to <b>bold</b>
    html = html.replace(/\*\*(.*?)\*\*/g, '<b>$1</b>');

    // Convert _italic_ and *italic* to <i>italic</i>
    html = html.replace(/_(.*?)_/g, '<i>$1</i>');
    html = html.replace(/\*(.*?)\*/g, '<i>$1</i>');

    return html;
}

component('message', (node, state) => {
    createNode('div', node, div => {
        createNode('p', div, p => {
            p.innerHTML = processMarkdown(state.msg);
        });
    });
});