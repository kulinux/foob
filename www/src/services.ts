

export class GameClient {
    join(): void {
        console.log('GameClient::join')
        const ws = new WebSocket('ws://localhost:8080/ws')
        ws.addEventListener("open", (event) => {
            ws.send(JSON.stringify(
                {
                    command: 'hello',
                    arguments: ''
                }
            ))
          });
    }
}

export const gameClient = new GameClient()