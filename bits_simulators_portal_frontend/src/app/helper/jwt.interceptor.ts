import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import {jwtDecode} from 'jwt-decode';



@Injectable()
export class JwtInterceptor implements HttpInterceptor {



  constructor(
    private authenticationService: AuthService,
    private router: Router
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.authenticationService.userValue;
    const isLoggedIn = user?.access_token;
     //console.log("token===>"+user?.access_token);
    
     //console.log("test request 1111: ", request);

     //const decodedToken = jwtDecode(user.access_token);
    // // console.log("Decoded Token: ", decodedToken);

    
    
    
    if (isLoggedIn) {
     
       
        request = request.clone({
            setHeaders: {
                Authorization: `Bearer ${user?.access_token}`
            }
        });
    }


  
  
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          if (event.body.respCode === "005") {
            this.authenticationService.logout(); 
          }
        }
      }),
      catchError((error: HttpErrorResponse) => {
        // console.log("test request: ", request);
        
        if (error.error && error.error.respCode === '005') {
          this.router.navigate(["/sign-in"]); 
        }
        return throwError(error);
      })
    );
  }
}
