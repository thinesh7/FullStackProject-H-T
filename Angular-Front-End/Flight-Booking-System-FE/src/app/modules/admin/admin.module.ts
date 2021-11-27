import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AddAirlineComponent } from './admin-components/add-airline/add-airline.component';
import { AdminMenuComponent } from './admin-components/admin-menu/admin-menu.component';
import { AdminLoginComponent } from './admin-components/admin-login/admin-login.component';
import { AdminDashboardComponent } from './admin-components/admin-dashboard/admin-dashboard.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginGuardGuard } from 'src/app/guards/login-guard.guard';
import { ManageFlightsComponent } from './admin-components/manage-flights/manage-flights.component';
import { ManageDiscountComponent } from './admin-components/manage-discount/manage-discount.component';
import { ManageScheduledFlightsComponent } from './admin-components/manage-scheduled-flights/manage-scheduled-flights.component';
import { AdminService } from 'src/app/service/admin.service';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { ReportsComponent } from './admin-components/reports/reports.component';
import { UserTicketsComponent } from './admin-components/user-tickets/user-tickets.component';

@NgModule({
  declarations: [AddAirlineComponent, AdminMenuComponent, AdminLoginComponent, AdminDashboardComponent, ManageFlightsComponent, ManageDiscountComponent, ManageScheduledFlightsComponent, ReportsComponent, UserTicketsComponent],
  imports: [CommonModule, AdminRoutingModule, ReactiveFormsModule, RouterModule, MatNativeDateModule, MatFormFieldModule,
    MatInputModule, MatRadioModule, MatSelectModule, MatAutocompleteModule],
  providers: [LoginGuardGuard, AdminService]
})
export class AdminModule { }
