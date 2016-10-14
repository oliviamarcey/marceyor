import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import 'package:http/http.dart';

import 'service/user.service.dart';
import 'service/activity.service.dart';
import 'service/kudos.service.dart';
import 'service/auth.service.dart';

import 'component/home.component.dart';
import 'component/sign-in.component.dart';

@Component(
    selector: 'app-root',
    templateUrl: './app.component.html',
    directives: const [
      ROUTER_DIRECTIVES
    ],
    providers: const [
      ROUTER_PROVIDERS,
      UserService,
      KudosService,
      ActivityService,
      AuthService
    ]
)
@RouteConfig(const [
  const Route(
      path: 'sign-in',
      name: 'SignIn',
      component: SignInComponent,
      useAsDefault: true
  ),
  const Route(
      path: '',
      name: 'Home',
      component: HomeComponent)
])
class AppComponent { }