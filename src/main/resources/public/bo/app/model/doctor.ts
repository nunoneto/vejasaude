import { Speciality } from "./speciality";
import { Curriculum } from "./curriculum";


export class Doctor{
    
    id: number;
    name: string;
    specialty: Speciality;
    curriculum: Curriculum;
  
    //JSON constructor
    constructor(jsonData:any){
        this.name = jsonData.name;
        this.specialty = new Speciality(jsonData.specialty);
        this.curriculum = new Curriculum(jsonData.curriculum);
        this.id = jsonData.id;
    }
    
}