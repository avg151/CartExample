# Проектирование системы (System Design)

В данном документе описана файловая структура для основных экранов приложения, распределенная по модулям фич.

---

## 1. Экран: Список товаров (Product List)

### Модуль `product_list_domain`
*   `model/ProductItem.kt` — Доменная модель товара.
*   `repo/ProductListRepository.kt` — Интерфейс репозитория для получения списка.
*   `usecase/GetProductListUseCase.kt` — Бизнес-логика получения списка товаров.

### Модуль `product_list_data`
*   `remote/api/ProductListApi.kt` — Интерфейс Retrofit для списка товаров.
*   `remote/dto/ProductDto.kt` — Сетевая модель (DTO).
*   `repo/ProductListRepositoryImpl.kt` — Реализация репозитория.
*   `mapper/ProductMapper.kt` — Преобразование `ProductDto -> ProductItem`.

### Модуль `product_list_presentation`
*   `ui/screen/ProductListScreen.kt` — Основной Compose-экран.
*   `ui/component/ProductListItem.kt` — Компонент элемента списка.
*   `ui/model/ProductUiModel.kt` — Модель данных для UI.
*   `ui/ui_state/ProductListUiState.kt` — Состояние экрана (Loading, Content, Error).
*   `vm/ProductListViewModel.kt` — ViewModel для управления состоянием.
*   `navigation/ProductListEntries.kt` — Регистрация экрана в навигации.

---

## 2. Экран: Детали товара (Product Detail)

### Модуль `product_detail_domain`
*   `model/ProductDetail.kt` — Детальная доменная модель (с описанием).
*   `repo/ProductDetailRepository.kt` — Интерфейс для получения деталей.
*   `usecase/GetProductDetailUseCase.kt` — Получение данных конкретного товара.

### Модуль `product_detail_data`
*   `remote/api/ProductDetailApi.kt` — API для деталей товара.
*   `repo/ProductDetailRepositoryImpl.kt` — Реализация репозитория.
*   `mapper/ProductDetailMapper.kt` — Преобразование `DetailDto -> ProductDetail`.

### Модуль `product_detail_presentation`
*   `ui/screen/ProductDetailScreen.kt` — Экран деталей товара.
*   `ui/model/ProductDetailUiState.kt` — Состояние экрана.
*   `vm/ProductDetailViewModel.kt` — ViewModel экрана деталей.
*   `navigation/ProductDetailEntries.kt` — Описание ключа и входа на экран.

---

## 3. Экран: Корзина (Cart Screen)

### Модуль `cart_domain`
*   `model/Cart.kt` — Модель корзины (включает расчет итогов).
*   `model/CartItem.kt` — Модель товара в корзине.
*   `repo/CartRepository.kt` — Интерфейс управления корзиной.
*   `usecase/ObserveCartUseCase.kt` — Реактивное наблюдение за корзиной.
*   `usecase/SetItemQuantityUseCase.kt` — Изменение количества товара.

### Модуль `cart_data`
*   `repo/CartRepositoryImpl.kt` — Реализация SSOT через Store.
*   `store/CartStore.kt` — Локальное хранилище состояния корзины.
*   `remote/api/CartApi.kt` — API для синхронизации корзины.
*   `mapper/CartMapper.kt` — Мапперы сетевых моделей в доменные.

### Модуль `cart_presentation`
*   `ui/screen/CartScreen.kt` — Основной экран корзины.
*   `ui/component/CartItemRow.kt` — Элемент списка в корзине.
*   `ui/model/CartUiState.kt` — Состояние экрана корзины.
*   `vm/CartViewModel.kt` — ViewModel для управления корзиной.
*   `navigation/CartEntries.kt` — Регистрация экрана корзины.

---

## 4. Общий компонент: Блок корзины (Cart Bottom Bar)

*Примечание: Этот компонент отображается на нескольких экранах (Список и Детали).*

### Модуль `shared:cart_widgets` (или внутри `cart_presentation`)
*   `ui/component/CartSummaryBar.kt` — Виджет с общей суммой и количеством.
*   `vm/CartSummaryViewModel.kt` — Общая ViewModel (или использование `CartViewModel`).

---

## 5. Модули внедрения зависимостей (DI)

Для каждой фичи создается модуль в соответствующем `:di` модуле:
*   `ProductListModule.kt` — `@Provides` для репозиториев и use-cases списка.
*   `ProductDetailModule.kt` — `@Provides` для деталей.
*   `CartModule.kt` — `@Provides` для корзины и Store (Singleton).
