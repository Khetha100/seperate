import { Donations } from './../../types/donation.interface';
import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { Dashboard } from '../../types/dashboard.interface';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  dashboardData: Dashboard = {
    reportedContent: 0,
      totalCommunities: [],
    totalDonations: [],
      totalUsers: []
  };

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.getDashboardData();
  }

  getDashboardData() {
    this.adminService.getDashboardData().subscribe(
      (data) => {
        console.log(data);
        this.dashboardData = data;
      },
      (error) => {
        console.error("Error fetching dashboard data:", error);
      }
    );
  }

  sumTotalDonations(): number{
    let sumDonation = 0;
    this.dashboardData.totalDonations.forEach(element => {
      sumDonation += element.amount;
    });
    return sumDonation;
  }
}
