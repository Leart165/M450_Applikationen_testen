import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  studenten = [
    { name: 'Max', alter: 20 },
    { name: 'Anna', alter: 22 }
  ];

  neuerStudent = { name: '', alter: 0 };

  addStudent() {
    if (this.neuerStudent.name && this.neuerStudent.alter > 0) {
      this.studenten.push({ ...this.neuerStudent });
      this.neuerStudent = { name: '', alter: 0 };
    }
  }

  deleteStudent(index: number) {
    this.studenten.splice(index, 1);
  }
}
