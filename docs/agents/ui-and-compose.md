# UI & Jetpack Compose

## Design System (`:core:designsystem`)
- Owns Material 3 theme, typography, shapes (`10–12dp` rounded covers), spacing tokens, and stateless reusable primitives.
- Depends only on `:core:model` — never `:core:data`, `:core:database`, or `:core:network`.

## UDF Feature Contract
- Every feature splits into `<Feature>Route` (injects `ViewModel` via `hiltViewModel()`, collects `uiState.collectAsStateWithLifecycle()`) and stateless `<Feature>Screen(state, onAction)`.
- `ViewModel` exposes a single immutable `StateFlow<UiState>` using `SharingStarted.WhileSubscribed(5_000)`.
- Navigation uses type-safe `@Serializable` routes wired in `:app`'s `NavHost`.

## 120 Hz Performance Rules
- Annotate UI state and models with `@Immutable`; use `ImmutableList` for lists.
- Every `LazyColumn`, `LazyRow`, and `LazyVerticalGrid` must provide stable `key` and `contentType`.
- Pre-compute cover dominant colors on a `64x64` thumbnail off the main thread and store the ARGB `Int` in Room; never compute palettes during composition or scroll.
- In Offline Mode, configure Coil 3 requests with `networkCachePolicy = CachePolicy.DISABLED`.
