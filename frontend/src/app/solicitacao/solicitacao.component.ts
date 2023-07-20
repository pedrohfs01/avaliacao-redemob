import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { SolicitacaoService } from '../services/solicitacao.service';
import { SolicitacaoDTO } from '../dtos/solicitacao.dto';
import { Router } from '@angular/router';

@Component({
    selector: 'app-solicitacao',
    templateUrl: './solicitacao.component.html',
    styleUrls: ['./solicitacao.component.css']
})
export class SolicitacaoComponent implements OnInit {

    registrationForm: FormGroup;

    constructor(
        private fb: FormBuilder,
        private messageService: MessageService,
        private router: Router,
        private solicitacaoService: SolicitacaoService
    ) {

    }

    ngOnInit(): void {
        this.newForm();
    }


    newForm() {
        this.registrationForm = this.fb.group({
            possuiMoradia: ['', Validators.required],
            login: ['', Validators.required],
            senha: ['', Validators.required],
            nome: ['', Validators.required],
            dataNascimento: ['', Validators.required],
            cpf: ['', Validators.required],
            nomeMae: ['', Validators.required],
            fotoRosto: [null, Validators.required],
            fotoDocumento: [null, Validators.required],
            fotoComprovaMoradia: [null, Validators.required]
        });
    }

    onSubmit() {
        if (this.registrationForm.get('possuiMoradia').value == 'false') {
            return this.showError("Precisa morar em um dos municípios da Região Metropolitana de Goiânia")
        }
        if (!this.registrationForm.valid) {
            return this.showError("Campos obrigatórios não preenchidos.")
        }
        let valor = this.registrationForm.value;

        this.solicitacaoService.salvar(valor as SolicitacaoDTO).subscribe((response) => {
            this.showSuccess("Solicitação enviada. Retornar ao site após 24h para verificar se a candidatura foi aceita ou não. ")
        }, (error) => {
            this.showError(JSON.parse(error.error).message);
        });
    }

    uploadFoto(event: any, tipoFoto: number) {
        const file = event.currentFiles[0];

        if (file) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                if (tipoFoto == 0) {
                    this.registrationForm.get('fotoRosto').setValue(reader.result);
                }
                if (tipoFoto == 1) {
                    this.registrationForm.get('fotoDocumento').setValue(reader.result);
                }
                if (tipoFoto == 2) {
                    this.registrationForm.get('fotoComprovaMoradia').setValue(reader.result);
                }
            };
        }
    }

    consultaSolicitacao(event) {
        this.router.navigate(['/login']);
    }

    showError(mensagem: string) {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: mensagem });
    }

    showSuccess(mensagem: string) {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: mensagem });

    }
}
