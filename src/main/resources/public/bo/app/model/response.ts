export class Response{
    
    statusCode: number;
    statusMessage: string;
    content;
    
    constructor(statusCode: number, statusMessage: string){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    
    
    
}