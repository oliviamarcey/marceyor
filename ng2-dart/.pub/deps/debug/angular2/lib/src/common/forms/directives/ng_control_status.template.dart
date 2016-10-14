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
import 'ng_control_status.dart';
import 'package:angular2/src/core/reflection/reflection.dart' as _ngRef;
import 'package:angular2/core.dart' show Directive, Self;
import 'ng_control.dart' show NgControl;
import 'package:angular2/core.template.dart' as i0;
import 'ng_control.template.dart' as i1;
export 'ng_control_status.dart';

var _visited = false;
void initReflector() {
if (_visited) return; _visited = true;
_ngRef.reflector
..registerType(NgControlStatus, new _ngRef.ReflectionInfo(
const [],
const [const [NgControl, const Self()]],
(NgControl cd) => new NgControlStatus(cd))
)
;
i0.initReflector();
i1.initReflector();
}