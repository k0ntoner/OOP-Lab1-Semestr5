package services;

import repositories.DataBaseRepository;

public class DataBaseService {
    public void restartDataBase(){
        DataBaseRepository.RestartDatabase();
    }
}
