package com.example.foodrecipeapp.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.foodrecipeapp.entity.CategoryItems;
import com.example.foodrecipeapp.entity.MealsItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RecipesDao_Impl implements RecipesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CategoryItems> __insertionAdapterOfCategoryItems;

  private final EntityInsertionAdapter<MealsItem> __insertionAdapterOfMealsItem;

  private final SharedSQLiteStatement __preparedStmtOfClearDB;

  private final SharedSQLiteStatement __preparedStmtOfClearMealDB;

  public RecipesDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategoryItems = new EntityInsertionAdapter<CategoryItems>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `CategoryItems` (`id`,`idcategory`,`strcategory`,`strcategorythumb`,`strcategorydescription`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CategoryItems entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.getId());
        }
        if (entity.getIdcategory() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getIdcategory());
        }
        if (entity.getStrcategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStrcategory());
        }
        if (entity.getStrcategorythumb() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStrcategorythumb());
        }
        if (entity.getStrcategorydescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStrcategorydescription());
        }
      }
    };
    this.__insertionAdapterOfMealsItem = new EntityInsertionAdapter<MealsItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `MEAL_ITEMS_TABLE` (`id`,`strMeal`,`strMealThumb`,`idMeal`,`categoryName`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MealsItem entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.getId());
        }
        if (entity.getStrMeal() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getStrMeal());
        }
        if (entity.getStrMealThumb() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStrMealThumb());
        }
        if (entity.getIdMeal() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIdMeal());
        }
        if (entity.getCategoryName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategoryName());
        }
      }
    };
    this.__preparedStmtOfClearDB = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM CategoryItems";
        return _query;
      }
    };
    this.__preparedStmtOfClearMealDB = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM MEAL_ITEMS_TABLE";
        return _query;
      }
    };
  }

  @Override
  public Object insertCategory(final CategoryItems categoryItems,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCategoryItems.insert(categoryItems);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMeal(final MealsItem mealItems,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMealsItem.insert(mealItems);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearDB(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearDB.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearDB.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearMealDB(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearMealDB.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearMealDB.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllCategories(final Continuation<? super List<CategoryItems>> $completion) {
    final String _sql = "select * from CategoryItems order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CategoryItems>>() {
      @Override
      @NonNull
      public List<CategoryItems> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "idcategory");
          final int _cursorIndexOfStrcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "strcategory");
          final int _cursorIndexOfStrcategorythumb = CursorUtil.getColumnIndexOrThrow(_cursor, "strcategorythumb");
          final int _cursorIndexOfStrcategorydescription = CursorUtil.getColumnIndexOrThrow(_cursor, "strcategorydescription");
          final List<CategoryItems> _result = new ArrayList<CategoryItems>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryItems _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpIdcategory;
            if (_cursor.isNull(_cursorIndexOfIdcategory)) {
              _tmpIdcategory = null;
            } else {
              _tmpIdcategory = _cursor.getString(_cursorIndexOfIdcategory);
            }
            final String _tmpStrcategory;
            if (_cursor.isNull(_cursorIndexOfStrcategory)) {
              _tmpStrcategory = null;
            } else {
              _tmpStrcategory = _cursor.getString(_cursorIndexOfStrcategory);
            }
            final String _tmpStrcategorythumb;
            if (_cursor.isNull(_cursorIndexOfStrcategorythumb)) {
              _tmpStrcategorythumb = null;
            } else {
              _tmpStrcategorythumb = _cursor.getString(_cursorIndexOfStrcategorythumb);
            }
            final String _tmpStrcategorydescription;
            if (_cursor.isNull(_cursorIndexOfStrcategorydescription)) {
              _tmpStrcategorydescription = null;
            } else {
              _tmpStrcategorydescription = _cursor.getString(_cursorIndexOfStrcategorydescription);
            }
            _item = new CategoryItems(_tmpId,_tmpIdcategory,_tmpStrcategory,_tmpStrcategorythumb,_tmpStrcategorydescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSpecificMealsList(final String categoryName,
      final Continuation<? super List<MealsItem>> $completion) {
    final String _sql = "select * from MEAL_ITEMS_TABLE where categoryName like ? order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (categoryName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, categoryName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MealsItem>>() {
      @Override
      @NonNull
      public List<MealsItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStrMeal = CursorUtil.getColumnIndexOrThrow(_cursor, "strMeal");
          final int _cursorIndexOfStrMealThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "strMealThumb");
          final int _cursorIndexOfIdMeal = CursorUtil.getColumnIndexOrThrow(_cursor, "idMeal");
          final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
          final List<MealsItem> _result = new ArrayList<MealsItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MealsItem _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpStrMeal;
            if (_cursor.isNull(_cursorIndexOfStrMeal)) {
              _tmpStrMeal = null;
            } else {
              _tmpStrMeal = _cursor.getString(_cursorIndexOfStrMeal);
            }
            final String _tmpStrMealThumb;
            if (_cursor.isNull(_cursorIndexOfStrMealThumb)) {
              _tmpStrMealThumb = null;
            } else {
              _tmpStrMealThumb = _cursor.getString(_cursorIndexOfStrMealThumb);
            }
            final String _tmpIdMeal;
            if (_cursor.isNull(_cursorIndexOfIdMeal)) {
              _tmpIdMeal = null;
            } else {
              _tmpIdMeal = _cursor.getString(_cursorIndexOfIdMeal);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            _item = new MealsItem(_tmpId,_tmpStrMeal,_tmpStrMealThumb,_tmpIdMeal,_tmpCategoryName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
