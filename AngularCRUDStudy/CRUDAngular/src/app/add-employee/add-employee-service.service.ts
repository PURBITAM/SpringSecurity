import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddEmployeeServiceService {

  constructor(private http:HttpClient) { }

  public addEmployeeService(user:any)
  {
    console.log("inside addEmployeeService");
    return this.http.post("http://localhost:8080/addEmployee",user,
      {responseType:'text' as 'json'}
    );
  }
}
