"use strict";

component('chat', (node, state) => {
    createNode('innerHTML', node, inner => {});
    createNode('div', node, div => {
        div.classList.add('message-container');
        createNode('message', div, message => {
            message.setAttribute('msg', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic incidunt nihil quam');
            
        });
        createNode('message', div, message => {
            message.setAttribute('msg', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic incidunt nihil quam');
        });
    });
    createNode('chat-input', node, input => {
        
    });
});