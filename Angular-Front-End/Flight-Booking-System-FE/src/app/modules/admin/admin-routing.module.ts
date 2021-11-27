import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuardGuard } from 'src/app/guards/login-guard.guard';
import { AddAirlineComponent } from './admin-components/add-airline/add-airline.component';
import { AdminDashboardComponent } from './admin-components/admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './admin-components/admin-login/admin-login.component';
import { ManageDiscountComponent } from './admin-components/manage-discount/manage-discount.component';
import { ManageFlightsComponent } from './admin-components/manage-flights/manage-flights.component';
import { ManageScheduledFlightsComponent } from './admin-components/manage-scheduled-flights/manage-scheduled-flights.component';
import { ReportsComponent } from './admin-components/reports/reports.component';
import { UserTicketsComponent } from './admin-components/user-tickets/user-tickets.component';

const routes: Routes = [
  { path: "login", component: AdminLoginComponent },
  {
    path: "dashboard", component: AdminDashboardComponent, canActivate: [LoginGuardGuard],
    children: [
      { path: "add-airline", component: AddAirlineComponent, canActivate: [LoginGuardGuard] },
      { path: "manage-flight", component: ManageFlightsComponent, canActivate: [LoginGuardGuard] },
      { path: "manage-discount", component: ManageDiscountComponent, canActivate: [LoginGuardGuard] },
      { path: "manage-scheduled-flight", component: ManageScheduledFlightsComponent, canActivate: [LoginGuardGuard] },
      { path: "reports", component: ReportsComponent, canActivate: [LoginGuardGuard] },
      { path: "user-reports", component: UserTicketsComponent, canActivate: [LoginGuardGuard] }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
