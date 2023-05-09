

export class GameClient {
    private questionCallback: (string) => void = () => {}

    question(callback: (string) => void) {
        this.questionCallback = callback
    }

    join(): void {
        console.log('GameClient::join')
        const ws = new WebSocket('ws://localhost:8080/ws')
        ws.addEventListener('open', (event) => {
            ws.send(JSON.stringify(
                {
                    command: 'hello',
                    arguments: ''
                }
            ))
        })

        ws.addEventListener('message', (event) => {
            const command = JSON.parse(event.data)
            console.log('Message from server ', event.data);
            if(command.command == 'question') {
                this.questionCallback(command.arguments)
            }
        })
    }
}

export const gameClient = new GameClient()