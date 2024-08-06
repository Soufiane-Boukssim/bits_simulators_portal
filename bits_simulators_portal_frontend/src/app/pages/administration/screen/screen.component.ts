import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.scss']
})
export class ScreenComponent implements OnInit {
  language: string = '';
  en: boolean = false;
  fr: boolean = false;
  esp: boolean = false;
  activeSection: string = 'layout';  // Default section
  displayCarrierName: boolean = false;
  screens: any[] = []; // Array to hold the screen data
  textObjectItems: any[] = []; // Array to hold the Text/Object data

  constructor(private globalService: GlobalService) {}

  ngOnInit(): void {
    const user1 = localStorage.getItem('user');
    if (user1) {
      const parsedUser = JSON.parse(user1);
      this.language = parsedUser.languageCode;

      if (this.language === 'en') {
        Promise.resolve().then(() => this.globalService.titleSubject.next('Smart ATM / NDC / Screen'));
        this.en = true;
      } else if (this.language === 'fr') {
        Promise.resolve().then(() => this.globalService.titleSubject.next('Smart ATM / NDC / Screen'));
        this.fr = true;
      } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next('Smart ATM / NDC / Screen'));
        this.esp = true;
      }
    }
  }

  showSection(section: string) {
    this.activeSection = section;
  }

  toggleCarrierName(event: Event) {
    this.displayCarrierName = (event.target as HTMLInputElement).checked;
  }

  addRow() {
    // Adds a new row with empty values
    this.screens.push({ name: '', order: null, time: '' });
  }

  deleteAll() {
    // Clears all rows
    this.screens = [];
  }

  addTextObjectRow() {
    // Adds a new row with empty values for Text/Object
    this.textObjectItems.push({ objet: '', line: null, colonne: null, numeroObjet: null });
  }

  deleteAllTextObjectRow() {
    // Clears all rows
    this.textObjectItems = [];
  }

}
