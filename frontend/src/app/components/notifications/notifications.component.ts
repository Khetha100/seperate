import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { InsideNavComponent } from "../inside-nav/inside-nav.component";
import { NotificationsService } from '../../services/notifications.service';

@Component({
  selector: 'app-notifications',
  imports: [InsideNavComponent, NavbarComponent],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css'
})
export class NotificationsComponent {
  
}
