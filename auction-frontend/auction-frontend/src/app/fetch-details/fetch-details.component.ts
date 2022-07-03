import { Component, OnInit } from '@angular/core';
import { FetchService } from '../fetch.service';
import { Product } from '../product';

@Component({
  selector: 'app-fetch-details',
  templateUrl: './fetch-details.component.html',
  styleUrls: ['./fetch-details.component.css']
})
export class FetchDetailsComponent implements OnInit {

  private products: Product[] = [];
  constructor(private fetchService: FetchService) { }

  ngOnInit(): void {
    // this.fetchService.getProducts().subscribe((data: Product[]) => {
    //   console.log(data);
    //   this.products = data;
    // });
    this.fetchService.getData().subscribe(data => {
      console.log(data);
    })
  }


}
