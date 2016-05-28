export class User{
    
    username: string;
    email: string;
    sessionID: string;
    prettyName: string;
  
    //JSON constructor
    constructor(jsonData:any){
        this.username = jsonData.username;
        this.email = jsonData.email;
        this.sessionID = jsonData.sessionID;
        this.prettyName = jsonData.prettyName;
    }
    
}