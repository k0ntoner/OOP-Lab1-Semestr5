# Звіт

### Варіант роботи: 12

### Виконав: Кот Андрій Анатолійович

## Завдання

Розробити програму для керування тарифами.

## Реалізація

В даній програмі реалізовані головні принципи ООП:

1. Інкапсуляція - доступ до даних користувачів реалізований через гетери та сетери, за ради запобігання втрачання даних.
2. Наслідування - всі тарифи наслідуються від класу BasicTariff, тому що в кожен тариф має базові поля, та повинен реалізовувати інтерейс Tariff.
3. Поліморфізм - реалізований за допомогою інтерфейсу Tariff який запрошує 3 метода:
        
    - `getId()` - потібен, щоб з довільного тарифу діставати id, та знаходити  відповідний тариф у базі даних, не зв'язуючись з конкретним тарифом.
    - `getName()` - несе у собі такий самий функціонал, як і минулий метод
    - `getPrice()` - потрібен для сортування тарифів за ціною, контрактний тариф має у мобі поле price, а для предплаченого, ціна рахується загальною вартістю послуг тарифа.

Збереження даних реалізовано через базу даних **Mysql**. 

Мова програмування **Java**.
      
## Програма

**Програма складається з наступних класів:**

### Models

1. **Tariff (інтерфейс)**

    **Методи:**
    - `getId()`
    - `getName()`
    - `getPrice()` — якщо це передплачений тариф, повертає загальну вартість усіх пропозицій, якщо контрактний тариф — повертає ціну за місяць.

2. **BasicTariff (abstract class)**, implements `Tariff`

    **Поля:**
    - `protected int id`
    - `protected String name`
    - `protected int restInternet`
    - `protected int totalInternet`

3. **ContractTariff** (sub-class of `BasicTariff`)

    **Поля:**
    - `protected int totalMessagesCounts`
    - `protected int restMessagesCounts`
    - `protected Time totalCallsTime`
    - `protected Time restCallsTime`
    - `protected double price`

4. **PrepaidTariff** (sub-class of `BasicTariff`)

    **Поля:**
    - `protected double messagesPrice`
    - `protected double callsPrice`
    - `protected double internetPrice`

5. **User**

    **Поля:**
    - `private int id`
    - `private String username`
    - `private String phoneNumber`
    - `private double wallet`
    - `private Date creationDate`
    - `private Tariff tariff`

Також в програмі використовується патерн Repository для кожного класу, щоб винести логіку роботи з базою даних в окремі класи.

### Repositories

1. **UserRepository**

    **Методи:**
    - `getUserById(int user_id)` — повертає користувача за його ідентифікатором.
    - `getUserWithTariffById(int user_id)` — повертає користувача разом із підключеним тарифом за його ідентифікатором.
    - `getUserWithTariffByPhoneNumber(String phoneNumber)` — повертає користувача з тарифом за його номером телефону.
    - `getUserByPhoneNumber(String phoneNumber)` — повертає користувача за його номером телефону.
    - `addUser(User user)` — додає нового користувача до бази даних.

2. **PrepaidTariffRepository**

    **Методи:**
    - `getPrepaidTariffByUserId(int user_id)` — повертає передплачений тариф, підключений до користувача за його ідентифікатором.
    - `getAllPrepaidTariffs()` — повертає список усіх передплачених тарифів.

3. **ContractTariffRepository**

    **Методи:**
    - `getContractTariffByUserId(int user_id)` — повертає контрактний тариф, підключений до користувача за його ідентифікатором.
    - `getAllContractTariffs()` — повертає список усіх контрактних тарифів.

4. **TariffRepository**

    **Методи:**
    - `getAllUsersWithTariff()` — повертає всіх користувачів із підключеними тарифами.
    - `findTariffByParameters(int totalMessagesCounts, Time totalCallsTime, int totalInternet, double price)` — шукає контрактний тариф за вказаними параметрами.
    - `findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double internetPrice)` — шукає передплачений тариф за вказаними параметрами.
    - `addTariffForUser(User user, Tariff tariff)` — додає тариф до користувача.
    - `getTariffByName(String name)` — знаходить тариф за його назвою.
    - `addTariff(Tariff tariff)` — додає новий тариф.
    - `getAllContractTariff()` — повертає список усіх контрактних тарифів.
    - `getAllPrepaidTariff()` — повертає список усіх передплачених тарифів.
    - `getAllTariffs()` — повертає список усіх тарифів.
    - `sortTariffsByPrice(List<Tariff> tariffs)` — сортує тарифи за ціною.

5. **DataBaseRepository**

    **Методи:**
    - `RestartDatabase()` — очищує базу дних від усіх даних.
    

   

