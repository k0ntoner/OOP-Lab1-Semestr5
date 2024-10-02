package services;

import repositories.DataBaseRepository;

public class DataBaseService {
    private final DataBaseRepository dataBaseRepository;
    public DataBaseService(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }
    public void restartDataBase(){
        dataBaseRepository.RestartDatabase();
    }
}
