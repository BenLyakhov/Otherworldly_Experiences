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
import com.example.vacations.entities.Vacation;
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
public final class VacationDAO_Impl implements VacationDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vacation> __insertionAdapterOfVacation;

  private final EntityDeletionOrUpdateAdapter<Vacation> __deletionAdapterOfVacation;

  private final EntityDeletionOrUpdateAdapter<Vacation> __updateAdapterOfVacation;

  public VacationDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVacation = new EntityInsertionAdapter<Vacation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `vacations` (`vacationID`,`vacationName`,`price`,`hotelName`,`startVacaDate`,`endVacaDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vacation entity) {
        statement.bindLong(1, entity.getVacationID());
        if (entity.getVacationName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVacationName());
        }
        statement.bindDouble(3, entity.getPrice());
        if (entity.getHotelName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getHotelName());
        }
        if (entity.getStartVacaDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStartVacaDate());
        }
        if (entity.getEndVacaDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEndVacaDate());
        }
      }
    };
    this.__deletionAdapterOfVacation = new EntityDeletionOrUpdateAdapter<Vacation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vacations` WHERE `vacationID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vacation entity) {
        statement.bindLong(1, entity.getVacationID());
      }
    };
    this.__updateAdapterOfVacation = new EntityDeletionOrUpdateAdapter<Vacation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vacations` SET `vacationID` = ?,`vacationName` = ?,`price` = ?,`hotelName` = ?,`startVacaDate` = ?,`endVacaDate` = ? WHERE `vacationID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vacation entity) {
        statement.bindLong(1, entity.getVacationID());
        if (entity.getVacationName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVacationName());
        }
        statement.bindDouble(3, entity.getPrice());
        if (entity.getHotelName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getHotelName());
        }
        if (entity.getStartVacaDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStartVacaDate());
        }
        if (entity.getEndVacaDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEndVacaDate());
        }
        statement.bindLong(7, entity.getVacationID());
      }
    };
  }

  @Override
  public void insert(final Vacation vacation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVacation.insert(vacation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Vacation vacation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfVacation.handle(vacation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Vacation vacation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfVacation.handle(vacation);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Vacation> getAllVacations() {
    final String _sql = "SELECT * FROM VACATIONS ORDER BY vacationID ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVacationID = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationID");
      final int _cursorIndexOfVacationName = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationName");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfHotelName = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelName");
      final int _cursorIndexOfStartVacaDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startVacaDate");
      final int _cursorIndexOfEndVacaDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endVacaDate");
      final List<Vacation> _result = new ArrayList<Vacation>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Vacation _item;
        final int _tmpVacationID;
        _tmpVacationID = _cursor.getInt(_cursorIndexOfVacationID);
        final String _tmpVacationName;
        if (_cursor.isNull(_cursorIndexOfVacationName)) {
          _tmpVacationName = null;
        } else {
          _tmpVacationName = _cursor.getString(_cursorIndexOfVacationName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpHotelName;
        if (_cursor.isNull(_cursorIndexOfHotelName)) {
          _tmpHotelName = null;
        } else {
          _tmpHotelName = _cursor.getString(_cursorIndexOfHotelName);
        }
        final String _tmpStartVacaDate;
        if (_cursor.isNull(_cursorIndexOfStartVacaDate)) {
          _tmpStartVacaDate = null;
        } else {
          _tmpStartVacaDate = _cursor.getString(_cursorIndexOfStartVacaDate);
        }
        final String _tmpEndVacaDate;
        if (_cursor.isNull(_cursorIndexOfEndVacaDate)) {
          _tmpEndVacaDate = null;
        } else {
          _tmpEndVacaDate = _cursor.getString(_cursorIndexOfEndVacaDate);
        }
        _item = new Vacation(_tmpVacationID,_tmpVacationName,_tmpPrice,_tmpHotelName,_tmpStartVacaDate,_tmpEndVacaDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Vacation getVacationByID(final int id) {
    final String _sql = "SELECT * FROM VACATIONS WHERE vacationID = ? Limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVacationID = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationID");
      final int _cursorIndexOfVacationName = CursorUtil.getColumnIndexOrThrow(_cursor, "vacationName");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfHotelName = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelName");
      final int _cursorIndexOfStartVacaDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startVacaDate");
      final int _cursorIndexOfEndVacaDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endVacaDate");
      final Vacation _result;
      if (_cursor.moveToFirst()) {
        final int _tmpVacationID;
        _tmpVacationID = _cursor.getInt(_cursorIndexOfVacationID);
        final String _tmpVacationName;
        if (_cursor.isNull(_cursorIndexOfVacationName)) {
          _tmpVacationName = null;
        } else {
          _tmpVacationName = _cursor.getString(_cursorIndexOfVacationName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpHotelName;
        if (_cursor.isNull(_cursorIndexOfHotelName)) {
          _tmpHotelName = null;
        } else {
          _tmpHotelName = _cursor.getString(_cursorIndexOfHotelName);
        }
        final String _tmpStartVacaDate;
        if (_cursor.isNull(_cursorIndexOfStartVacaDate)) {
          _tmpStartVacaDate = null;
        } else {
          _tmpStartVacaDate = _cursor.getString(_cursorIndexOfStartVacaDate);
        }
        final String _tmpEndVacaDate;
        if (_cursor.isNull(_cursorIndexOfEndVacaDate)) {
          _tmpEndVacaDate = null;
        } else {
          _tmpEndVacaDate = _cursor.getString(_cursorIndexOfEndVacaDate);
        }
        _result = new Vacation(_tmpVacationID,_tmpVacationName,_tmpPrice,_tmpHotelName,_tmpStartVacaDate,_tmpEndVacaDate);
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
