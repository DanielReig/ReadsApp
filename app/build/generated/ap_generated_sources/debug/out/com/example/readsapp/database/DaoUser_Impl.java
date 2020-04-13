package com.example.readsapp.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DaoUser_Impl implements DaoUser {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<dbUser> __insertionAdapterOfdbUser;

  private final EntityDeletionOrUpdateAdapter<dbUser> __deletionAdapterOfdbUser;

  private final EntityDeletionOrUpdateAdapter<dbUser> __updateAdapterOfdbUser;

  public DaoUser_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfdbUser = new EntityInsertionAdapter<dbUser>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MyUser` (`_ID`,`name`,`age`,`search`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, dbUser value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAge());
        if (value.getSearch() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSearch());
        }
      }
    };
    this.__deletionAdapterOfdbUser = new EntityDeletionOrUpdateAdapter<dbUser>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MyUser` WHERE `_ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, dbUser value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfdbUser = new EntityDeletionOrUpdateAdapter<dbUser>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MyUser` SET `_ID` = ?,`name` = ?,`age` = ?,`search` = ? WHERE `_ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, dbUser value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAge());
        if (value.getSearch() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSearch());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void addUser(final dbUser book) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfdbUser.insert(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(final dbUser book) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfdbUser.handle(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateUser(final dbUser user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfdbUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public String getSearch() {
    final String _sql = "SELECT search FROM MyUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAge() {
    final String _sql = "SELECT age FROM MyUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getNameUser() {
    final String _sql = "SELECT name FROM MyUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public dbUser getUser() {
    final String _sql = "SELECT * FROM MyUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_ID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "search");
      final dbUser _result;
      if(_cursor.moveToFirst()) {
        _result = new dbUser();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final int _tmpAge;
        _tmpAge = _cursor.getInt(_cursorIndexOfAge);
        _result.setAge(_tmpAge);
        final String _tmpSearch;
        _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
        _result.setSearch(_tmpSearch);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
