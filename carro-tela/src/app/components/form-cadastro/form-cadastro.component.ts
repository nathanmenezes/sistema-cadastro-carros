import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Carro } from 'src/app/models/carro';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-form-cadastro',
  templateUrl: './form-cadastro.component.html',
  styleUrls: ['./form-cadastro.component.css'],
})
export class FormCadastroComponent {

  carro:Carro = this.appComponent.carro;

  tipoForm:boolean = this.appComponent.tipoFormulario;

  constructor(private appComponent:AppComponent) {}

  closeForm(){
    this.appComponent.toggleFormCad();
  }

  editarForm(){
    this.appComponent.editarForm(this.carro);
  }

  cadastroForm(){
    this.appComponent.cadastroForm(this.carro);
  }
}
