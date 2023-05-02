import {screen, fireEvent} from '@testing-library/dom'
import { init } from "../src"
import { gameClient } from "../src/services"

jest.mock('../src/services.ts')

describe('Game', () => {
    test('should join game on click join', () => {
        const div = global.document.createElement('div')
        div.innerHTML = '<button id="join">Join</button>'
        document.body.appendChild(div)

        init()

        const joinButton = screen.getByText('Join')
        fireEvent.click(joinButton!)

        expect(gameClient.join).toBeCalled()
    })
})