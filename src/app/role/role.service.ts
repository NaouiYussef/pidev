import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from './role';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';


@Injectable ({
    providedIn : 'root'
})
export class RoleService {
    private apiServerUrl=environment.apiBaseUrl;
    constructor(private http: HttpClient) { }

    public getRoles(): Observable<Role[]> {
        const url = `${this.apiServerUrl}admin/allrole`; // remove the extra slash
        return this.http.get<Role[]>(url);
    }
    public getRoleById(id: number) {
        return this.getRoles().pipe(
          map((roles: Role[]) => {
            const foundRole = roles.find(role => role.id === id);
            return foundRole ;
          })
        );
      }
   


    public deleteRoles(roleId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}admin/deleterole/${roleId}`);
    }
}
