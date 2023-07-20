import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdministracaoComponent } from './administracao/administracao.component';
import { ConsultaSolicitacaoComponent } from './consulta-solicitacao/consulta-solicitacao.component';
import { LoginComponent } from './login/login.component';
import { SolicitacaoComponent } from './solicitacao/solicitacao.component';

const routes: Routes = [
    { path: '', component: SolicitacaoComponent },
    { path: 'login', component: LoginComponent },
    { path: 'rocket', component: SolicitacaoComponent },
    { path: 'administracao', component: AdministracaoComponent },
    { path: 'consulta-solicitacao/:username', component: ConsultaSolicitacaoComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
