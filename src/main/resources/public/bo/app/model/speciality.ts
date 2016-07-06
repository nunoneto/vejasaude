export class Speciality{

    id: number;
    specialty: string;

    constructor(json:any){
        this.id = json.id;
        this.specialty = json.specialty;
    }

}