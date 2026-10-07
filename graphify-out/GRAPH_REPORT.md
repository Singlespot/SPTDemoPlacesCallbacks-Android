# Graph Report - SPTDemoPlacesCallbacks-Android  (2026-10-07)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 59 nodes · 92 edges · 15 communities (1 shown, 14 thin omitted)
- Extraction: 97% EXTRACTED · 3% INFERRED · 0% AMBIGUOUS · INFERRED: 3 edges (avg confidence: 0.73)
- Token cost: 46,665 input · 64 output

## Graph Freshness
- Built from commit: `d9573453`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Main Activity Setup
- Place Notification Receiver
- Permission Utilities
- Android App Manifest & Assets
- App Entry Components
- App Build Config
- Launcher Icon HDPI Square
- Launcher Icon HDPI Round
- Launcher Icon MDPI
- Square App Launcher Icon
- Round App Launcher Icon
- High-Density Launcher Icon

## God Nodes (most connected - your core abstractions)
1. `ProximityKit Implementation Guide` - 13 edges
2. `MainActivity` - 9 edges
3. `PlaceCallbackReceiver` - 9 edges
4. `SPTProximityKit.geodata Interface` - 7 edges
5. `SPTProximityKit SDK` - 6 edges
6. `MainActivity` - 5 edges
7. `PermissionUtils` - 4 edges
8. `SPTDemoPlacesCallbacks-Android README` - 4 edges
9. `SPTPlaceCallbackConfig` - 4 edges
10. `AndroidManifest.xml` - 3 edges

## Surprising Connections (you probably didn't know these)
- `SPTDemoPlacesCallbacks-Android README` --conceptually_related_to--> `ProximityKit Implementation Guide`  [INFERRED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md
- `SPTDemoPlacesCallbacks-Android README` --references--> `AndroidManifest.xml`  [EXTRACTED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md
- `SPTDemoPlacesCallbacks-Android README` --references--> `build.gradle (Project)`  [EXTRACTED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md
- `SPTDemoPlacesCallbacks-Android README` --references--> `MainActivity`  [EXTRACTED]
  README.md → PROXIMITYKIT_IMPLEMENTATION.md

## Import Cycles
- None detected.

## Hyperedges (group relationships)
- **Place Callback Configuration Flow** — proximitykit_implementation_mainactivity, proximitykit_implementation_sptproximitykit, proximitykit_implementation_geodata, proximitykit_implementation_sptplacecallbackconfig, proximitykit_implementation_placecallbackreceiver, proximitykit_implementation_home_place, proximitykit_implementation_work_place, proximitykit_implementation_custom_place [EXTRACTED 0.85]
- **Default Android Studio Launcher Icon Set (all densities)** — app_src_main_res_mipmap_mdpi_ic_launcher, app_src_main_res_mipmap_mdpi_ic_launcher_round, app_src_main_res_mipmap_xxxhdpi_ic_launcher, app_src_main_res_mipmap_xxhdpi_ic_launcher_round [EXTRACTED 0.90]
- **SDK Setup Steps** — proximitykit_implementation_buildgradle, proximitykit_implementation_androidmanifest, proximitykit_implementation_mainactivity [EXTRACTED 0.90]
- **App Launcher Icon Density/Shape Variants** — app_src_main_res_mipmap_mdpi_ic_launcher_round, app_src_main_res_mipmap_xhdpi_ic_launcher, app_src_main_res_mipmap_xhdpi_ic_launcher_round, app_src_main_res_mipmap_xxhdpi_ic_launcher, app_src_main_res_mipmap_xxhdpi_ic_launcher_round [INFERRED 0.90]

## Communities (15 total, 14 thin omitted)

### Community 0 - "Main Activity Setup"
Cohesion: 0.32
Nodes (14): ProximityKit Implementation Guide, AndroidManifest.xml, build.gradle (Project), SPTProximityKit.cmp Interface, Custom Place Callback, SPTProximityKit.geodata Interface, Home Place Callback, MainActivity (+6 more)

## Knowledge Gaps
- **8 isolated node(s):** `App Launcher Icon (xxxhdpi)`, `App Launcher Icon (hdpi, square)`, `App Launcher Icon (mdpi)`, `App Launcher Icon Round (mdpi)`, `App Launcher Icon (Round, XHDPI)` (+3 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 13 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **14 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `PlaceCallbackReceiver` connect `Permission Utilities` to `Place Notification Receiver`?**
  _High betweenness centrality (0.076) - this node is a cross-community bridge._
- **What connects `App Launcher Icon (xxxhdpi)`, `App Launcher Icon (hdpi, square)`, `App Launcher Icon (mdpi)` to the rest of the system?**
  _8 weakly-connected nodes found - possible documentation gaps or missing edges._