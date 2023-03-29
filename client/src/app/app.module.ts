import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PostformComponent } from './components/postform.component';
import { PostresultComponent } from './components/postresult.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './services/http.service';

@NgModule({
  declarations: [
    AppComponent,
    PostformComponent,
    PostresultComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,

  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
