import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UpdateEmployeeService {

  constructor(private http:HttpClient) { }

  public updateEmployeeService(user:any)
  {
    return this.http.put("http://localhost:8080/updateEmployee",user,
      {responseType:'text' as 'json'});
  }

}
