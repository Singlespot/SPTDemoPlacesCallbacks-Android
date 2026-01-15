# ProximityKit SDK Implementation Guide

## Overview
This project demonstrates the implementation of the SPT ProximityKit SDK for Android (version 356). The SDK allows you to configure place-based callbacks that trigger when users enter or exit specific geographic areas.

## Key Features Implemented

### 1. Place Callbacks
- **Home Place**: Triggers when entering home area (after 4pm or before 9am)
- **Work Place**: Triggers when exiting work area (between 9am and 5pm)
- **Custom Place**: Configurable place with custom coordinates and trigger rules

### 2. Permission Handling
- Runtime permission requests for location access
- Support for background location (Android 10+)
- Graceful handling of permission denial

### 3. Configuration Options
- New API initialization with apiKey and apiSecret
- Time-based triggers with minimum intervals
- Custom place configuration with radius settings
- Uses geodata interface for place callbacks

## Setup Instructions

### 1. Add Credentials
In `build.gradle` (Project level), update the Singlespot repository credentials:
```gradle
maven {
    url "https://sdk.singlespot.com/artifactory/SPTProximityKit"
    credentials {
        username = "YOUR_USERNAME" // Change with your account's username
        password = "YOUR_PASSWORD" // Change with your account's password
    }
}
```

### 2. Add API Keys
In `AndroidManifest.xml`, update the API credentials:
```xml
<meta-data
    android:name="com.sptproximitykit.ApiKey"
    android:value="YOUR_API_KEY" />
<meta-data
    android:name="com.sptproximitykit.ApiSecret"
    android:value="YOUR_API_SECRET" />
```

### 3. Update MainActivity
Update the initialization in MainActivity with your credentials:
```java
private void initSinglespot(final Activity activity) {
    String apiKey = "YOUR_API_KEY";
    String apiSecret = "YOUR_API_SECRET";
    
    SPTProximityKit.init(activity.getApplicationContext(), apiKey, apiSecret);
    
    setSinglespotPlacesCallback();
    SPTProximityKit.geodata.registerPlaceBroadcastReceiver(activity, new PlaceCallbackReceiver());
}
```

## Usage

### Basic Initialization (New API v356)
```java
// Initialize with apiKey and apiSecret (activation is by default)
SPTProximityKit.init(context, apiKey, apiSecret);
```

### Configure Home Place
```java
SPTPlaceCallbackConfig homeConfig = new SPTPlaceCallbackConfig(16, 9, 0);
SPTProximityKit.geodata.setEnterHomeCallback(context, homeConfig);
```

### Configure Work Place
```java
SPTPlaceCallbackConfig workConfig = new SPTPlaceCallbackConfig(9, 17, 0);
SPTProximityKit.geodata.setExitWorkCallback(context, workConfig);
```

### Configure Custom Place
```java
SPTPlaceCallbackConfig customConfig = new SPTPlaceCallbackConfig.Builder()
    .setPlaceId(CUSTOM_PLACE_ID)
    .setLatitude(23.0)
    .setLongitude(25.0)
    .setPlaceTransition(PlaceTransition.ENTER)
    .setDistanceTrigger(70)
    .setAfterHourOfTheDay(0)
    .setBeforeHourOfTheDay(24)
    .setMinHoursBetweenEvents(0)
    .build();
SPTProximityKit.geodata.setCustomPlaceCallback(context, customConfig);
```

### Register Broadcast Receiver
```java
SPTProximityKit.geodata.registerPlaceBroadcastReceiver(activity, new PlaceCallbackReceiver());
```

## API Changes from v2.5.0 to v356

1. **Initialization**: 
   - Old: `init(activity, locMode, cmpMode)`
   - New: `init(context, apiKey, apiSecret)` + `activate(context)`

2. **Place Callbacks**:
   - Old: `SPTProximityKit.setEnterHomeCallback(...)`
   - New: `SPTProximityKit.geodata.setEnterHomeCallback(...)`

3. **CMP Management**:
   - CMP is now managed through `SPTProximityKit.cmp` interface
   - Location permissions are handled by the app (not SDK)

## Permissions Required

The app requires the following permissions:
- `ACCESS_FINE_LOCATION` - For precise location tracking
- `ACCESS_COARSE_LOCATION` - For approximate location
- `ACCESS_BACKGROUND_LOCATION` - For background place detection (Android 10+)
- `INTERNET` - For server-based location requests
- `RECEIVE_BOOT_COMPLETED` - For auto-start on device boot
- `WAKE_LOCK` - For background processing

## Testing

1. Install the app on a physical device (location simulation doesn't work with geofencing)
2. Grant all location permissions when prompted
3. For background location, grant "Allow all the time" permission
4. Move to the configured locations to trigger callbacks
5. Check notifications for place events

## Notes

- This implementation uses SDK version 356
- Ensure location is enabled on the device
- Background location requires additional user consent on Android 10+
- Place detection accuracy depends on GPS signal and location settings
- The app must handle location permissions manually in this version
