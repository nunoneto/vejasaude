import { Component, OnInit } from "@angular/core";
import { Router, ROUTER_DIRECTIVES, ActivatedRoute } from '@angular/router';
import { UserService } from "../../services/users.service"
import { User } from "../../model/user"

@Component({
    templateUrl: 'views/menus/users/list-users.html',
    providers: [UserService],
    directives: [ROUTER_DIRECTIVES]
})

export class ListUsersComponent implements OnInit{
    
    users: User[];

    constructor(private userService:UserService, private router:Router,private route: ActivatedRoute){}

    ngOnInit(){
        this.userService.getUsers()
        .then(users => this.users = users)
        .catch(err => console.log(err));
    }

    editUser(user){
        this.router.navigate(['home/users',user.username]);
    }

    
}