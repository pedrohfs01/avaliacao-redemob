import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {

    username: string;
    password: string;

    errors: string[];

    msgSuccess: string;

    constructor(
        private router: Router,
        private authService: AuthService
    ) { }

    onSubmit() {

        this.authService.tentarLogar(this.username, this.password).subscribe(
            response => {
                const access_token = JSON.stringify(response);
                localStorage.setItem("access_token", access_token);
                if (this.username == "admin") {
                    this.router.navigate(['/administracao'])
                } else {
                    this.router.navigate(['/consulta-solicitacao', this.username]);
                }
            }, error => {
                this.errors = ["Usuário e/ou senha incorreto(s)."];
            }
        )
    }

    preparaCadastro(event) {
        event.preventDefault();
        this.router.navigate(['/rocket'])
    }
}
