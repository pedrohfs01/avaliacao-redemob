import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../dtos/usuario';
import { SolicitacaoDTO } from '../dtos/solicitacao.dto';
import { ImageUtilService } from './img-util.service';

@Injectable({
    providedIn: 'root'
})
export class SolicitacaoService {

    url: string = environment.api_url;

    constructor(
        private http: HttpClient,
        private imageUtilService: ImageUtilService
    ) { }

    salvar(solicitacao: SolicitacaoDTO): Observable<any> {
        let fotoComprovaMoradia = this.imageUtilService.dataUriToBlob(solicitacao.fotoComprovaMoradia);
        let fotoDocumento = this.imageUtilService.dataUriToBlob(solicitacao.fotoDocumento);
        let fotoRosto = this.imageUtilService.dataUriToBlob(solicitacao.fotoRosto);

        let formData: FormData = new FormData();

        let dto = {
            login: solicitacao.login,
            senha: solicitacao.senha,
            dataNascimento: solicitacao.dataNascimento,
            nome: solicitacao.nome,
            nomeMae: solicitacao.nomeMae,
            cpf: solicitacao.cpf
        }

        const json = JSON.stringify(dto)
        const blob = new Blob([json], {
            type: 'application/json'
        });

        formData.append("file", fotoComprovaMoradia, "fotoComprovaMoradia.png");
        formData.append("file", fotoDocumento, "fotoDocumento.png");
        formData.append("file", fotoRosto, "fotoRosto.png");
        formData.append("solicitacao", blob);

        return this.http.post(`${this.url}/api/solicitacao/`, formData,
            {
                observe: 'response',
                responseType: 'text'
            });
    }

    buscarSolicitacoes(): Observable<SolicitacaoDTO[]> {
        return this.http.get<SolicitacaoDTO[]>(`${this.url}/api/solicitacao`);
    }

    buscarSolicitacoesPorUsuario(login: string): Observable<SolicitacaoDTO[]> {
        return this.http.get<SolicitacaoDTO[]>(`${this.url}/api/solicitacao/${login}`);
    }

    atualizarAprovacao(id: number, aprovado: boolean) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        return this.http.put(`${this.url}/api/solicitacao/${id}`, aprovado, httpOptions);
    }
}
