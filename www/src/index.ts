import { gameClient } from "./services.js"

const hello = "Hello"


function installJoin() {
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