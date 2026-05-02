package com.example.vacations.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.vacations.dao.ExcursionDAO;
import com.example.vacations.dao.ExcursionDAO_Impl;
import com.example.vacations.dao.VacationDAO;
import com.example.vacations.dao.VacationDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VacationsDatabaseBuilder_Impl extends VacationsDatabaseBuilder {
  private volatile VacationDAO _vacationDAO;

  private volatile ExcursionDAO _excursionDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `vacations` (`vacationID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vacationName` TEXT, `price` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `excursions` (`excursionID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `excursionName` TEXT, `price` REAL NOT NULL, `vacationID` INTEGER NOT NULL, `excursionDate` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f7a6b88d27472d4f8818d5972064456e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `vacations`");
        db.execSQL("DROP TABLE IF EXISTS `excursions`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsVacations = new HashMap<String, TableInfo.Column>(3);
        _columnsVacations.put("vacationID", new TableInfo.Column("vacationID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVacations.put("vacationName", new TableInfo.Column("vacationName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVacations.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVacations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVacations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVacations = new TableInfo("vacations", _columnsVacations, _foreignKeysVacations, _indicesVacations);
        final TableInfo _existingVacations = TableInfo.read(db, "vacations");
        if (!_infoVacations.equals(_existingVacations)) {
          return new RoomOpenHelper.ValidationResult(false, "vacations(com.example.vacations.entities.Vacation).\n"
                  + " Expected:\n" + _infoVacations + "\n"
                  + " Found:\n" + _existingVacations);
        }
        final HashMap<String, TableInfo.Column> _columnsExcursions = new HashMap<String, TableInfo.Column>(5);
        _columnsExcursions.put("excursionID", new TableInfo.Column("excursionID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExcursions.put("excursionName", new TableInfo.Column("excursionName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExcursions.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExcursions.put("vacationID", new TableInfo.Column("vacationID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExcursions.put("excursionDate", new TableInfo.Column("excursionDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExcursions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExcursions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExcursions = new TableInfo("excursions", _columnsExcursions, _foreignKeysExcursions, _indicesExcursions);
        final TableInfo _existingExcursions = TableInfo.read(db, "excursions");
        if (!_infoExcursions.equals(_existingExcursions)) {
          return new RoomOpenHelper.ValidationResult(false, "excursions(com.example.vacations.entities.Excursion).\n"
                  + " Expected:\n" + _infoExcursions + "\n"
                  + " Found:\n" + _existingExcursions);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f7a6b88d27472d4f8818d5972064456e", "fbdfd2fd135653065fd7ff9375cf4774");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "vacations","excursions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `vacations`");
      _db.execSQL("DELETE FROM `excursions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(VacationDAO.class, VacationDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExcursionDAO.class, ExcursionDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public VacationDAO vacationDAO() {
    if (_vacationDAO != null) {
      return _vacationDAO;
    } else {
      synchronized(this) {
        if(_vacationDAO == null) {
          _vacationDAO = new VacationDAO_Impl(this);
        }
        return _vacationDAO;
      }
    }
  }

  @Override
  public ExcursionDAO excursionDAO() {
    if (_excursionDAO != null) {
      return _excursionDAO;
    } else {
      synchronized(this) {
        if(_excursionDAO == null) {
          _excursionDAO = new ExcursionDAO_Impl(this);
        }
        return _excursionDAO;
      }
    }
  }
}
