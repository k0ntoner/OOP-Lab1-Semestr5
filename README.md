<p align="center">#Звіт </p>

###Варіант роботи: 12

###Виконав: Кот Андрій Анатолійович

## Завдання полягало в тому, щоб розробити програму, яка в свою чергу буде керувати тарифами. 

## Програма

Програма складається з таких класів:

1. Tariff (interface)

     Methods:
   
     - getId()
     - getName()
2. BasicTariff (AbstractClass) implement Tariff
3. ContractTariff(child class of BasicTariff)
4. PrepaidTariff(child class of BasicTariff)
5. User
6. DataBaseConnection

Також в програмі присутній патерн Repository для кожного класу, ради того, щоб винести логіку роботи з базою даних в окремі класи
  
