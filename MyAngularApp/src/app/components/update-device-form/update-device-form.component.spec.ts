import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDeviceFormComponent } from './update-device-form.component';

describe('UpdateDeviceFormComponent', () => {
  let component: UpdateDeviceFormComponent;
  let fixture: ComponentFixture<UpdateDeviceFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateDeviceFormComponent]
    });
    fixture = TestBed.createComponent(UpdateDeviceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
