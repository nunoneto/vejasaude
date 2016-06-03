export class Alert{
    
    visible: string = "hidden";
    type: string;
    message: string;
    
    setVisible(visible:boolean){
        this.visible = visible ? "" : "hidden";
    }

}