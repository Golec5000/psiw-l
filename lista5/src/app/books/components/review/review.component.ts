import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Review} from '../../model/review';

@Component({
  selector: 'app-review',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './review.component.html',
})
export class ReviewComponent {
  @Input() review!: Review;
}
