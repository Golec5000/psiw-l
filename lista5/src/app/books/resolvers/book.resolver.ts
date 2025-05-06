import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {Observable} from 'rxjs';
import {BooksService} from '../services/books.service';
import {Book} from '../model/book';

@Injectable({
  providedIn: 'root',
})
export class BookResolver implements Resolve<Book> {
  constructor(private booksService: BooksService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<Book> {
    const id = Number(route.paramMap.get('bookId'));
    return this.booksService.findBookById(id);
  }
}
