# CartExample AI Agent Guide

## Architecture Overview

This is a modular Android app built with Kotlin, following clean architecture principles. The project is structured into core modules and feature modules, each feature split into layers: data, domain, presentation, and DI.

### Module Structure
- **Core Modules** (`core/`): Shared functionality
  - `network/`: Retrofit setup for API calls
  - `design_system/`: Reusable Compose components
  - `navigation/`: Navigation3 contracts and implementation
- **Feature Modules** (`feature/{feature_name}/`): Feature-specific code
  - `{feature}_data/`: Repository implementations, DTOs, mappers
  - `{feature}_domain/`: Models, repository interfaces, use cases (pure Kotlin)
  - `{feature}_presentation/`: Compose UI screens, ViewModels, navigation entries
  - `{feature}_di/`: Hilt dependency injection modules
- **App Module** (`app/`): Application entry point, assembles features

### Data Flow
- **Presentation** (Compose screens) injects Hilt ViewModels
- **ViewModels** use domain use cases, expose StateFlow UI state
- **Use Cases** (domain) call repository interfaces
- **Repositories** (data) implement interfaces, handle data sources (currently fake implementations)
- **DI Modules** provide dependencies in Hilt SingletonComponent

### Navigation
Uses AndroidX Navigation3 with custom NavKeys. Each feature provides navigation entries and keys. App-level navigator is provided via CompositionLocal.

Example: `ProductListKey` (object), `productListEntries()` function adds screen to NavDisplay.

## Key Patterns

### Dependency Injection
- Use Hilt with `@HiltViewModel`, `@Inject` constructors
- Modules in `{feature}_di/` provide use cases and repositories
- Example: `ProductListModule` provides `GetProductListUseCase` and `FakeProductListRepository`

### State Management
- ViewModels use `MutableStateFlow<ProductListUiState>`, expose as `StateFlow`
- UI collects with `collectAsStateWithLifecycle()`
- Handle loading/error/data states in sealed classes or data classes

### Error Handling
- Use `runCatching` in ViewModels for async operations
- Map to UI state (loading, error, data)

### Networking
- Retrofit with Kotlinx Serialization
- DTOs in data modules, map to domain models

### Compose UI
- Screens use `Scaffold` with `WindowInsets.safeDrawing`
- ViewModels injected with `hiltViewModel()`
- Access navigator via `LocalAppNavigator.current`

## Developer Workflows

### Building
- `./gradlew assembleDebug` - Build debug APK
- `./gradlew build` - Full build with tests

### Testing
- `./gradlew test` - Run unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests

### Debugging
- Use Android Studio's Compose preview for UI components
- Logging interceptor enabled in debug builds for network

## Conventions

### Naming
- Packages: `com.example.{module_name}` (e.g., `com.example.product_list_presentation`)
- Namespaces in `build.gradle.kts`: Match package (e.g., `namespace = "com.example.product_list_presentation"`)
- Feature modules: `{feature}_{layer}` (e.g., `product_list_domain`)

### File Structure
- `navigation/`: NavKeys, entry providers
- `ui/screen/`: Main screens
- `ui/ui_state/`: UI state data classes
- `vm/`: ViewModels

### Dependencies
- Managed via `gradle/libs.versions.toml` version catalog
- Use `libs.{library}` in build files

## Integration Points

- **External APIs**: Retrofit clients in `core/network/`
- **Navigation**: Features register entries in `AppRootNavDisplay`
- **Shared UI**: `core/design_system/` for common components
- **Cross-Feature**: Navigator interface in `core/navigation/navigation_api/`

Reference files: `settings.gradle.kts` (modules), `app/build.gradle.kts` (app assembly), `core/navigation/navigation_api/src/main/java/com/example/core/navigation/AppNavigator.kt` (navigator contract)
