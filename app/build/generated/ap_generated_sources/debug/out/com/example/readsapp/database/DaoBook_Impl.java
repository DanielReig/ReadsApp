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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DaoBook_Impl implements DaoBook {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<dbbook> __insertionAdapterOfdbbook;

  private final EntityDeletionOrUpdateAdapter<dbbook> __deletionAdapterOfdbbook;

  public DaoBook_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfdbbook = new EntityInsertionAdapter<dbbook>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MyBooks` (`_ID`,`book`,`listBook`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, dbbook value) {
        stmt.bindLong(1, value.getId());
        if (value.getBook() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBook());
        }
        if (value.getList() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getList());
        }
      }
    };
    this.__deletionAdapterOfdbbook = new EntityDeletionOrUpdateAdapter<dbbook>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MyBooks` WHERE `_ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, dbbook value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addBook(final dbbook book) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfdbbook.insert(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void addlist(final dbbook list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfdbbook.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBook(final dbbook book) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfdbbook.handle(book);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteList(final dbbook list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfdbbook.handle(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<dbbook> getBooks(final String text) {
    final String _sql = "SELECT * FROM MyBooks WHERE listBook = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (text == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, text);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_ID");
      final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
      final int _cursorIndexOfList = CursorUtil.getColumnIndexOrThrow(_cursor, "listBook");
      final List<dbbook> _result = new ArrayList<dbbook>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final dbbook _item;
        _item = new dbbook();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpBook;
        _tmpBook = _cursor.getString(_cursorIndexOfBook);
        _item.setBook(_tmpBook);
        final String _tmpList;
        _tmpList = _cursor.getString(_cursorIndexOfList);
        _item.setList(_tmpList);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<dbbook> getlist() {
    final String _sql = "SELECT * FROM MyBooks WHERE book IS NULL OR book = ';'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_ID");
      final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
      final int _cursorIndexOfList = CursorUtil.getColumnIndexOrThrow(_cursor, "listBook");
      final List<dbbook> _result = new ArrayList<dbbook>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final dbbook _item;
        _item = new dbbook();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpBook;
        _tmpBook = _cursor.getString(_cursorIndexOfBook);
        _item.setBook(_tmpBook);
        final String _tmpList;
        _tmpList = _cursor.getString(_cursorIndexOfList);
        _item.setList(_tmpList);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
