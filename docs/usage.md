# Usage

## Connecting a server

1. Open Maigo and enter your Navidrome (or OpenSubsonic) server URL, username, and password.
2. Maigo verifies the connection and performs an initial library sync into the encrypted local database.
3. All credentials are stored encrypted via Android Keystore.

## Online and Offline modes

- **Online mode**: streams tracks not yet stored locally, refreshes metadata from the server (via pull-to-refresh or the refresh action), and flushes queued favorites and scrobbles.
- **Offline mode**: disables all network calls immediately. Uncached tracks and Internet Radio stations appear dimmed so playable local content is obvious at a glance.

## Downloads vs Cache

- **Downloads**: albums, playlists, or tracks you explicitly download remain available offline until you remove them. You can choose original quality or server-side transcoding independently from streaming quality.
- **Cache**: recently streamed tracks and cover artwork are cached automatically up to a configurable size limit and evicted automatically (LRU) without touching explicit downloads.

## Private listening mode

Enable **Private session** when you want playback to stay local: Maigo skips `Now Playing` updates, skips scrobbling, and does not record tracks in local listening history.
