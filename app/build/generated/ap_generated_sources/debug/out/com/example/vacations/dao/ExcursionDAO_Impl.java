package com.example.vacations.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.vacations.entities.Excursion;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExcursionDAO_Impl implements ExcursionDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Excursion> __insertionAdapterOfExcursion;

  private final EntityDeletionOrUpdateAdapter<Excursion> __deletionAdapterOfExcursion;

  private final EntityDeletionOrUpdateAdapter<Excursion> __updateAdapterOfExcursion;

  public ExcursionDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExcursion = new EntityInsertionAdapter<Excursion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `excursions` (`excursionID`,`excursionName`,`price`,`vacationID`,`excursionDate`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Excursion entity) {
        statement.bindLong(1, entity.getExcursionID());
        if (entity.getExcursionName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getExcursionName());
        }
        statement.bindDouble(3, entity.getPrice());
        statement.bindLong(4, entity.getVacationID());
        if (entity.getExcursionDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExcursionDate());
        }
      }
    };
    this.__deletionAdapterOfExcursion = new EntityDeletionOrUpdateAdapter<Excursion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `excursions` WHERE `excursionID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Excursion entity) {
        statement.bindLong(1, entity.getExcursionID());
      }
    };
    this.__updateAdapterOfExcursion = new EntityDeletionOrUpdateAdapter<Excursion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `excursions` SET `excursionID` = ?,`excursionName` = ?,`price` = ?,`vacationID` = ?,`excursionDate` = ? WHERE `excursionID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Excursion entity) {
        statement.bindLong(1, entity.getExcursionID());
        if (entity.getExcursionName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getExcursionName());
        }
        statement.bindDouble(3, entity.getPrice());
        statement.bindLong(4, entity.getVacationID());
        if (entity.getExcursionDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExcursionDate());
        }
        statement.bindLong(6, entity.getExcursionID());
      }
    };
  }

  @Override
  public void insert(final Excursion excursion) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExcursion.insert(excursion);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Excursion excursion) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExcursion.handle(excursion);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Excursion excursion) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfExcursion.handle(excursion);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Excursion> getAllExcursions() {
    final String _sql = "SELECT * FROM EXCURSIONS ORDER BY excursionID ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfExcursionID = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionID");
      final int _cursorIndexOfExcursionName = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionName");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfVacationID = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationID");
      final int _cursorIndexOfExcursionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionDate");
      final List<Excursion> _result = new ArrayList<Excursion>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Excursion _item;
        final int _tmpExcursionID;
        _tmpExcursionID = _cursor.getInt(_cursorIndexOfExcursionID);
        final String _tmpExcursionName;
        if (_cursor.isNull(_cursorIndexOfExcursionName)) {
          _tmpExcursionName = null;
        } else {
          _tmpExcursionName = _cursor.getString(_cursorIndexOfExcursionName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final int _tmpVacationID;
        _tmpVacationID = _cursor.getInt(_cursorIndexOfVacationID);
        final String _tmpExcursionDate;
        if (_cursor.isNull(_cursorIndexOfExcursionDate)) {
          _tmpExcursionDate = null;
        } else {
          _tmpExcursionDate = _cursor.getString(_cursorIndexOfExcursionDate);
        }
        _item = new Excursion(_tmpExcursionID,_tmpExcursionName,_tmpPrice,_tmpVacationID,_tmpExcursionDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Excursion> getAssociatedExcursions(final int vaca) {
    final String _sql = "SELECT * FROM EXCURSIONS WHERE vacationID=? ORDER BY excursionID ASC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vaca);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfExcursionID = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionID");
      final int _cursorIndexOfExcursionName = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionName");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfVacationID = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationID");
      final int _cursorIndexOfExcursionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionDate");
      final List<Excursion> _result = new ArrayList<Excursion>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Excursion _item;
        final int _tmpExcursionID;
        _tmpExcursionID = _cursor.getInt(_cursorIndexOfExcursionID);
        final String _tmpExcursionName;
        if (_cursor.isNull(_cursorIndexOfExcursionName)) {
          _tmpExcursionName = null;
        } else {
          _tmpExcursionName = _cursor.getString(_cursorIndexOfExcursionName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final int _tmpVacationID;
        _tmpVacationID = _cursor.getInt(_cursorIndexOfVacationID);
        final String _tmpExcursionDate;
        if (_cursor.isNull(_cursorIndexOfExcursionDate)) {
          _tmpExcursionDate = null;
        } else {
          _tmpExcursionDate = _cursor.getString(_cursorIndexOfExcursionDate);
        }
        _item = new Excursion(_tmpExcursionID,_tmpExcursionName,_tmpPrice,_tmpVacationID,_tmpExcursionDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Excursion getExcursionByID(final int id) {
    final String _sql = "SELECT * FROM excursions WHERE excursionID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfExcursionID = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionID");
      final int _cursorIndexOfExcursionName = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionName");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfVacationID = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationID");
      final int _cursorIndexOfExcursionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "excursionDate");
      final Excursion _result;
      if (_cursor.moveToFirst()) {
        final int _tmpExcursionID;
        _tmpExcursionID = _cursor.getInt(_cursorIndexOfExcursionID);
        final String _tmpExcursionName;
        if (_cursor.isNull(_cursorIndexOfExcursionName)) {
          _tmpExcursionName = null;
        } else {
          _tmpExcursionName = _cursor.getString(_cursorIndexOfExcursionName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final int _tmpVacationID;
        _tmpVacationID = _cursor.getInt(_cursorIndexOfVacationID);
        final String _tmpExcursionDate;
        if (_cursor.isNull(_cursorIndexOfExcursionDate)) {
          _tmpExcursionDate = null;
        } else {
          _tmpExcursionDate = _cursor.getString(_cursorIndexOfExcursionDate);
        }
        _result = new Excursion(_tmpExcursionID,_tmpExcursionName,_tmpPrice,_tmpVacationID,_tmpExcursionDate);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
