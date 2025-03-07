import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NavbarComponent } from "../navbar/navbar.component";
import { InsideNavComponent } from "../inside-nav/inside-nav.component";
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ProfileDataService } from '../../services/profile-data.service';
import { User } from '../../types/user.interface';
import { BadgesDialogComponent } from "../badges-dialog/badges-dialog.component";
import { Badge, BadgesService } from '../../services/badges.service';

@Component({
  selector: 'app-own-profile',
  imports: [RouterLink,
    NavbarComponent,
    InsideNavComponent,
    CommonModule, BadgesDialogComponent],
  templateUrl: './own-profile.component.html',
  styleUrl: './own-profile.component.css',
})
export class OwnProfileComponent  {

  user: User | null = null;
  connections: any[] = [];
  userProfileData: User | null = null;
  userId: string | null = null;
  isToastMessageVisible= false;
  toastMessage: string =  '';
  loading = false;
  showBadgesDialog = false;
  earnedBadges: Badge[] | undefined;
  badge!: Badge | null;
  userBadge!: Badge | null;


  constructor(private router: Router,
    private authService: AuthService,
    private profileDataService: ProfileDataService,
    private badgesService: BadgesService
  ) {}

  ngOnInit(): void {

    //get the ID from Auth
    // this.authService.getUserId().subscribe((id: string) => { //check with maboku bc in the auth he passed a number
    //   this.userId = id;
    //   if (this.userId) {
    //     this.loadOwnProfile(this.userId); // this converts the ID to a number, i did this bc it keeps givign me an error
    //   }
    // });
          this.authService
            .getUserById(this.authService.clickedUserId)
            .subscribe((res) => {
              // console.log('USER DATA FROM DATABASE');
              console.log(res);
              this.userProfileData = res;
            });
    // this.loadUserBadge();
  }

  loadOwnProfile(userId: string): void {
    // this.loading= true;
    // this.profileDataService.getOwnProfile(userId).subscribe(
      this.authService.getUserById(Number(userId)).subscribe(
      (data) => {
        this.userProfileData = data;
        // this.loading = false;
      },
      (error) => {
        console.error('Error fetching own profile:', error);
        // this.loading = false;
      }
    );
  }


  copyLinkToProfile(){
    const profileLink = window.location.href;
    navigator.clipboard.writeText(profileLink).then(() => {
      console.log('Link copied to clipboard');
      this.showToast('Link copied to clipboard!');
    }).catch(err => {
      console.error('Failed to copy link:', err);
      this.showToast('Failed to copy link to clipboard.');
    });
  }

  showToast(message: string){
    this.toastMessage = message;
    console.log('Link copied.');
    this.isToastMessageVisible = true;

    setTimeout(() => {
      this.isToastMessageVisible = false;
    }, 3000 );
    console.log('link workssss');
  }

  goToCommunities() {
  this.loading = true;

  setTimeout(() => {
    this.loading = false;
    this.router.navigate(['/allCommunities']);

  }, 2000);

  }
 



 
  private loadUserBadge() {
    const userId = localStorage.getItem('id');
    if (!userId) return;

    this.badgesService.getUserBadge(Number(userId)).subscribe({
      next: (badge) => {
        this.badge = badge;
      },
      error: (error) => {
        console.error('Error loading badge:', error);
      },
    });
  }
  openBadgesDialog() {
    this.showBadgesDialog = true;
    // Prevent background scrolling
    document.body.style.overflow = 'hidden';
  }

  closeBadgesDialog() {
    this.showBadgesDialog = false;
    // Re-enable background scrolling
    document.body.style.overflow = '';
  }
}


// function loadOwnProfile(userId: any, number: any) {
//   throw new Error('Function not implemented.');
// }
// function goToCommunities() {
//   throw new Error('Function not implemented.');
// }
