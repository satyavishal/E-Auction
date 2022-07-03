import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FetchService {

  private baseUrl = "https://api.zippopotam.us/us/33162";

  constructor(private http: HttpClient) {
  }

  //  getProducts(): Observable<Product[]>{
  //    return this.http.get<Product[]>(`${this.baseUrl}`);

  //  }
  getData() {
    return this.http.get(this.baseUrl);
  }
}
