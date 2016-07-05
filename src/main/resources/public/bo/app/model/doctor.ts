export class Doctor{
    
    id: number;
    name: string;
    speciality: number;
    curriculum: number;
  
    //JSON constructor
    constructor(jsonData:any){
        this.name = jsonData.name;
        this.speciality = jsonData.speciality;
        this.curriculum = jsonData.curriculum;
        this.id = jsonData.id;
    }
    
}