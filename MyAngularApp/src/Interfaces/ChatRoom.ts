import { ChatMessage } from "./ChatMessage";

export interface ChatRoom{
    clientId:number,
    messages:ChatMessage[],
    username:string
}