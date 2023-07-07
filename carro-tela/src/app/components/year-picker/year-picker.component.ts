import { Component, EventEmitter, Output } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-year-picker',
  templateUrl: './year-picker.component.html',
  styleUrls: ['./year-picker.component.css'],
})

export class YearPickerComponent {

  @Output() yearSelected: EventEmitter<string> = new EventEmitter<string>();
  years: number[];

  ano:string = this.appComponent.filtros.ano;

  constructor(private appComponent:AppComponent) {
    const currentYear = new Date().getFullYear();
    this.years = Array.from({ length: 100 }, (_, index) => currentYear - index);
  }

  onYearSelected(year: string) {
    this.appComponent.filtros.ano = year;
    this.yearSelected.emit(year);
  }
}
