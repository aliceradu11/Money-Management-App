package com.example.proiect;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CurrenciesDao_Impl implements CurrenciesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Currencies> __insertionAdapterOfCurrencies;

  private final EntityDeletionOrUpdateAdapter<Currencies> __deletionAdapterOfCurrencies;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CurrenciesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCurrencies = new EntityInsertionAdapter<Currencies>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `currencies` (`id`,`date`,`euro`,`usd`,`gbp`,`rub`,`jpy`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Currencies value) {
        stmt.bindLong(1, value.getId());
        if (value.getDate() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDate());
        }
        if (value.getEuro() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEuro());
        }
        if (value.getUsd() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUsd());
        }
        if (value.getGbp() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGbp());
        }
        if (value.getRub() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRub());
        }
        if (value.getJpy() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getJpy());
        }
      }
    };
    this.__deletionAdapterOfCurrencies = new EntityDeletionOrUpdateAdapter<Currencies>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `currencies` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Currencies value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from currencies";
        return _query;
      }
    };
  }

  @Override
  public long insert(final Currencies currencies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCurrencies.insertAndReturnId(currencies);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Currencies currencies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCurrencies.handle(currencies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Currencies> getAll() {
    final String _sql = "select * from currencies";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfEuro = CursorUtil.getColumnIndexOrThrow(_cursor, "euro");
      final int _cursorIndexOfUsd = CursorUtil.getColumnIndexOrThrow(_cursor, "usd");
      final int _cursorIndexOfGbp = CursorUtil.getColumnIndexOrThrow(_cursor, "gbp");
      final int _cursorIndexOfRub = CursorUtil.getColumnIndexOrThrow(_cursor, "rub");
      final int _cursorIndexOfJpy = CursorUtil.getColumnIndexOrThrow(_cursor, "jpy");
      final List<Currencies> _result = new ArrayList<Currencies>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Currencies _item;
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        final String _tmpEuro;
        _tmpEuro = _cursor.getString(_cursorIndexOfEuro);
        final String _tmpUsd;
        _tmpUsd = _cursor.getString(_cursorIndexOfUsd);
        final String _tmpGbp;
        _tmpGbp = _cursor.getString(_cursorIndexOfGbp);
        final String _tmpRub;
        _tmpRub = _cursor.getString(_cursorIndexOfRub);
        final String _tmpJpy;
        _tmpJpy = _cursor.getString(_cursorIndexOfJpy);
        _item = new Currencies(_tmpDate,_tmpEuro,_tmpUsd,_tmpGbp,_tmpRub,_tmpJpy);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
