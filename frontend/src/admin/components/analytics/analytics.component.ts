import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from "../sidebar/sidebar.component";

@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [CommonModule, SidebarComponent],
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {
  analyticsData: any = null;
  donationSummary: any = null;
  newUsersCount: number = 0;

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.getAnalyticsData();
    this.getDonationSummary();
    this.getNewUsersCount();
  }

  getAnalyticsData() {
    this.adminService.getAnalyticsData().subscribe(
      (data) => {
        this.analyticsData = data;
      },
      (error) => {
        console.error("Error fetching analytics data:", error);
      }
    );
  }

  getDonationSummary() {
    this.adminService.getDonationSummary().subscribe(
      (data) => {
        this.donationSummary = data;
      },
      (error) => {
        console.error("Error fetching donation summary:", error);
      }
    );
  }

  getNewUsersCount() {
    const thirtyDaysAgo = new Date();
    thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);
    this.adminService.getNewUsersCount(thirtyDaysAgo.toISOString().split('T')[0]).subscribe(
      (count) => {
        this.newUsersCount = count;
      },
      (error) => {
        console.error("Error fetching new users count:", error);
      }
    );
  }

  formatAmount(amount: number): string {
    return `R${amount.toLocaleString()}`;
  }
}
