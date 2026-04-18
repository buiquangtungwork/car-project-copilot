import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Car } from '../models/car.model';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiUrl = 'http://localhost:8081/api/cars';

  constructor(private http: HttpClient) {
    console.log('CarService initialized with API URL:', this.apiUrl);
  }

  getAllCars(): Observable<Car[]> {
    console.log('Fetching all cars from:', this.apiUrl);
    return this.http.get<Car[]>(this.apiUrl).pipe(
      tap(data => {
        console.log('Successfully fetched cars:', data);
      }),
      catchError(this.handleError)
    );
  }

  getCarById(id: number): Observable<Car> {
    console.log(`Fetching car with ID: ${id}`);
    return this.http.get<Car>(`${this.apiUrl}/${id}`).pipe(
      tap(data => {
        console.log('Successfully fetched car:', data);
      }),
      catchError(this.handleError)
    );
  }

  createCar(car: Car): Observable<Car> {
    console.log('Creating car:', car);
    return this.http.post<Car>(this.apiUrl, car).pipe(
      tap(data => {
        console.log('Successfully created car:', data);
      }),
      catchError(this.handleError)
    );
  }

  updateCar(id: number, car: Car): Observable<Car> {
    console.log(`Updating car with ID ${id}:`, car);
    return this.http.put<Car>(`${this.apiUrl}/${id}`, car).pipe(
      tap(data => {
        console.log('Successfully updated car:', data);
      }),
      catchError(this.handleError)
    );
  }

  deleteCar(id: number): Observable<void> {
    console.log(`Deleting car with ID: ${id}`);
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        console.log(`Successfully deleted car with ID: ${id}`);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('HTTP Error:', {
      status: error.status,
      statusText: error.statusText,
      message: error.message,
      error: error.error
    });

    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(() => new Error(errorMessage));
  }
}
