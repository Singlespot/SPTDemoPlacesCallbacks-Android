# Graph Report - SPTDemoPlacesCallbacks-Android  (2026-09-10)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 61 nodes · 94 edges · 17 communities (6 shown, 9 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.68)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `73a88d3b`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Main Activity Setup
- Place Notification Receiver
- Permission Utilities
- Android App Manifest & Assets
- ProximityKit Place Callbacks
- Gradle Wrapper Script
- App Entry Components
- Launcher Icon HDPI Square
- Launcher Icon HDPI Round
- Launcher Icon MDPI
- Launcher Icon MDPI Round
- Launcher Icon XXHDPI Square
- Launcher Icon XXHDPI Round
- Launcher Icon XXXHDPI
- Launcher Icon XXXHDPI Round

## God Nodes (most connected - your core abstractions)
1. `ProximityKit Implementation Guide` - 13 edges
2. `MainActivity` - 9 edges
3. `PlaceCallbackReceiver` - 9 edges
4. `SPTProximityKit.geodata Interface` - 7 edges
5. `SPTProximityKit SDK` - 6 edges
6. `SPTDemoPlacesCallbacks-Android README` - 6 edges
7. `MainActivity` - 5 edges
8. `PermissionUtils` - 4 edges
9. `SPTPlaceCallbackConfig` - 4 edges
10. `AndroidManifest.xml` - 3 edges

## Surprising Connections (you probably didn't know these)
- `App Launcher Icon (xhdpi, square)` --conceptually_related_to--> `SPTDemoPlacesCallbacks-Android README`  [INFERRED]
  app/src/main/res/mipmap-xhdpi/ic_launcher.png → README.md
- `App Launcher Icon (xhdpi, round)` --conceptually_related_to--> `SPTDemoPlacesCallbacks-Android README`  [INFERRED]
  app/src/main/res/mipmap-xhdpi/ic_launcher_round.png → README.md
- `SPTDemoPlacesCallbacks-Android README` --conceptually_related_to--> `ProximityKit Implementation Guide`  [INFERRED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md
- `SPTDemoPlacesCallbacks-Android README` --references--> `MainActivity`  [EXTRACTED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md
- `SPTDemoPlacesCallbacks-Android README` --references--> `AndroidManifest.xml`  [EXTRACTED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md

## Import Cycles
- None detected.

## Hyperedges (group relationships)
- **Place Callback Configuration Flow** — proximitykit_implementation_mainactivity, proximitykit_implementation_sptproximitykit, proximitykit_implementation_geodata, proximitykit_implementation_sptplacecallbackconfig, proximitykit_implementation_placecallbackreceiver, proximitykit_implementation_home_place, proximitykit_implementation_work_place, proximitykit_implementation_custom_place [EXTRACTED 0.85]
- **Default Android Studio Launcher Icon Set (all densities)** — app_src_main_res_mipmap_mdpi_ic_launcher, app_src_main_res_mipmap_mdpi_ic_launcher_round, app_src_main_res_mipmap_xxxhdpi_ic_launcher, app_src_main_res_mipmap_xxxhdpi_ic_launcher_round [EXTRACTED 0.90]
- **SDK Setup Steps** — proximitykit_implementation_buildgradle, proximitykit_implementation_androidmanifest, proximitykit_implementation_mainactivity [EXTRACTED 0.90]

## Communities (17 total, 9 thin omitted)

### Community 0 - "Main Activity Setup"
Cohesion: 0.35
Nodes (4): MainActivity, AppCompatActivity, Bundle, TextView

### Community 1 - "Place Notification Receiver"
Cohesion: 0.49
Nodes (4): PlaceCallbackReceiver, BroadcastReceiver, Context, Intent

### Community 2 - "Permission Utilities"
Cohesion: 0.25
Nodes (3): android, androidx, PermissionUtils

### Community 3 - "Android App Manifest & Assets"
Cohesion: 0.33
Nodes (7): App Launcher Icon (xhdpi, square), App Launcher Icon (xhdpi, round), AndroidManifest.xml, build.gradle (Project), SPTProximityKit.cmp Interface, SPTProximityKit SDK, SPTDemoPlacesCallbacks-Android README

### Community 4 - "ProximityKit Place Callbacks"
Cohesion: 0.57
Nodes (7): ProximityKit Implementation Guide, Custom Place Callback, SPTProximityKit.geodata Interface, Home Place Callback, PlaceTransition Enum, SPTPlaceCallbackConfig, Work Place Callback

### Community 5 - "Gradle Wrapper Script"
Cohesion: 0.70
Nodes (4): gradlew script, die(), save(), warn()

## Knowledge Gaps
- **10 isolated node(s):** `App Launcher Icon (mdpi)`, `App Launcher Icon Round (mdpi)`, `App Launcher Icon (xxhdpi, square)`, `App Launcher Icon (xxhdpi, round)`, `App Launcher Icon (xxxhdpi)` (+5 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 15 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **9 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `PlaceCallbackReceiver` connect `Place Notification Receiver` to `Main Activity Setup`?**
  _High betweenness centrality (0.071) - this node is a cross-community bridge._
- **What connects `App Launcher Icon (mdpi)`, `App Launcher Icon Round (mdpi)`, `App Launcher Icon (xxhdpi, square)` to the rest of the system?**
  _10 weakly-connected nodes found - possible documentation gaps or missing edges._