package com.example.oe.database;

import android.app.Application;

import com.example.oe.dao.ExcursionDAO;
import com.example.oe.dao.VacationDAO;
import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


// all the code here is from Part 2 of the panopto video series
public class Repository {
    private ExcursionDAO mExcursionDAO;
    private VacationDAO mVacationDAO;
    private List<Vacation> mAllVacations;
    private List<Excursion> mAllExcursions;
    private String search;

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

//    public List<Vacation>getVacationBySearch(String search){
//        databaseExecutor.execute(() -> {
//            mAllVacations= mVacationDAO.getVacationBySearch(search);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
//                e.printStackTrace();
//        }
//        return mAllVacations;
//    }

//  The above getVacationBySearch function seems to not be loading all the vacations in the report
//  currently, on the first line gets loaded
//  rewriting the function so that all the data loads

public List<Vacation> getVacationBySearch(String search) {
    Future<List<Vacation>> future = databaseExecutor.submit(() ->
            mVacationDAO.getVacationBySearch(search)
            );
    try {
        return future.get();
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
//    return new ArrayList<>();
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

//    public List<Excursion> getExcursionBySearch(String search) {
//        databaseExecutor.execute(() -> {
//            mAllExcursions= mExcursionDAO.getExcursionBySearch(search);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//        return mAllExcursions;
//    }

    public List<Excursion> getExcursionBySearch(String search) {
    Future<List<Excursion>> future = databaseExecutor.submit(() ->
            mExcursionDAO.getExcursionBySearch(search)
            );
    try {
        return future.get();
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
//    return new ArrayList<>();
}
}
