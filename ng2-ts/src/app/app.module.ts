import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { routing, routedComponents } from './app.routes';

import './rxjs-extensions';

import { CommentPipe } from './pipe/comment.pipe';
import { TimeAgoPipe } from './pipe/time-ago.pipe';

import { AuthService } from './service/auth.service';
import { UserService } from './service/user.service';
import { KudosService } from './service/kudos.service';
import { ActivityService } from './service/activity.service';

import { AuthGuard } from './shared/auth.guard';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    routedComponents,
    CommentPipe,
    TimeAgoPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    AuthService,
    UserService,
    KudosService,
    ActivityService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
