import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  var authService = inject(AuthService);
  if(authService.isAuthenticated() && authService.getRole()==='ADMIN'){
    return true;
   } 
   else{
     inject(Router).navigate(['']);
     return false;
   }
};
