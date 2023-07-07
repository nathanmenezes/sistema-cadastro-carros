import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Carro } from '../models/carro';
import { Filtro } from '../models/filtro';

@Injectable({
  providedIn: 'root'
})
export class CarroService {

  constructor(private httpClient:HttpClient) { }
  private apiUrl = 'http://localhost:8080/carro';
  private urlFiltro = 'http://localhost:8080/carro/filtrar'
  private url = "http://localhost:8080/carro/filtrar";

  getCarros(): Observable<any>{
    return this.httpClient.get<any>(this.apiUrl);
  }

  getCarroId(id: number): Observable<any>{
    return this.httpClient.get<any>(this.apiUrl + "/" + id)
  }

  cadastrarCarro(carro: any): Observable<any> {
    return this.httpClient.post(this.apiUrl, carro);
  }

  editarCarro(carro: any): Observable<any> {
    return this.httpClient.put(this.apiUrl, carro);
  }

  deletarCarro(id:number): Observable<any>{
    return this.httpClient.delete(this.apiUrl + "/" + id)
  }

  filtrarCarros(filtro:Filtro): Observable<any>{
    this.urlFiltro = this.url + `?page=0&size=100&marca=${filtro.marca}&modelo=${filtro.modelo}&ano=${filtro.ano}`
    console.log(this.urlFiltro)
    return this.httpClient.get(this.urlFiltro);
  }
}
