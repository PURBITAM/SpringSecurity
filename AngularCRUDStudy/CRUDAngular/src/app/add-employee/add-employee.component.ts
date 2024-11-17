import { Component, OnInit } from '@angular/core';
import { AddEmployeeServiceService } from './add-employee-service.service';
import { GetEmployeeService } from '../get-employee/get-employee.service';
import { DeleteEmployeeService } from '../delete-employee/delete-employee.service';
import { UpdateEmployeeService } from '../update-employee/update-employee.service';

import { FormGroup, FormControl } from "@angular/forms";

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee:any={};
  allEmployees:any=[];
  getEmployee:any;
  searchId:any;
  showFlag=false;
  showId:any;

  constructor(private service:AddEmployeeServiceService,private getService:GetEmployeeService, private deleteService:DeleteEmployeeService,private updateService:UpdateEmployeeService) { }

  ngOnInit(): void {
    this.getAllEmployeesComponent();
  }

  public getAllEmployeesComponent(){
    this.getService.getAllEmployeesService().subscribe((data:any)=>{
      this.allEmployees = data;
    })
  }

  public getEmployeeComponent(){
    if(this.searchId)
    {
      this.getService.getEmployeeService(this.searchId).subscribe((data:any)=>{
        this.allEmployees = data;
      })
    }
    else{
      this.getAllEmployeesComponent();
    }
    
  }

  public addEmployeeComponent()
  {
    this.service.addEmployeeService(this.employee).subscribe(
      (data:any)=>{
        this.getAllEmployeesComponent();
    })
    
  }

  public deleteEmployeeComponent(deleteId:any)
  {
    this.deleteService.deleteEmployeeService(deleteId).subscribe((data:any)=>
    {
      this.getAllEmployeesComponent();
    })
  }

  public updateEmployeeComponent(empVar:any)
  {
    this.updateService.updateEmployeeService(empVar).subscribe((data:any)=>
    {
      this.getAllEmployeesComponent();
      this.flagChange(empVar.empId);
    })
  }

  public flagChange(id:any)
  {
    this.showFlag = !this.showFlag;
    this.showId=id;
  }
}
