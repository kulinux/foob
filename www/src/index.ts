import { gameClient } from "./services.js"

const hello = "Hello"


function installJoin() {
    const question = document.querySelector('#question')
    gameClient.question((str: string) => {
        var text = document.createTextNode(str);
        question.appendChild(text)
    })
    const join = document.querySelector('#join')
    join?.addEventListener('click', () => {
        gameClient.join()
    })
}

export function init(): void {
    console.log('init')
    installJoin()
}


init()