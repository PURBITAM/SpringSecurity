import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DeleteEmployeeService {

  constructor(private http:HttpClient) { }

  public deleteEmployeeService(id:any)
  {
    return this.http.delete("http://localhost:8080/deleteEmployee/"+id);
  }
}
