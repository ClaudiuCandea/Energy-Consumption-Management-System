import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorGeneratorComponent } from './sensor-generator.component';

describe('SensorGeneratorComponent', () => {
  let component: SensorGeneratorComponent;
  let fixture: ComponentFixture<SensorGeneratorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SensorGeneratorComponent]
    });
    fixture = TestBed.createComponent(SensorGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
