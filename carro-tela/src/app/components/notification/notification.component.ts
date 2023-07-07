import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {
  constructor(private appComponent:AppComponent) {}

  mensagem:string = this.appComponent.mensagem;

  toggleNotification(){
    this.appComponent.toggleNotification();
  }
}
