// @ignoreProblemForFile annotate_overrides
// @ignoreProblemForFile cancel_subscriptions
// @ignoreProblemForFile constant_identifier_names
// @ignoreProblemForFile non_constant_identifier_names
// @ignoreProblemForFile implementation_imports
// @ignoreProblemForFile library_prefixes
// @ignoreProblemForFile type_annotate_public_apis
// @ignoreProblemForFile STRONG_MODE_DOWN_CAST_COMPOSITE
// @ignoreProblemForFile UNUSED_IMPORT
// @ignoreProblemForFile UNUSED_SHOWN_NAME
// @ignoreProblemForFile UNUSED_LOCAL_VARIABLE
import 'dom_sanitization_service_impl.dart';
import 'package:angular2/src/core/reflection/reflection.dart' as _ngRef;
import 'html_sanitizer.dart';
import 'style_sanitizer.dart';
import 'url_sanitizer.dart';
import '../core/security.dart';
import '../core/di.dart' show Injectable;
import 'dom_sanitization_service.dart';
import 'html_sanitizer.template.dart' as i0;
import 'style_sanitizer.template.dart' as i1;
import 'url_sanitizer.template.dart' as i2;
import '../core/security.template.dart' as i3;
import '../core/di.template.dart' as i4;
import 'dom_sanitization_service.template.dart' as i5;
export 'dom_sanitization_service_impl.dart';

var _visited = false;
void initReflector() {
if (_visited) return; _visited = true;
_ngRef.reflector
..registerType(DomSanitizationServiceImpl, new _ngRef.ReflectionInfo(
const [const Injectable()],
const [],
() => new DomSanitizationServiceImpl(),
const [DomSanitizationService])
)
;
i0.initReflector();
i1.initReflector();
i2.initReflector();
i3.initReflector();
i4.initReflector();
i5.initReflector();
}
