import { Component, OnInit } from '@angular/core';
import { CarService } from './services/car.service';
import { Car } from './models/car.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  cars: Car[] = [];
  filteredCars: Car[] = [];
  searchTerm: string = '';
  showModal: boolean = false;
  modalMode: 'create' | 'edit' = 'create';
  selectedCar: Car | null = null;
  showDetailModal: boolean = false;
  formCar: Car = { make: '', model: '', year: new Date().getFullYear() };
  loading: boolean = false;
  error: string = '';
  importLoading: boolean = false;
  importMessage: string = '';
  importError: string = '';

  constructor(private carService: CarService) {}

  ngOnInit(): void {
    this.loadCars();
  }

  loadCars(): void {
    this.loading = true;
    this.carService.getAllCars().subscribe({
      next: (data) => {
        this.cars = data;
        this.filterCars();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load cars';
        this.loading = false;
      }
    });
  }

  filterCars(): void {
    if (!this.searchTerm.trim()) {
      this.filteredCars = this.cars;
    } else {
      const term = this.searchTerm.toLowerCase();
      this.filteredCars = this.cars.filter(car =>
        car.make.toLowerCase().includes(term) ||
        car.model.toLowerCase().includes(term) ||
        car.year.toString().includes(term)
      );
    }
  }

  onSearch(event: any): void {
    this.searchTerm = (event.target as HTMLInputElement).value;
    this.filterCars();
  }

  openCreateModal(): void {
    this.modalMode = 'create';
    this.formCar = { make: '', model: '', year: new Date().getFullYear() };
    this.showModal = true;
  }

  openEditModal(car: Car): void {
    this.modalMode = 'edit';
    this.formCar = { ...car };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.formCar = { make: '', model: '', year: new Date().getFullYear() };
  }

  savecar(): void {
    if (!this.formCar.make.trim() || !this.formCar.model.trim() || this.formCar.year <= 0) {
      this.error = 'Please fill all fields correctly';
      return;
    }

    if (this.modalMode === 'create') {
      this.carService.createCar(this.formCar).subscribe({
        next: () => {
          this.loadCars();
          this.closeModal();
          this.error = '';
        },
        error: (err) => {
          this.error = 'Failed to create car';
        }
      });
    } else {
      this.carService.updateCar(this.formCar.id!, this.formCar).subscribe({
        next: () => {
          this.loadCars();
          this.closeModal();
          this.error = '';
        },
        error: (err) => {
          this.error = 'Failed to update car';
        }
      });
    }
  }

  viewDetail(car: Car): void {
    this.selectedCar = car;
    this.showDetailModal = true;
  }

  closeDetailModal(): void {
    this.showDetailModal = false;
    this.selectedCar = null;
  }

  editFromDetail(): void {
    if (this.selectedCar) {
      this.openEditModal(this.selectedCar);
      this.closeDetailModal();
    }
  }

  deleteCar(id: number): void {
    if (confirm('Are you sure you want to delete this car?')) {
      this.carService.deleteCar(id).subscribe({
        next: () => {
          this.loadCars();
          this.error = '';
        },
        error: (err) => {
          this.error = 'Failed to delete car';
        }
      });
    }
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.importCars(file);
    }
  }

  importCars(file: File): void {
    this.importLoading = true;
    this.importError = '';
    this.importMessage = '';

    this.carService.importCars(file).subscribe({
      next: (response) => {
        this.importMessage = response;
        this.loadCars(); // Refresh the list
        this.importLoading = false;
      },
      error: (err) => {
        this.importError = 'Failed to import cars: ' + (err.error || err.message);
        this.importLoading = false;
      }
    });
  }

  exportCsv(): void {
    this.carService.exportCarsCsv();
  }

  exportExcel(): void {
    this.carService.exportCarsExcel();
  }

  downloadCsvTemplate(): void {
    this.carService.downloadCsvTemplate();
  }

  downloadExcelTemplate(): void {
    this.carService.downloadExcelTemplate();
  }
}
