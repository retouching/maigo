# Media Playback (`:core:media`)

- **Engine**: Jetpack Media3 (`ExoPlayer` + `MediaSessionService`) supporting background playback, lockscreen/Bluetooth controls, audio focus, and gapless playback.
- **Hybrid `DataSource` resolution**:
  1. Explicit offline downloads (`DownloadManager` + persistent `SimpleCache`, never auto-evicted).
  2. Streaming cache (`SimpleCache` with configurable max size and LRU eviction).
  3. Remote stream URL from `MusicProvider` (blocked when Offline Mode is enabled).
- **Audio processing**: ReplayGain loudness normalization (track/album tags from OpenSubsonic) and equalizer (presets + per-band control attached to Media3 `audioSessionId` / `AudioProcessor`).
- **Internet Radio**: streams live URLs from `getInternetRadioStations` natively with ICY metadata extraction (`onMediaMetadataChanged`), displayed with a `LIVE` badge and dimmed in Offline Mode.
