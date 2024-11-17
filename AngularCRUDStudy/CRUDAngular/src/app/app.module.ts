import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { GetEmployeeComponent } from './get-employee/get-employee.component';
import { DeleteEmployeeComponent } from './delete-employee/delete-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

import { AddEmployeeServiceService } from './add-employee/add-employee-service.service';
import { GetEmployeeService } from './get-employee/get-employee.service';
import { DeleteEmployeeService } from './delete-employee/delete-employee.service';
import { UpdateEmployeeService } from './update-employee/update-employee.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    AddEmployeeComponent,
    GetEmployeeComponent,
    DeleteEmployeeComponent,
    UpdateEmployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AddEmployeeServiceService,
    GetEmployeeService,
    UpdateEmployeeService,
    DeleteEmployeeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
