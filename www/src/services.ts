

export class GameClient {
    join(): void {
        console.log('GameClient::join')
    }
}

export const gameClient = new GameClient()