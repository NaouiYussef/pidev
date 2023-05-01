import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardBackComponent } from './dashboard-back.component';

describe('DashboardBackComponent', () => {
  let component: DashboardBackComponent;
  let fixture: ComponentFixture<DashboardBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardBackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
