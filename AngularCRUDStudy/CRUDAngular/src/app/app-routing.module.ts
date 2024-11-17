import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { GetEmployeeComponent } from './get-employee/get-employee.component';
import { DeleteEmployeeComponent } from './delete-employee/delete-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

const routes: Routes = [
  {path:"", redirectTo:"addEmployee", pathMatch:"full"},
  {path:"addEmployee", component:AddEmployeeComponent},
  {path:"deleteEmployee", component:DeleteEmployeeComponent},
  {path:"updateEmployee", component:UpdateEmployeeComponent},
  {path:"getEmployee", component:GetEmployeeComponent},
  {path:"getAllEmployees", component:GetEmployeeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
