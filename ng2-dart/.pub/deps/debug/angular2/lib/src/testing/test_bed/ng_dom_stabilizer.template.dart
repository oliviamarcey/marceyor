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
import 'ng_dom_stabilizer.dart';
import 'package:angular2/src/core/reflection/reflection.dart' as _ngRef;
import 'dart:async';
import 'package:angular2/di.dart';
import 'package:angular2/src/core/zone/ng_zone.dart';
import 'package:angular2/di.template.dart' as i0;
import 'package:angular2/src/core/zone/ng_zone.template.dart' as i1;
export 'ng_dom_stabilizer.dart';

var _visited = false;
void initReflector() {
if (_visited) return; _visited = true;
_ngRef.reflector
..registerType(NgZoneStabilizer, new _ngRef.ReflectionInfo(
const [const Injectable()],
const [const [NgZone]],
(NgZone _ngZone) => new NgZoneStabilizer(_ngZone))
)
;
i0.initReflector();
i1.initReflector();
}
