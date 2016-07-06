import { Component, OnInit } from "@angular/core";
import { Router, ROUTER_DIRECTIVES, ActivatedRoute } from '@angular/router';
import { DoctorService } from "../../services/doctor.service"
import { Doctor } from "../../model/doctor"

@Component({
    templateUrl: 'views/menus/doctors/list-doctors.html',
    providers: [DoctorService],
    directives: [ROUTER_DIRECTIVES]
})

export class ListDoctorsComponent implements OnInit{
    
    doctors: Doctor[];

    constructor(private doctorService:DoctorService, private router:Router,private route: ActivatedRoute){}

    ngOnInit(){
        this.doctorService.getAll()
        .then(doctors => this.doctors = doctors)
        .catch(err => console.log(err));
    }

    editDoctor(doctor: Doctor){
        this.router.navigate(['home/doctors',doctor.id]);
    }

    goToCurriculum(doctor: Doctor){
        this.router.navigate(['home/curriculum',doctor.curriculum.id]);
    }

    
}