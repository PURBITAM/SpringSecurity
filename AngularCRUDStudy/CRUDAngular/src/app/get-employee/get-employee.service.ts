import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GetEmployeeService {

  constructor(private http:HttpClient) { }

  public getAllEmployeesService()
  {
    return this.http.get("http://localhost:8080/allEmployees");
  }

  public getEmployeeService(id:any)
  {
    return this.http.get("http://localhost:8080/employee/"+id);
  }
}
