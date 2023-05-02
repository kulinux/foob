import { init } from "../src"

describe('Game', () => {
    test('should join game on click join', () => {
        const div = global.document.createElement('div')
        div.innerHTML = '<button id="join">Join</button>'
        document.body.appendChild(div)
        init()
    })
})