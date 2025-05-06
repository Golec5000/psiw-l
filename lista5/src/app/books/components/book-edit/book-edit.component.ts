import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators,} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {Book} from '../../model/book';
import {BooksService} from '../../services/books.service';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class BookEditComponent {
  form: FormGroup;
  book: Book;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private booksService: BooksService
  ) {
    this.book = this.route.snapshot.data['book'];

    this.form = this.fb.group({
      title: [this.book.title, [Validators.required, Validators.maxLength(50)]],
      author: [
        this.book.author,
        [Validators.required, Validators.maxLength(50)],
      ],
      year: [
        this.book.year,
        [Validators.required, Validators.min(1000), Validators.max(2023)],
      ],
      description: [this.book.description, [Validators.maxLength(1000)]],
    });
  }

  save(): void {
    if (this.form.valid && this.form.dirty) {
      const updatedBook: Book = { ...this.book, ...this.form.value };
      this.booksService.saveBook(updatedBook).subscribe(() => {
        this.router.navigate(['/books']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/books']);
  }

  isInvalid(control: string): boolean {
    const ctrl = this.form.get(control);
    return !!(ctrl && ctrl.invalid && (ctrl.dirty || ctrl.touched));
  }
}
