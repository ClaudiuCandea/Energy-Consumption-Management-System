export interface ChatMessage{
    id:number,
    content:string,
    clientId:number,
    sender:string,
    seen:boolean,
    username:string
}