# Data, Encryption & Offline-First Sync

## Encrypted Room (`:core:database`)
- Room opens through **SQLCipher for Android** (`SupportOpenHelperFactory(passphrase)`) using a random 256-bit key stored in Android Keystore via `:core:security`'s `SecretStore`.
- Zero the passphrase `ByteArray` (`fill(0)`) immediately after opening the database.
- WAL mode enabled, `exportSchema = true` under `core/database/schemas/`, explicit `Migration` objects for every version bump, never `fallbackToDestructiveMigration()` in production.
- Use FTS4/FTS5 virtual tables for instant local search across tracks, artists, and albums.
- Local `PlaylistFolderEntity` supports organizing playlists into folders client-side.

## Offline-First SSOT (`:core:data`)
- UI reads exclusively from Room `Flow`s.
- Network sync writes into Room inside transactions; incremental refresh runs on pull-to-refresh or manual refresh in Online Mode.
- Offline mutations (favorites, scrobbles) write optimistically to Room + `PendingActionEntity` outbox and flush when Online Mode is active and connectivity returns.

## Modes
- **Online / Offline toggle**: persisted in DataStore; Offline Mode short-circuits all network calls cleanly and dims uncached tracks and radio stations in the UI.
- **Private listening mode**: suppresses `Now Playing` (`scrobble?submission=false`), scrobbles (`scrobble?submission=true`), and local listening history writes.
