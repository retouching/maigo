# Privacy

Maigo is designed to keep your listening habits and server credentials strictly under your control.

## Guarantees

- **Zero telemetry and zero analytics**: Maigo includes no analytics, crash-reporting, advertising, or tracking SDKs.
- **No Google Play Services**: works on stock Android, GrapheneOS, LineageOS, and F-Droid builds without proprietary Google libraries.
- **Encrypted storage at rest**: the local SQLite database is encrypted with SQLCipher using a randomly generated 256-bit key protected by Android Keystore. Server credentials and tokens are also encrypted via Android Keystore.
- **Private listening mode**: suppresses `Now Playing` broadcasts, scrobbles, and local history entries.

## Permissions

| Permission | Reason |
|---|---|
| `INTERNET` | Stream music and sync metadata with your configured server |
| `ACCESS_NETWORK_STATE` | Detect connectivity changes for automatic offline fallback and sync |
| `FOREGROUND_SERVICE` / `FOREGROUND_SERVICE_MEDIA_PLAYBACK` | Keep audio playback running reliably when the screen is off |
| `POST_NOTIFICATIONS` | Show media playback controls and offline download progress |
