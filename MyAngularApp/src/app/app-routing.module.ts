import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { CreateDeviceComponent } from './components/create-device/create-device.component';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { ViewDeviceComponent } from './components/view-device/view-device.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { authGuard } from './guards/auth.guard';
import { roleGuard } from './guards/role.guard';
import { HomeClientComponent } from './components/home-client/home-client.component';
import { SensorGeneratorComponent } from './components/sensor-generator/sensor-generator.component';
import { ViewConsumptionComponent } from './components/view-consumption/view-consumption.component';
import { ClientChatComponent } from './components/client-chat/client-chat.component';
import { AdminChatComponent } from './components/admin-chat/admin-chat.component';

const routes: Routes = [
  {path:'', component: LoginFormComponent},
  {path: 'register', component: RegisterFormComponent},
  {path:'device/create',component:CreateDeviceComponent,canActivate:[roleGuard]},
  {path:'user/display',component:ViewUserComponent,canActivate:[roleGuard]},
  {path:'device/display',component:ViewDeviceComponent,canActivate:[roleGuard]},
  {path:'homeAdmin',component:HomeAdminComponent,canActivate:[roleGuard]},
  {path:'homeClient',component:HomeClientComponent,canActivate:[authGuard]},
  {path:'sensorGenerator',component:SensorGeneratorComponent,canActivate:[roleGuard]},
  {path:'viewConsumption/:numberData',component:ViewConsumptionComponent,canActivate:[authGuard]},
  {path:'clientChat',component:ClientChatComponent,canActivate:[authGuard]},
  {path:'adminChat',component:AdminChatComponent,canActivate:[roleGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
