import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
  FormBuilder,
  ReactiveFormsModule,
  FormGroup,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InsideNavComponent } from '../inside-nav/inside-nav.component';
import { Router } from '@angular/router';
import { EditProfileService } from '../../services/edit-profile.service';
import { ProfileDataService } from '../../services/profile-data.service';

@Component({
  selector: 'app-edit-own-profile',
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    InsideNavComponent,
  ],
  templateUrl: './edit-own-profile.component.html',
  styleUrl: './edit-own-profile.component.css',
})
export class EditOwnProfileComponent implements OnInit {
  profileForm: FormGroup;
  selectedImage: string | null = null;

  toastMessage: string = '';
  isToastMessageVisible = false;

  userId: string | null = '';
  profileData: any = {};

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private editProfileService: EditProfileService,
    private profileDataService: ProfileDataService
  ) {
    this.profileForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      bio: [''],
      phoneNumber: ['', [Validators.required, Validators.pattern('^\\d{10}$')]],
      grade: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    if(localStorage.getItem('userId') != null){
      this.userId = localStorage.getItem('userId');
    }


    if (this.userId) {
      this.profileDataService.getOwnProfile(this.userId).subscribe(
        (data) => {
          console.log('Fetched profile:', data);
          this.profileData = data;
          this.prefillForm(data);
        },
        (error) => {
          console.error('Error fetching own profile:', error);
        }
      );
    }
  }

  prefillForm(data: any): void {
    this.profileForm.patchValue({
      firstName: data.firstName,
      lastName: data.lastName,
      bio: data.bio,
      phoneNumber: data.phoneNumber,
      phone: data.phoneNumber,
      grade: data.grade,
      // subjects: data.subjects,
    });

     // Prefill profile picture if available
     if (data.imageUrl) {
      this.selectedImage = data.imageUrl;
    }
  }

  // loadProfile(userId: string) {
  //   if(userId){
  //     this.editProfileService.getProfile(userId).subscribe(
  //       (profile) => {
  //         console.log('Fetched profile:', profile);

  //         //this then updates the form with thte recent new details
  //         this.profileForm.patchValue(profile);
  //       },
  //       (error) => {
  //         console.error('Error fetching profile:', error);
  //       }
  //     );
  //   }

  // }

  showToast(message: string) {
    this.toastMessage = message;
    console.log('profile updated');
    this.isToastMessageVisible = true;

    setTimeout(() => {
      this.isToastMessageVisible = false;
    }, 3000);
    console.log(this.toastMessage);
    // this.router.navigate(['/my-account']);
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.selectedImage = reader.result as string; // Convert file to base64 URL
      };

      reader.readAsDataURL(file);
    }
  }

  triggerFileInput() {
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    if (fileInput) {
      fileInput.click();
    }
  }

  saveEdittedProfile() {
    if (this.profileForm.valid) {
      const profileData = this.profileForm.value;
      if (this.selectedImage) {
        profileData.imageUrl = this.selectedImage;
      }

      this.editProfileService.updateProfile(this.userId!, profileData).subscribe(
        (response) => {
          console.log('Profile updated successfully:', response);
          this.showToast('Profile updated successfully!');
          // this.loadProfile(this.userId!); //reload the profile after updatig
        },
        (error) => {
          console.error('Error updating profile:', error);
          this.showToast('Failed to update profile.');
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }


    // saveEdittedProfile() {
  //   if (this.profileForm.valid) {
  //     const profileData = this.profileForm.value;
  //     this.isToastMessageVisible = true;
  //     console.log('Form submitted successfully:', this.profileForm.value);

  //     setTimeout(() => {
  //       this.isToastMessageVisible = false;
  //     }, 3000);
  //   } else {
  //     console.log('Form is invalid:');
  //   }

  //   setTimeout(() => {}, 3500);
  //   // this.router.navigate(['/home']);
  // }

}
