import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/users.service"
import { User } from "../../model/user"

@Component({
    templateUrl: 'views/menus/users/list-users.html',
    providers: [UserService]
})

export class ListUsersComponent implements OnInit{
    
    users: User[];

    constructor(private userService:UserService){}

    ngOnInit(){
        this.userService.getUsers()
        .then(users => this.users = users)
        .catch(err => console.log(err));
    }
    
}