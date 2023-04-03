import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostformComponent } from './components/postform.component';
import { PostresultComponent } from './components/postresult.component';

const routes: Routes = [
  { path: '', component: PostformComponent },
  { path: 'results', component: PostresultComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
