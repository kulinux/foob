import { init } from "../src"

describe('Game', () => {
    test('should join game on click join', () => {
        const div = document.createElement('div')
        div.innerHTML = '<button id="join">Join</button>'
        init()
        
    })
})