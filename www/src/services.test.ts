import { WS } from 'jest-websocket-mock';
import {GameClient} from './services.js'

describe('Game Client', () => {
    it('open ws on init', async () => {
        const server = new WS('ws://localhost:8080/ws')
        const gameClient = new GameClient()
        gameClient.join()
        await server.connected
        await expect(server).toReceiveMessage(JSON.stringify({
            command: "hello",
            arguments: ""
        }))
    })
})