import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/users.service"
import { User } from "../../model/user"
import { Router, ActivatedRoute }       from '@angular/router';
import { NgForm }    from '@angular/common';

@Component({
    templateUrl: 'views/menus/users/edit-user.html',
    providers: [UserService],
})
export class UserEditComponent implements OnInit{
     
    private sub: any;
    private username: string;
    private user: User;

    constructor(private userService:UserService, private route: ActivatedRoute){}
    
    ngOnInit(){
        this.sub = this.route.params.subscribe(params => {
            this.username = params['id'];
            this.userService.findUser(this.username)
                .then(user => this.user = user)
                .catch(err => console.error(err));
        });
    }

    editUser(event){

    }
}