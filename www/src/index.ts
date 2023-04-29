const hello = "Hello"


function installJoin() {
    const join = document.querySelector('#join')
    join?.addEventListener('click', () => {
        console.log('join')
    })
}

export function init(): void {
    console.log('init')
    installJoin()
}


init()