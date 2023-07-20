import { Component, OnInit } from '@angular/core';
import { SolicitacaoDTO } from '../dtos/solicitacao.dto';
import { SolicitacaoService } from '../services/solicitacao.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-consulta-solicitacao',
    templateUrl: './consulta-solicitacao.component.html',
    styleUrls: ['./consulta-solicitacao.component.css']
})
export class ConsultaSolicitacaoComponent implements OnInit {

    solicitacoes: SolicitacaoDTO[] = [];
    login: string = "";
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private solicitacaoService: SolicitacaoService
    ) { }

    ngOnInit(): void {
        this.login = this.route.snapshot.paramMap.get('username');
        this.carregarSolicitacoes();
    }

    carregarSolicitacoes() {
        this.solicitacaoService.buscarSolicitacoesPorUsuario(this.login).subscribe(response => {
            this.solicitacoes = response;
        })
    }

    novaSolicitacao(event) {
        event.preventDefault();
        this.router.navigate(['/rocket']);
    }

}
