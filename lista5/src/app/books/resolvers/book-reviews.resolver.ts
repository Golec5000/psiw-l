import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Review} from '../model/review';
import {ReviewsService} from '../services/reviews.service';

@Injectable({ providedIn: 'root' })
export class BookReviewsResolver implements Resolve<Review[]> {
  constructor(private reviewsService: ReviewsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<Review[]> {
    const bookId = Number(route.paramMap.get('bookId'));
    return this.reviewsService.getReviewsForBook(bookId);
  }
}
