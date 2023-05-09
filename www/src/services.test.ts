import { WS } from 'jest-websocket-mock';
import { GameClient } from './services.js'

describe('Game Client', () => {

    let server: WS
    let gameClient: GameClient

    beforeEach(() => {
        server = new WS('ws://localhost:8080/ws')
        gameClient = new GameClient()
    })

    afterEach(async () => {
        await server.close()
    })

    it('open ws on init', async () => {
        gameClient.join()
        await server.connected
        await expect(server).toReceiveMessage(JSON.stringify({
            command: "hello",
            arguments: ""
        }))
    })

    it('call callback on message', async () => {
        const callback = jest.fn()
        gameClient.question(callback)
        gameClient.join()
        await server.connected
        const question = {
            command: "question",
            arguments: "What number goes after 1"
        }
        server.send(JSON.stringify(question));

        expect(callback).toBeCalledWith(question.arguments)
    })

})