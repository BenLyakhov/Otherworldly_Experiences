package com.example.vacations.database;

import android.app.Application;

import com.example.vacations.dao.ExcursionDAO;
import com.example.vacations.dao.VacationDAO;
import com.example.vacations.entities.Excursion;
import com.example.vacations.entities.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// all the code here is from Part 2 of the panopto video series
public class Repository {
    private ExcursionDAO mExcursionDAO;
    private VacationDAO mVacationDAO;
    private List<Vacation> mAllVacations;
    private List<Excursion> mAllExcursions;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        VacationsDatabaseBuilder db=VacationsDatabaseBuilder.getDatabase(application);
        mExcursionDAO=db.excursionDAO();
        mVacationDAO=db.vacationDAO();
    }

//    Vacation stuff
    public List<Vacation>getmAllVacations(){
        databaseExecutor.execute(()-> {
            mAllVacations=mVacationDAO.getAllVacations();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllVacations;
    }

    public Vacation getVacationByID(int id) {
        return  mVacationDAO.getVacationByID(id);
    }

    public void insert(Vacation vacation){
        databaseExecutor.execute(()-> {
            mVacationDAO.insert(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
//         Panopto video uses below code
//            e.printStackTrace();
        }
    }
    public void delete(Vacation vacation) {
        databaseExecutor.execute(() -> {
            mVacationDAO.delete(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }
    }

    public void update(Vacation vacation) {
        databaseExecutor.execute(() -> {
            mVacationDAO.update(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }
    }

// Excursion Stuff
public List<Excursion>getmAllExcursions(){
    databaseExecutor.execute(()-> {
        mAllExcursions=mExcursionDAO.getAllExcursions();
    });
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
//            e.printStackTrace();
    }
    return mAllExcursions;
}

// Associated Excursions List
public List<Excursion>getAssociatedExcursions(int vacationID){
    databaseExecutor.execute(()-> {
        mAllExcursions=mExcursionDAO.getAssociatedExcursions(vacationID);
    });
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
            e.printStackTrace();
    }
    return mAllExcursions;
}
    public void insert(Excursion excursion){
        databaseExecutor.execute(()-> {
            mExcursionDAO.insert(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    public void delete(Excursion excursion) {
        databaseExecutor.execute(() -> {
            mExcursionDAO.delete(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public void update(Excursion excursion) {
        databaseExecutor.execute(() -> {
            mExcursionDAO.update(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public Excursion getExcursionByID(int excursionID) {
        return mExcursionDAO.getExcursionByID(excursionID);
    }
}
