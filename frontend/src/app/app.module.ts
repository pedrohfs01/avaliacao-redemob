import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxMaskModule } from 'ngx-mask';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { FileUploadModule } from 'primeng/fileupload';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { RadioButtonModule } from 'primeng/radiobutton';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

import { AdministracaoComponent } from './administracao/administracao.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConsultaSolicitacaoComponent } from './consulta-solicitacao/consulta-solicitacao.component';
import { SecurePasswordDirective } from './directives/secure-password.directive';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { ImageUtilService } from './services/img-util.service';
import { SolicitacaoService } from './services/solicitacao.service';
import { SolicitacaoComponent } from './solicitacao/solicitacao.component';
import { TokenInterceptor } from './token.interceptor';




@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        SolicitacaoComponent,
        SecurePasswordDirective,
        AdministracaoComponent,
        ConsultaSolicitacaoComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        NgxMaskModule.forRoot(),
        InputTextModule,
        CalendarModule,
        FileUploadModule,
        ButtonModule,
        InputMaskModule,
        ToastModule,
        RadioButtonModule,
        TableModule,
        DropdownModule
    ],
    providers: [
        AuthService,
        MessageService,
        SolicitacaoService,
        ImageUtilService,
        { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
