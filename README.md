# CartExample

**CartExample** — это демонстрационное Android-приложение для электронной коммерции, построенное на принципах чистой архитектуры (**Clean Architecture**) и строгой многомодульности. Проект демонстрирует современный подход к разработке масштабируемых и поддерживаемых приложений.

### Основные особенности:
*   **Многомодульная архитектура:** Разделение по функциональным фичам (`cart`, `product_list`, `product_detail`) и общим слоям (`core:network`, `core:design_system`, `core:navigation`).
*   **Clean Architecture:** Четкое разделение ответственности на слои `domain` (бизнес-логика), `data` (репозитории и источники данных) и `presentation` (UI).
*   **Фоновая синхронизация:** Использование **WorkManager** для надежной периодической синхронизации данных корзины с сервером.
*   **Собственная Design System:** Библиотека готовых UI-компонентов для обеспечения визуальной целостности всего приложения.

### Технологический стек:
*   **Язык:** Kotlin
*   **UI:** Jetpack Compose (декларативный интерфейс)
*   **DI:** Hilt (Dagger)
*   **Async:** Coroutines & Flow
*   **Network:** Retrofit + Kotlinx Serialization
*   **Jetpack:** WorkManager, Navigation Component, ViewModel
