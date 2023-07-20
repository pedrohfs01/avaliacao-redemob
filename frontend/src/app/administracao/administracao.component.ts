import { Component, OnInit } from '@angular/core';
import { SolicitacaoDTO } from '../dtos/solicitacao.dto';
import { SolicitacaoService } from '../services/solicitacao.service';
import { MessageService } from 'primeng/api';

@Component({
    selector: 'app-administracao',
    templateUrl: './administracao.component.html',
    styleUrls: ['./administracao.component.css']
})
export class AdministracaoComponent implements OnInit {

    solicitacoes: SolicitacaoDTO[] = [];

    constructor(
        private solicitacaoService: SolicitacaoService,
        private messageService: MessageService
    ) { }

    ngOnInit(): void {
        this.carregarSolicitacoes();
    }

    carregarSolicitacoes() {
        this.solicitacaoService.buscarSolicitacoes().subscribe(response => {
            this.solicitacoes = response;
        })
    }

    aprovar(solicitacao: SolicitacaoDTO) {
        this.solicitacaoService.atualizarAprovacao(solicitacao.id, true).subscribe(() => {
            this.showSuccess("Aprovado com sucesso.")
            this.carregarSolicitacoes();
        });
    }

    recusar(solicitacao: SolicitacaoDTO) {
        this.solicitacaoService.atualizarAprovacao(solicitacao.id, false).subscribe(() => {
            this.showSuccess("Recusado com sucesso.")
            this.carregarSolicitacoes();
        });
    }

    showSuccess(mensagem: string) {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: mensagem });
    }
}
