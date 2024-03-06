import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { FormsModule } from '@angular/forms';
import { RegisterFormComponent  } from './components/register-form/register-form.component';
import { MaterialModule } from './material/material.module';
import { UpdateUserFormComponent } from './components/update-user-form/update-user-form.component';
import { CreateDeviceComponent } from './components/create-device/create-device.component';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { ViewDeviceComponent } from './components/view-device/view-device.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateDeviceFormComponent } from './components/update-device-form/update-device-form.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { HomeClientComponent } from './components/home-client/home-client.component';
import { SensorGeneratorComponent } from './components/sensor-generator/sensor-generator.component';
import { ViewConsumptionComponent } from './components/view-consumption/view-consumption.component';
import { ClientChatComponent } from './components/client-chat/client-chat.component';
import { AdminChatComponent } from './components/admin-chat/admin-chat.component';
import { ReceivedMessageComponent } from './components/received-message/received-message.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    UpdateUserFormComponent,
    CreateDeviceComponent,
    ViewUserComponent,
    ViewDeviceComponent,
    UpdateDeviceFormComponent,
    NavbarComponent,
    HomeAdminComponent,
    HomeClientComponent,
    SensorGeneratorComponent,
    ViewConsumptionComponent,
    ClientChatComponent,
    AdminChatComponent,
    ReceivedMessageComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    HttpClientModule
  ],
  exports: [
    FormsModule,
    MaterialModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
