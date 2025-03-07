import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { ModalComponent } from '../modal-delete/modal-delete.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-content',
  standalone: true,
  imports: [SidebarComponent, ModalComponent, CommonModule],
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css'],
})
export class ContentComponent implements OnInit {
  reportedContent: any[] = [];
  showModal = false;
  contentToDeleteId: number | null = null;

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.getReportedContent();
  }

  getReportedContent() {
    this.adminService.getReportedContent().subscribe(
      (data) => {
        this.reportedContent = data;
      },
      (error) => {
        console.error('Error fetching reported content:', error);
      }
    );
  }

  confirmDeleteContent(id: number) {
    this.contentToDeleteId = id;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.contentToDeleteId = null;
  }

  deleteContent() {
    if (this.contentToDeleteId !== null) {
      this.adminService.deleteContent(this.contentToDeleteId).subscribe(
        () => {
          this.reportedContent = this.reportedContent.filter((item) => item.id !== this.contentToDeleteId);
          this.closeModal();
        },
        (error) => {
          console.error("Error deleting content:", error);
          this.closeModal();
        }
      );
    }
  }

  reviewContent(id: number) {
    console.log('Reviewing content with id:', id);
    // Implement review functionality
  }
}
