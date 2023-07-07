import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CarroService } from './services/carro.service';
import { Carro } from './models/carro';
import { FormCadastroComponent } from './components/form-cadastro/form-cadastro.component';
import { Filtro } from './models/filtro';
import { DateAdapter } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'carro-front-angular';
  constructor(private carroService:CarroService) {
    const currentYear = new Date().getFullYear();
    this.years = Array.from({ length: 100 }, (_, index) => currentYear - index);
  }

  infoCarros: Carro[] = [];

  marcas: string[] = [];

  showForm: boolean = false;

  showNotification: boolean = false;

  tipoFormulario:boolean = true;

  idCarro:number = 0;

  mensagem:string = "Error: Contate o Administrador";

  filtros:Filtro={
    ano:"",
    marca:"",
    modelo:""
  }

  carro:Carro = {
    id: 0,
    ano:"",
    marca:"",
    modelo:""
  };

  toggleForm(tipoForm:boolean) {
    this.tipoFormulario = tipoForm;
  }

  toggleFormCad(){
    this.limparForms();
    this.showForm = !this.showForm;
  }

  toggleNotification(){
    this.showNotification = !this.showNotification;
  }

  limparForms(){
    this.carro = {
      id: 0,
    ano:"",
    marca:"",
    modelo:""
    }
  }

  pegarId(id:number){
    this.idCarro = id;
  }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() : void {
    this.carroService.getCarros().subscribe((resp)=>{
      this.infoCarros = resp.content as []
      this.infoCarros.forEach(element => {
        this.marcas.push(element.marca);
      });
      this.marcas = this.marcas.filter((este, i) => this.marcas.indexOf(este) === i);
      console.log(resp);
    },
    (error: any) =>{
      console.log("Error", error);
    }
    )
  }

  cadastroForm(carro:Carro) {
    this.carroService.cadastrarCarro(carro).subscribe(
      () => {
        this.mensagem = "Carro cadastrado com sucesso!";  
        this.toggleNotification();
        console.log('Carro cadastrado com sucesso');
        carro = {
          id: 0,
          ano: '',
          marca: '',
          modelo: ''
        };
        this.findAll();
      },
      error => {
        console.error('Erro ao cadastrar carro:', error);
        this.mensagem = error.error.mensagem;
        console.log(error.error.mensagem);
        this.toggleNotification();
      }
    );
    this.toggleForm(true);
    this.toggleFormCad();
  }

  editarForm(carro:Carro){
    carro.id = this.idCarro;
    this.carroService.editarCarro(carro).subscribe(
      () => {
        console.log('Carro editado com sucesso');
        carro = {
          id:0,
          ano: '',
          marca: '',
          modelo: ''
        };
        this.findAll();
      },
      error => {
        console.error('Erro ao cadastrar carro:', error);
      }
    );
    this.toggleForm(true);
  }

  deletarCarro(){
    this.carroService.deletarCarro(this.idCarro).subscribe(() =>{
      alert('Carro Deletado com Sucesso!');
      this.findAll();
    },
    error =>{
      console.error('Erro ao deletar carro:', error)
    })
  }

  preencherForm(id:number){
    this.carroService.getCarroId(id).subscribe((resp)=>{
      this.carro = resp.corpo;
      this.showForm = !this.showForm;
      console.log(this.carro);
    },
    (error: any) =>{
      console.log("Error", error);
    }
    )
  }

  filtrarCarros(){
    this.carroService.filtrarCarros(this.filtros).subscribe((resp) =>{
      this.infoCarros = resp.content as []
      console.log(resp)
    },
    (error: any) =>{
      console.log("Error", error);
    })
  }

  limparFiltros(){
    this.filtros = {
      ano:"",
      marca:"",
      modelo:""
    }
  }

  @Output() yearSelected: EventEmitter<string> = new EventEmitter<string>();
  years: number[];

  onYearSelected(year: string) {
    this.filtros.ano = year;
    this.yearSelected.emit(year);
  }
}
