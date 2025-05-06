import {Component} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {Book} from '../../model/book';
import {CommonModule} from '@angular/common';
import {Review} from '../../model/review';
import {ReviewComponent} from '../review/review.component';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  standalone: true,
  imports: [CommonModule, RouterLink, ReviewComponent],
})
export class BookDetailsComponent {
  readonly book: Book;
  readonly reviews: Review[];

  constructor(private readonly route: ActivatedRoute) {
    this.book = this.route.snapshot.data['book'];
    this.reviews = this.route.snapshot.data['reviews'] ?? [];
  }
}
