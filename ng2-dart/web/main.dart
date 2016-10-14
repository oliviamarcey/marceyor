import 'package:angular2/platform/browser.dart';
import 'package:kudos/app.component.dart';
import 'package:http/browser_client.dart';
import 'package:http/http.dart';
import 'package:angular2/core.dart';

void main() {
  bootstrap(AppComponent,
  	[provide(Client, useFactory: () => new BrowserClient(), deps: [])]
  );
}

