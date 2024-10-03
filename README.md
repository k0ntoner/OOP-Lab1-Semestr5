# Звіт

### Варіант роботи: 12

### Виконав: Кот Андрій Анатолійович

## Завдання полягало в тому, щоб розробити програму, яка в свою чергу буде керувати тарифами. 

## Програма

**Програма складається з таких класів:**
### Models
1. Tariff (interface)
        Methods:
        - getId()
        - getName()
2. BasicTariff (AbstractClass) implement Tariff
        Fields:
        - protected int id;
        - protected String name;
        - protected int restInternet;
        - protected int totalInternet;
4. ContractTariff(child class of BasicTariff)
        Fields:
        - protected int totalMessagesCounts;
        - protected int restMessagesCounts;
        - protected Time totalCallsTime;
        - protected Time restCallsTime;
        - protected double price;
6. PrepaidTariff(child class of BasicTariff)
        Fields:
        - protected double messagesPrice;
        - protected double callsPrice;
        - protected double internetPrice;
8. User
        Fields:
        - private int id;
        - private String username;
        - private String phoneNumber;
        - private double wallet;
        - private Date creationDate;
        - private Tariff tariff;


Також в програмі присутній патерн Repository для кожного класу, ради того, щоб винести логіку роботи з базою даних в окремі класи
### Repositories

1. TariffRepository
        Methods:
        - addTariffForUser(User user, Tariff tariff)

