package com.example.proiect;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CurrenciesDB_Impl extends CurrenciesDB {
  private volatile CurrenciesDao _currenciesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `currencies` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT, `euro` TEXT, `usd` TEXT, `gbp` TEXT, `rub` TEXT, `jpy` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '26453cb07e0b474223da827e31404270')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `currencies`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCurrencies = new HashMap<String, TableInfo.Column>(7);
        _columnsCurrencies.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("euro", new TableInfo.Column("euro", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("usd", new TableInfo.Column("usd", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("gbp", new TableInfo.Column("gbp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("rub", new TableInfo.Column("rub", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencies.put("jpy", new TableInfo.Column("jpy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCurrencies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCurrencies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCurrencies = new TableInfo("currencies", _columnsCurrencies, _foreignKeysCurrencies, _indicesCurrencies);
        final TableInfo _existingCurrencies = TableInfo.read(_db, "currencies");
        if (! _infoCurrencies.equals(_existingCurrencies)) {
          return new RoomOpenHelper.ValidationResult(false, "currencies(com.example.proiect.Currencies).\n"
                  + " Expected:\n" + _infoCurrencies + "\n"
                  + " Found:\n" + _existingCurrencies);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "26453cb07e0b474223da827e31404270", "9ed20a2ea1165d96bfb778f15945e562");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "currencies");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `currencies`");
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
  public CurrenciesDao getCurrenciesDao() {
    if (_currenciesDao != null) {
      return _currenciesDao;
    } else {
      synchronized(this) {
        if(_currenciesDao == null) {
          _currenciesDao = new CurrenciesDao_Impl(this);
        }
        return _currenciesDao;
      }
    }
  }
}
